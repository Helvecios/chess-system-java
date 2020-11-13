package chess;

import java.util.InputMismatchException;

import boardgame.Board;
import boardgame.Piece;
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
	
	//método para indicar possíveis posições de movimento de uma peça
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition(); //converte uma posição de xadrez para um posição normal de matrix
		validateSourcePosition(position); //valida a posição de origem
		return board.piece(position).possibleMoves(); //retorna os movimentos possíveis da peça nesta posição 
	}
	
	//método para mover uma peça da "sourcePosition" para "targetPosition"
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition(); //converte a posição "source" para a posição da matrix 
		Position target = targetPosition.toPosition(); //converte a posição "target" para a posição da matrix 
		//verifica se na posição de origem existe uma peça
		validateSourcePosition(source);
		//verifica se na posição de destino tem uma peça
		validadeTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target); //responsavel por mover a peça da posição atual para a pretendida
		return (ChessPiece)capturedPiece; //tem que fazer um downcasting para o ChesPiece pois apeça captura era do tipo Piece
	}
	
	//método que recebe as coordenadas do Chess a1 to h8 
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); //instancia "ChessPosition" e chama método toPosition() para converter converter para posição de matrix
	}
	
	//método para validar a posição inicial da peça 
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new InputMismatchException("There is no piece on source position");
			}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	//método para validar a posição final da peça 
	private void validadeTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
		
	}
	
	//método makeMove() para mover uma peça da "sourcePosition" para "targetPosition"
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); //remove a peça que estava na posição de origem
		Piece capturedPiece = board.removePiece(target); //remove a possível peça que está na posição de destino
		board.placePiece(p, target); //coloca a peça que estava na posição de origem na posição de destino
		return capturedPiece; //retorna a peça captura
	}
	
	//método para setup inicial da partida colocando as peças no tabuleiro
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
