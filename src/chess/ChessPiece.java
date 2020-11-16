package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece { //herda da classe Piece

	//atributo
	private Color color;
	private int moveCount;

	//construtor com argumentos
	public ChessPiece(Board board, Color color) {
		super(board); //herdado da classe Piece
		this.color = color;
	}
	
	//getter
	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
		
	//método para incrementar o contador de movimentos
	public void increaseMoveCount() 
	{
		moveCount++;
	}
	
	//método para decrementar o contador de movimentos
		public void decreaseMoveCount() 
		{
			moveCount--;
		}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position); //converte position para chessPosition
	}
	
	//operção para verificar se existe um peça  adversária na posição desejada
	protected boolean isThereOpponentPiece(Position position) {
		//pega a peça que está na posição desejada
		ChessPiece p = (ChessPiece)getBoard().piece(position); //tem que fazer um downcasting
		return p != null && p.getColor() != color; // verifica se a peça é do adversário
	}
}
