package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//Peão
public class Pawn extends ChessPiece { //herda da classe ChesPiece
	
	//dependencia (associação) para partida (ChessMatch)
	private ChessMatch chessMatch;

	//construtor com argumentos
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color); //herdado da classe ChesPiece
		this.chessMatch = chessMatch; 
	} 
	
//método movimentos possíveis para o Pawn
@Override
public boolean[][] possibleMoves() {
	boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()]; //matrix auxiliar
	
	Position p = new Position(0, 0); //posição auxiliar
	
	
	//movimentos do peão na cor branca
	if(getColor() == Color.WHITE) { //verifica se é o peão branco
		p.setValues(position.getRow() - 1, position.getColumn()); //move uma posição acima
		//verifca se o peão pode mover uma casa para frente
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //significa que o peão pode mover uma casa para frente
			mat[p.getRow()][p.getColumn()] = true;
		}
	
	p.setValues(position.getRow() - 2, position.getColumn()); //move duas posição acima se for o primeiro movimento
	Position p2 = new Position(position.getRow() - 1, position.getColumn());
	//verifca se o peão pode mover duas casas para frente
	if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) { //significa que o peão pode mover duas casa par frente 
		mat[p.getRow()][p.getColumn()] = true;
	}
	
	//verifica se o peão pode mover na diagonal esquerda (para comer peão adversário)
	p.setValues(position.getRow() - 1, position.getColumn() - 1); //move uma posição na diagonal para a esquerda se existir uma peça adversária lá
	if(getBoard().positionExists(p) && isThereOpponentPiece(p)) { //significa que o peão pode mover na diagonal esquerda
		mat[p.getRow()][p.getColumn()] = true;
	}
	
	//verifica se o peão pode mover na diagonal direita (para comer peão adversário)
		p.setValues(position.getRow() - 1, position.getColumn() + 1); //move uma posição na diagonal para a esquerda se existir uma peça adversária lá
	if(getBoard().positionExists(p) && isThereOpponentPiece(p)) { //significa que o peão pode mover na diagonal esquerda
		mat[p.getRow()][p.getColumn()] = true;
	}
		
	//tratamento do en passant peças brancas
	if (position.getRow() == 3) {
		//peão do adversário a esquerda
		Position left = new Position(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
			mat[left.getRow() - 1][left.getColumn()] = true; //posição que o peão ira se mover no "en passant"
		}
		//peão do adversário a esquerda
		Position right = new Position(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVunerable()) {
			mat[right.getRow() - 1][right.getColumn()] = true; //posição que o peão ira se mover no "en passant"
		}
	}
	
	}
	//movimentos do peão na cor preta
	else {
		p.setValues(position.getRow() + 1, position.getColumn()); //move uma posição acima
		//verifca se o peão pode mover uma casa para frente
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { //significa que o peão pode mover uma casa para frente
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 2, position.getColumn()); //move duas posição acima se for o primeiro movimento
		Position p2 = new Position(position.getRow() + 1, position.getColumn());
		//verifca se o peão pode mover duas casas para frente
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) { //significa que o peão pode mover duas casa par frente 
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//verifica se o peão pode mover na diagonal esquerda (para comer peão adversário)
		p.setValues(position.getRow() + 1, position.getColumn() - 1); //move uma posição na diagonal para a esquerda se existir uma peça adversária lá
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) { //significa que o peão pode mover na diagonal esquerda
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//verifica se o peão pode mover na diagonal direita (para comer peão adversário)
			p.setValues(position.getRow() + 1, position.getColumn() + 1); //move uma posição na diagonal para a esquerda se existir uma peça adversária lá
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) { //significa que o peão pode mover na diagonal esquerda
				mat[p.getRow()][p.getColumn()] = true;
			}
			
		//tratamento do en passant peças pretas
		if (position.getRow() == 4) {
			//peão do adversário a esquerda
			Position left = new Position(position.getRow(), position.getColumn() - 1);
			if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
				mat[left.getRow() + 1][left.getColumn()] = true; //posição que o peão ira se mover no "en passant"
			}
			//peão do adversário a esquerda
			Position right = new Position(position.getRow(), position.getColumn() + 1);
			if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVunerable()) {
				mat[right.getRow() + 1][right.getColumn()] = true; //posição que o peão ira se mover no "en passant"
			}
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
