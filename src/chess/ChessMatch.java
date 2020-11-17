package chess;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Rook;

//todas as regras do jogo ser�o implentadas nesta classe
public class ChessMatch {
	
	//atributo
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	
	
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
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		
		//verifica se o pr�prio jogador se colocou em cheque
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put youself in check");
		}
		
		//verifica se o oponente entrou em check
		check = (testCheck(opponent(currentPlayer))) ? true : false;//express�o condicional tern�ria
		
		
		//
		if(testCheckMate(opponent(currentPlayer))) { //se a jogada deixou o openente em cheque mate, "true"
			checkMate = true;
		}
		else {
		nextTurn(); //para trocar o turno (o outro jogador)
		}
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
		ChessPiece p = (ChessPiece)board.removePiece(source); //remove a pe�a que estava na posi��o de origem. Fazer downcasting (ChessPiece)board
		p.increaseMoveCount(); //soma o n�mero de movimentos
		Piece capturedPiece = board.removePiece(target); //remove a poss�vel pe�a que est� na posi��o de destino
		board.placePiece(p, target); //coloca a pe�a que estava na posi��o de origem na posi��o de destino
		
		if(capturedPiece != null); //significa que capturei uma pe�a
			piecesOnTheBoard.remove(capturedPiece); //remove a pe�a na lista de pe�as do jogo
			capturedPieces.add(capturedPiece); //incluie a pe�a na lista de pe�as
		return capturedPiece; //retorna a pe�a captura
	}
	
	//m�todo para desfazer o movimento
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target); //remove a pe�a que vc colocou no destino. Faz um downcasting (ChessPiece)board
		p.decreaseMoveCount(); //subtrai o n�mero de movimentos
		board.placePiece(p, source);//devolve a pe�a para a origem
		//se tinha capturado uma pe�a ela tem que ser devolvida para a posi�ao de destino
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target); 
			capturedPieces.remove(capturedPiece); //remove a pe�a que estava na posi��o de destino
			piecesOnTheBoard.add(capturedPiece); //adciona a pe�a na lista de pe�as do tabuleiro
		}
	}
	
	//m�todo para trocar o turno para o outro jogador
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;//condi��o tern�ria para trocar o jogador
		}
	
	//m�todo para devolver o oponente da cor
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : color.WHITE;
	}
	
	//m�todo para localizar o rei de uma determinada cor
	private ChessPiece king(Color color) {
		//usando express�o lambda para filtrar uma lista
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		//para cada pe�a "p" na lista list testar: 
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is not " + color + "King on the board");
	}
	
	//m�todo par testar se um dos reis est� em cheque
	private boolean testCheck(Color color) {
		Position KingPosition = king(color).getChessPosition().toPosition(); //pega a posi��o do rei em formato de matrix
		//lista de pe�as oponentes ao rei escolhido para veririficar se est� em cheque
		List<Piece> opponetPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		//para cada pe�a "p" na lista de pe�as do oponente testar: 
		for (Piece p : opponetPieces) {
			boolean [][] mat = p.possibleMoves(); //matrix de movimentos poss�veis da "opponetPieces" 
			if (mat[KingPosition.getRow()][KingPosition.getColumn()]) { //verifica se o rei est� em cheque
				return true;
			}
		}
		return false; //o rei n�o est� em cheque
	}
	
	//m�todo par testar cheque mate
	private boolean testCheckMate(Color color) { 
		if(!testCheck(color)) { //n�o est� em cheque mate
			return false;
		}
		//cria lista com todas as pe�as da cor do jogador em cheque
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList()); 
		//para percorrer as pe�as pertencentes a list
		for(Piece p: list) { //se tiver alguma pe�a que tira o rei do cheque mate, retorna false
			//movimentos poss�veis da pe�a "p"
			boolean[][] mat = p.possibleMoves();
			//para percorrer toda a matriz
			for (int i=0; i<board.getRows(); i++) {
				for (int j=0; j<board.getColumns(); j++) {
					if(mat[i][j]) { //� um movimento poss�vel?
						Position source = ((ChessPiece)p).getChessPosition().toPosition(); //tem que fazer um downcasting
						//fazendo o movimento da pe�a "p" da origem para o destino
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						//teste para verificar se ainda est� em cheque
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece); //para desfazer o movimento
						if (!testCheck) { //se n�o estiver mais em cheque, o movimento tirou do cheque mate, retorna false
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	
	//m�todo que recebe as coordenadas do Chess a1 to h8 
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); //instancia "ChessPosition" e chama m�todo toPosition() para converter converter para posi��o de matrix
		piecesOnTheBoard.add(piece); //incluie a pe�a na lista de pe�as do jogo
	}
	
	//m�todo para setup inicial da partida colocando as pe�as no tabuleiro
	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
	}
}
