package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Pe�o
public class Pawn extends ChessPiece { //herda da classe ChesPiece

	public Pawn(Board board, Color color) {
		super(board, color); //herdado da classe ChesPiece
		
	} 
	
//m�todo movimentos poss�veis para o Pawn
@Override
public boolean[][] possibleMoves() {
	boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; //matrix auxiliar
	
	Position p = new Position(0, 0); //posi��o auxiliar
	
	
	//movimentos do pe�o na cor branca
	if(getColor() == Color.WHITE) { //verifica se � o pe�o branco
		p.setValues(position.getRow() - 1, position.getColumn()); //move uma posi��o acima
		//verifca se o pe�o pode mover uma casa para frente
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //significa que o pe�o pode mover uma casa para frente
			mat[p.getRow()][p.getColumn()] = true;
		}
	
	p.setValues(position.getRow() - 2, position.getColumn()); //move duas posi��o acima se for o primeiro movimento
	Position p2 = new Position(position.getRow() - 1, position.getColumn());
	//verifca se o pe�o pode mover duas casas para frente
	if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) { //significa que o pe�o pode mover duas casa par frente 
		mat[p.getRow()][p.getColumn()] = true;
	}
	
	//verifica se o pe�o pode mover na diagonal esquerda (para comer pe�o advers�rio)
	p.setValues(position.getRow() - 1, position.getColumn() - 1); //move uma posi��o na diagonal para a esquerda se existir uma pe�a advers�ria l�
	if(getBoard().positionExists(p) && isThereOpponentPiece(p)) { //significa que o pe�o pode mover na diagonal esquerda
		mat[p.getRow()][p.getColumn()] = true;
	}
	
	//verifica se o pe�o pode mover na diagonal direita (para comer pe�o advers�rio)
		p.setValues(position.getRow() - 1, position.getColumn() + 1); //move uma posi��o na diagonal para a esquerda se existir uma pe�a advers�ria l�
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) { //significa que o pe�o pode mover na diagonal esquerda
			mat[p.getRow()][p.getColumn()] = true;
		}
	
	}
	//movimentos do pe�o na cor preta
	else {
		p.setValues(position.getRow() + 1, position.getColumn()); //move uma posi��o acima
		//verifca se o pe�o pode mover uma casa para frente
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //significa que o pe�o pode mover uma casa para frente
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 2, position.getColumn()); //move duas posi��o acima se for o primeiro movimento
		Position p2 = new Position(position.getRow() + 1, position.getColumn());
		//verifca se o pe�o pode mover duas casas para frente
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) { //significa que o pe�o pode mover duas casa par frente 
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//verifica se o pe�o pode mover na diagonal esquerda (para comer pe�o advers�rio)
		p.setValues(position.getRow() + 1, position.getColumn() - 1); //move uma posi��o na diagonal para a esquerda se existir uma pe�a advers�ria l�
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) { //significa que o pe�o pode mover na diagonal esquerda
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//verifica se o pe�o pode mover na diagonal direita (para comer pe�o advers�rio)
			p.setValues(position.getRow() + 1, position.getColumn() + 1); //move uma posi��o na diagonal para a esquerda se existir uma pe�a advers�ria l�
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) { //significa que o pe�o pode mover na diagonal esquerda
				mat[p.getRow()][p.getColumn()] = true;
			}
		
		}
	
	return mat;
}
//converte objeto em String
	@Override
	public String toString() {
		return "P";
	}		
}
