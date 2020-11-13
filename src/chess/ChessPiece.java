package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece { //herda da classe Piece

	//atributo
	private Color color;

	//construtor com argumentos
	public ChessPiece(Board board, Color color) {
		super(board); //herdado da classe Piece
		this.color = color;
	}
	
	//getter
	public Color getColor() {
		return color;
	}
}
