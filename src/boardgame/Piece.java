package boardgame;

public class Piece {
	
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
}
