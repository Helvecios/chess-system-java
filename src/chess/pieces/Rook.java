package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//torre
public class Rook extends ChessPiece { //herda da classe ChesPiece
	
	//construtor com argumetos
	public Rook(Board board, Color color) {
		super(board, color); //atributos herdados da classe ChessPiece
	} 
	
	//converte objeto em String
	@Override
	public String toString() {
		return "R";
	}

}
