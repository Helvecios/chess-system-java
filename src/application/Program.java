package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {

		//instaciar um objeto tipo partida de xadrez
		ChessMatch chessMatch = new ChessMatch();
		//função para imprimir 
		//chama a classe UI e o método printBoard
		UI.printBoard(chessMatch.getPieces());
	}
}
