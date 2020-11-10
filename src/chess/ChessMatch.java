package chess;

import boardgame.Board;

//todas as regras do jogo serão implentadas nesta classe
public class ChessMatch {
	
	//atributo
	private Board board;
	
	//construtor padrão
	public ChessMatch() {
		board = new Board(8, 8);//instancia o tabuleiro
	}

	//método para retornar uma matriz de peças de xadrez correspondente a esta partida
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; //instanciar a matrix "mat"
		//para percorrer toda a matrix
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getRows(); j++) {
				mat [i][j] = (ChessPiece) board.piece(i, j); //tem que fazer um downcasting para interpretar como uma peça de xadrez
			}
		}
		return mat; //retorna a matrix de peças da partida de xadrez "mat"
	}
}
