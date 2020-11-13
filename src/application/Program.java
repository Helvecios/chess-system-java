package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		//instaciar um objeto tipo partida de xadrez
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			try {
			UI.clearScreen(); //chama método de limpar a tela	
			//chama a classe UI e o método printBoard
			UI.printMatch(chessMatch); //imprime a partida
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc);
			
			//declara um matrix chamada possibleMovies
			boolean[][] possibleMoves = chessMatch.possibleMoves(source);
			UI.clearScreen(); //limpa a tela
			UI.printBoard(chessMatch.getPieces(), possibleMoves); //imprime o tabuleiro e sobrecarrega com os movimentos possíveis
			
			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine(); //para aguardar apertar enter
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine(); //para aguardar apertar enter
			}
		}
		
	}
}
