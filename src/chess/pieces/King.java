package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//Rei
public class King extends ChessPiece { //herda da classe ChesPiece
	
	//cria uma dependência (associação) para a ChessMatch
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
	
	//método para testar se a torre está em condição de roque
	private boolean testRookCasting(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	//método movimentos possíveis para o King
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position); //pega a peça p que está na desta posiçao
		return p == null || p.getColor() != getColor(); //retorna a peça se ela existir e se a cor for diferente da cor deste rei
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
		
		//below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
		
		
		//left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
		
		//right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
		
		//noroeste
		p.setValues(position.getRow() -1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
		
		//nordeste
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
		
		//sudoeste
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
		
		//sudeste
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) { //o rei pode mover para a posição "p"
			mat[p.getRow()][p.getColumn()] = true; //marca como true (posição possível)
		}
		
		//movimento especial para fazer Roque
		if(getMoveCount() == 0 && !chessMatch.getCheck()) { //verifica se o rei não movimentou e se ele não está em cheque
			//Roque pequeno
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);//instanciar a posição da torre do rei
				if (testRookCasting(posT1)) { //verifica se existe uma torre lá
					//teste para vericar se as duas casas entre o rei e a torre estão vazias
					Position p1 = new Position(position.getRow(), position.getColumn() + 1);
					Position p2 = new Position(position.getRow(), position.getColumn() + 2);
					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
						mat[position.getRow()][position.getColumn() + 2] = true; //desloca o rei duas casas para a direita
					}
				}
			//Roque grande
			Position posT2 = new Position(position.getRow(), position.getColumn() -4);//instanciar a posição da torre da rainha
				if (testRookCasting(posT2)) { //verifica se existe uma torre lá
					//teste para vericar se as tres casas entre o rei e a torre estão vazias
					Position p1 = new Position(position.getRow(), position.getColumn() - 1);
					Position p2 = new Position(position.getRow(), position.getColumn() - 2);
					Position p3 = new Position(position.getRow(), position.getColumn() - 3);
					if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
						mat[position.getRow()][position.getColumn() - 2] = true; //desloca o rei duas casas para a esquerda
					}
				}	
		}
		return mat; //retorna uma matriz com todas as posições como false (simulando como o King estivesse preso)
	}
} 
