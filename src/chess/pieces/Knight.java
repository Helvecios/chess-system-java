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

	//método movimentos possíveis para o Knigh
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position); //pega a peça p que está na desta posiçao
		return p == null || p.getColor() != getColor(); //retorna a peça se ela existir e se a cor for diferente da cor deste cavalo
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
				
		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
				
		p.setValues(position.getRow() - 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
				
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
				
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
				
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
				
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
				
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) { //o cavalo pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
		
		return mat; //retorna uma matriz com todas as posições como false (simulando como o Knight estivesse preso)
	}
}