package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//todas as regras do jogo serão implentadas nesta classe
public class ChessMatch {
	
	//atributo
	private Board board;
	
	//construtor padrão
	public ChessMatch() {
		board = new Board(8, 8);//instancia o tabuleiro
		initialSetup(); //chama o método para colocar as peças no tabuleiro
	}

	//método para retornar uma matriz de peças de xadrez correspondente a esta partida
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; //instanciar a matrix "mat"
		//para percorrer toda a matrix
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); //tem que fazer um downcasting para interpretar como uma peça de xadrez
			}
		}
		return mat; //retorna a matrix de peças da partida de xadrez "mat"
	}
	
	//método para setup inicial da partida colocando as peças no tabuleiro
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1)); //coloca a torre branca na posição 2, 1
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
	}
}
