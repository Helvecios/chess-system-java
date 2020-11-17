package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Rainha
public class Queen extends ChessPiece { //herda da classe ChesPiece
	
	//construtor com argumetos
	public Queen(Board board, Color color) {
		super(board, color); //atributos herdados da classe ChessPiece
	} 
	
	//converte objeto em String
	@Override
	public String toString() {
		return "Q";
	}
	
	//método movimentos possíveis para a Rook
	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
	Position p = new Position(0, 0);
	//above
	p.setValues(position.getRow() - 1, position.getColumn());
	while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
		mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		p.setRow(p.getRow() - 1);
	}
	if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se existe uma peça adversária e marca como true
		mat[p.getRow()][p.getColumn()] = true;
	}
	
	//left
	p.setValues(position.getRow(), position.getColumn() - 1);
	while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
		mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		p.setColumn(p.getColumn() - 1);
	}
	if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se existe uma peça adversária e marca como true
		mat[p.getRow()][p.getColumn()] = true;
	}
	
	//right
	p.setValues(position.getRow(), position.getColumn() + 1);
	while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
		mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		p.setColumn(p.getColumn() + 1);
	}
	if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se existe uma peça adversária e marca como true
		mat[p.getRow()][p.getColumn()] = true;
	}
	
	//below
	p.setValues(position.getRow() + 1, position.getColumn());
	while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
		mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		p.setRow(p.getRow() + 1);
	}
	if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se existe uma peça adversária e marca como true
		mat[p.getRow()][p.getColumn()] = true;
	}
	
	//noroeste
		p.setValues(position.getRow() - 1, position.getColumn() -1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se existe uma peça adversária e marca como true
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//nordeste
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se existe uma peça adversária e marca como true
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sudeste
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se existe uma peça adversária e marca como true
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sudoeste
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { //verifica se existe uma peça adversária e marca como true
			mat[p.getRow()][p.getColumn()] = true;
		}
	
	return mat; 
	}

}
