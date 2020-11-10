package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//todas as regras do jogo ser�o implentadas nesta classe
public class ChessMatch {
	
	//atributo
	private Board board;
	
	//construtor padr�o
	public ChessMatch() {
		board = new Board(8, 8);//instancia o tabuleiro
		initialSetup(); //chama o m�todo para colocar as pe�as no tabuleiro
	}

	//m�todo para retornar uma matriz de pe�as de xadrez correspondente a esta partida
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; //instanciar a matrix "mat"
		//para percorrer toda a matrix
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); //tem que fazer um downcasting para interpretar como uma pe�a de xadrez
			}
		}
		return mat; //retorna a matrix de pe�as da partida de xadrez "mat"
	}
	
	//m�todo para setup inicial da partida colocando as pe�as no tabuleiro
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1)); //coloca a torre branca na posi��o 2, 1
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
	}
}
