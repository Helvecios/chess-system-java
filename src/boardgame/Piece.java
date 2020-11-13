package boardgame;

public abstract class Piece {
	
	//atributo
	protected Position position;
	
	//associa��o da pe�a com o tabuleiro
	private Board board;

	//construtor com atributo
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	//gettter
	protected Board getBoard() { //protected "somente classes dentro do pacote e subclasses v�o poder acessar o tabuleio de uma pe�a"
		return board;
	}
	
	//m�todo abstrato para verificar movimentos poss�veis
	public abstract boolean[][] possibleMoves();
	
	//implementando a opera��o concreto possibleMove() que est� utilizando um m�tedo abstrato (chamado de hook methods)
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()] ;
	}
	
	//opera��o para verificar se existe pelo menos um movimento poss�vel para a pe�a
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves(); //vari�vel auxiliar
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}