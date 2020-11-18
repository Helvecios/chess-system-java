package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		
		//list de peças do tipo captured e inicia como ArrayList<>
		List<ChessPiece> captured = new ArrayList<>();

		while (!chessMatch.getCheckMate()) { //enquanto a partida não tiver cheque mate, continua ...
			try {
				UI.clearScreen(); //chama método de limpar a tela	
				//chama a classe UI e o método printBoard
				UI.printMatch(chessMatch, captured); //imprime a partida e a lista de peças capturadas
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
				
				if (capturedPiece != null) {
					captured.add(capturedPiece); //adiciona a peça capturada a lista de peças capturadas
				}
				if (chessMatch.getPromoted() != null) { //significa que uma peça foi promovida
					System.out.print("Enter piece for promotion (B/N/R/Q)");
					String type = sc.nextLine(); //escolhe a peça que irá substituir o peão
					chessMatch.replacePromotedPiece(type);
				}
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
		UI.clearScreen(); //limpa a tela
		UI.printMatch(chessMatch, captured); //imprimi a partida com a visão do cheque mate
		
	}
}
