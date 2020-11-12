package chess;

import java.util.InputMismatchException;

import boardgame.Board;
import boardgame.Piece;
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
	
	//m�todo para mover uma pe�a da "sourcePosition" para "targetPosition"
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition(); //converte a posi��o "source" para a posi��o da matrix 
		Position target = targetPosition.toPosition(); //converte a posi��o "target" para a posi��o da matrix 
		//verifica se na posi��o de origem existe uma pe�a
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source, target); //responsavel por mover a pe�a da posi��o atual para a pretendida
		return (ChessPiece)capturedPiece; //tem que fazer um downcasting para o ChesPiece pois ape�a captura era do tipo Piece
	}
	
	//m�todo que recebe as coordenadas do Chess a1 to h8 
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); //instancia "ChessPosition" e chama m�todo toPosition() para converter converter para posi��o de matrix
	}
	
	//m�todo para validar a posi��o inicial da pe�a 
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new InputMismatchException("There is no piece on source position");
			}
		}
	
	//m�todo makeMove() para mover uma pe�a da "sourcePosition" para "targetPosition"
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); //remove a pe�a que estava na posi��o de origem
		Piece capturedPiece = board.removePiece(target); //remove a poss�vel pe�a que est� na posi��o de destino
		board.placePiece(p, target); //coloca a pe�a que estava na posi��o de origem na posi��o de destino
		return capturedPiece; //retorna a pe�a captura
	}
	
	//m�todo para setup inicial da partida colocando as pe�as no tabuleiro
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
