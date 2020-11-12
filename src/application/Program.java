package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		//instaciar um objeto tipo partida de xadrez
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
		
		//chama a classe UI e o método printBoard
		UI.printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println("Source: ");
		ChessPosition source = UI.readChessPosition(sc);
		
		System.out.println();
		System.out.println("Target: ");
		ChessPosition target = UI.readChessPosition(sc);
		
		ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		}
		
		
	}
}
