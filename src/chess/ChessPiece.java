package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

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
	
	//oper��o para verificar se existe um pe�a  advers�ria na posi��o desejada
	protected boolean isThereOpponentPiece(Position position) {
		//pega a pe�a que est� na posi��o desejada
		ChessPiece p = (ChessPiece)getBoard().piece(position); //tem que fazer um downcasting
		return p != null && p.getColor() != color; // verifica se a pe�a � do advers�rio
	}
}
