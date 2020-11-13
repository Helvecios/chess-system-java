package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//Rei
public class King extends ChessPiece { //herda da classe ChesPiece

	public King(Board board, Color color) {
		super(board, color); //atributos herdados da classe ChessPiece
	} 
	
	//converte objeto em String
	@Override
	public String toString() {
		return "K";
	}

	//método movimentos possíveis para o King
	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat; //retorna uma matriz com todas as posições como false (simulando com o King estivesse preso)
	}
} 
