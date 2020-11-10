package boardgame;

public class Piece {
	
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
}
