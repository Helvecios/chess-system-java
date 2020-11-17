package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Rei
public class Knight extends ChessPiece { //herda da classe ChesPiece

	public Knight(Board board, Color color) {
		super(board, color); //atributos herdados da classe ChessPiece
	} 
	
	//converte objeto em String
	@Override
	public String toString() {
		return "N";
	}

	//m�todo movimentos poss�veis para o Knigh
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position); //pega a pe�a p que est� na desta posi�ao
		return p == null || p.getColor() != getColor(); //retorna a pe�a se ela existir e se a cor for diferente da cor deste cavalo
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
				
		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
				
		p.setValues(position.getRow() - 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
				
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
				
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
				
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
				
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
				
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
		
		return mat; //retorna uma matriz com todas as posi��es como false (simulando como o Knight estivesse preso)
	}
}