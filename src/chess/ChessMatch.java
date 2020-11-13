package chess;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//todas as regras do jogo ser�o implentadas nesta classe
public class ChessMatch {
	
	//atributo
	private Board board;
	private int turn;
	private Color currentPlayer;
	
	//declara a lista das pe�as do jogo e a lista das pe�as capturadas e instancia
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
		
	//construtor padr�o
	public ChessMatch() {
		board = new Board(8, 8);//instancia o tabuleiro
		turn = 1; //primeiro jogada
		currentPlayer = Color.WHITE; //o jogador das pe�as brancas � quem come�a a jogar
		initialSetup(); //chama o m�todo para colocar as pe�as no tabuleiro
	}

	//getters
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
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
	
	//m�todo para indicar poss�veis posi��es de movimento de uma pe�a
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition(); //converte uma posi��o de xadrez para um posi��o normal de matrix
		validateSourcePosition(position); //valida a posi��o de origem
		return board.piece(position).possibleMoves(); //retorna os movimentos poss�veis da pe�a nesta posi��o 
	}
	
	//m�todo para mover uma pe�a da "sourcePosition" para "targetPosition"
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition(); //converte a posi��o "source" para a posi��o da matrix 
		Position target = targetPosition.toPosition(); //converte a posi��o "target" para a posi��o da matrix 
		//verifica se na posi��o de origem existe uma pe�a
		validateSourcePosition(source);
		//verifica se na posi��o de destino tem uma pe�a
		validadeTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target); //responsavel por mover a pe�a da posi��o atual para a pretendida
		nextTurn(); //para trocar o turno (o outro jogador) 
		return (ChessPiece)capturedPiece; //tem que fazer um downcasting para o ChesPiece pois ape�a captura era do tipo Piece
	}
	
	//m�todo para validar a posi��o inicial da pe�a 
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new InputMismatchException("There is no piece on source position");
			}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) { //downcasting, se esta pe�a for diferente do jogar atual, significa que � uma pe�a do advers�rio, n�o posso move-la
			throw new ChessException("The chosen piece is not yours");
			
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	//m�todo para validar a posi��o final da pe�a 
	private void validadeTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
		
	}
	
	//m�todo makeMove() para mover uma pe�a da "sourcePosition" para "targetPosition"
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); //remove a pe�a que estava na posi��o de origem
		Piece capturedPiece = board.removePiece(target); //remove a poss�vel pe�a que est� na posi��o de destino
		board.placePiece(p, target); //coloca a pe�a que estava na posi��o de origem na posi��o de destino
		
		if(capturedPiece != null); //significa que capturei uma pe�a
			piecesOnTheBoard.remove(capturedPiece); //remove a pe�a na lista de pe�as do jogo
			capturedPieces.add(capturedPiece); //incluie a pe�a na lista de pe�as
		return capturedPiece; //retorna a pe�a captura
	}
	
	//m�todo para trocar o turno para o outro jogador
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;//condi��o tern�ria para trocar o jogador
		}
	
	//m�todo que recebe as coordenadas do Chess a1 to h8 
		private void placeNewPiece(char column, int row, ChessPiece piece) {
			board.placePiece(piece, new ChessPosition(column, row).toPosition()); //instancia "ChessPosition" e chama m�todo toPosition() para converter converter para posi��o de matrix
			piecesOnTheBoard.add(piece); //incluie a pe�a na lista de pe�as do jogo
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
