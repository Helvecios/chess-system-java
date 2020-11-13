package boardgame;

public abstract class Piece {
	
	//atributo
	protected Position position;
	
	//associação da peça com o tabuleiro
	private Board board;

	//construtor com atributo
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	//gettter
	protected Board getBoard() { //protected "somente classes dentro do pacote e subclasses vão poder acessar o tabuleio de uma peça"
		return board;
	}
	
	//método abstrato para verificar movimentos possíveis
	public abstract boolean[][] possibleMoves();
	
	//implementando a operação concreto possibleMove() que está utilizando um métedo abstrato (chamado de hook methods)
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()] ;
	}
	
	//operação para verificar se existe pelo menos um movimento possível para a peça
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves(); //variável auxiliar
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