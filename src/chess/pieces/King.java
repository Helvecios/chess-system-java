package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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

	//m�todo movimentos poss�veis para o King
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position); //pega a pe�a p que est� na desta posi�ao
		return p == null || p.getColor() != getColor(); //retorna a pe�a se ela existir e se a cor for diferente da cor deste rei
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
		
		//below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
		
		
		//left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
		
		//right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
		
		//noroeste
		p.setValues(position.getRow() -1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
		
		//nordeste
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
		
		//sudoeste
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
		
		//sudeste
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posi��o "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posi��o poss�vel)
		}
		
		return mat; //retorna uma matriz com todas as posi��es como false (simulando como o King estivesse preso)
	}
} 
