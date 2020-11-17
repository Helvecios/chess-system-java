package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//Rei
public class King extends ChessPiece { //herda da classe ChesPiece
	
	//cria uma depend�ncia (associa��o) para a ChessMatch
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color); //atributos herdados da classe ChessPiece
		this.chessMatch = chessMatch;
	} 
	
	//converte objeto em String
	@Override
	public String toString() {
		return "K";
	}
	
	//m�todo para testar se a torre est� em condi��o de roque
	private boolean testRookCasting(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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
		
		//movimento especial para fazer Roque
		if(getMoveCount() == 0 && !chessMatch.getCheck()) { //verifica se o rei n�o movimentou e se ele n�o est� em cheque
			//Roque pequeno
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);//instanciar a posi��o da torre do rei
				if (testRookCasting(posT1)) { //verifica se existe uma torre l�
					//teste para vericar se as duas casas entre o rei e a torre est�o vazias
					Position p1 = new Position(position.getRow(), position.getColumn() + 1);
					Position p2 = new Position(position.getRow(), position.getColumn() + 2);
					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
						mat[position.getRow()][position.getColumn() + 2] = true; //desloca o rei duas casas para a direita
					}
				}
			//Roque grande
			Position posT2 = new Position(position.getRow(), position.getColumn() -4);//instanciar a posi��o da torre da rainha
				if (testRookCasting(posT2)) { //verifica se existe uma torre l�
					//teste para vericar se as tres casas entre o rei e a torre est�o vazias
					Position p1 = new Position(position.getRow(), position.getColumn() - 1);
					Position p2 = new Position(position.getRow(), position.getColumn() - 2);
					Position p3 = new Position(position.getRow(), position.getColumn() - 3);
					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
						mat[position.getRow()][position.getColumn() - 2] = true; //desloca o rei duas casas para a esquerda
					}
				}	
		}
		return mat; //retorna uma matriz com todas as posi��es como false (simulando como o King estivesse preso)
	}
} 
