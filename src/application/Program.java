package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {

		//instaciar um objeto tipo partida de xadrez
		ChessMatch chessMatch = new ChessMatch();
		//fun��o para imprimir 
		//chama a classe UI e o m�todo printBoard
		UI.printBoard(chessMatch.getPieces());
	}
}
