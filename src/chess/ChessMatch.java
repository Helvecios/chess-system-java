package chess;

import boardgame.Board;
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
	
	//método que recebe as coordenadas do Chess a1 to h8 
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); //instancia "ChessPosition" e chama método toPosition() para converter converter para posição de matrix
	}
	
	//método para setup inicial da partida colocando as peças no tabuleiro
	private void initialSetup() {
		placeNewPiece('b', 6, new Rook(board, Color.WHITE)); //coloca a torre branca na posição 2, 1
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
	}
}
