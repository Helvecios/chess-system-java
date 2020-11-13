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
	
	//m�todo movimentos poss�veis para a Rook
		@Override
		public boolean[][] possibleMoves() {
			boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
			return mat; //retorna uma matriz com todas as posi��es como false (simulando com a Rook estivesse preso)
		}

}
