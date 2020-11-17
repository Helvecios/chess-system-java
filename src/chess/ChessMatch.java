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

//todas as regras do jogo serão implentadas nesta classe
public class ChessMatch {
	
	//atributo
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	
	
	//declara a lista das peças do jogo e a lista das peças capturadas e instancia
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
		
	//construtor padrão
	public ChessMatch() {
		board = new Board(8, 8);//instancia o tabuleiro
		turn = 1; //primeiro jogada
		currentPlayer = Color.WHITE; //o jogador das peças brancas é quem começa a jogar
		initialSetup(); //chama o método para colocar as peças no tabuleiro
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
		
		//verifica se o próprio jogador se colocou em cheque
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put youself in check");
		}
		
		//verifica se o oponente entrou em check
		check = (testCheck(opponent(currentPlayer))) ? true : false;//expressão condicional ternária
		
		
		//
		if(testCheckMate(opponent(currentPlayer))) { //se a jogada deixou o openente em cheque mate, "true"
			checkMate = true;
		}
		else {
		nextTurn(); //para trocar o turno (o outro jogador)
		}
		return (ChessPiece)capturedPiece; //tem que fazer um downcasting para o ChesPiece pois apeça captura era do tipo Piece
	}
	
	//método para validar a posição inicial da peça 
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new InputMismatchException("There is no piece on source position");
			}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) { //downcasting, se esta peça for diferente do jogar atual, significa que é uma peça do adversário, não posso move-la
			throw new ChessException("The chosen piece is not yours");
			
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
		ChessPiece p = (ChessPiece)board.removePiece(source); //remove a peça que estava na posição de origem. Fazer downcasting (ChessPiece)board
		p.increaseMoveCount(); //soma o número de movimentos
		Piece capturedPiece = board.removePiece(target); //remove a possível peça que está na posição de destino
		board.placePiece(p, target); //coloca a peça que estava na posição de origem na posição de destino
		
		if(capturedPiece != null); //significa que capturei uma peça
			piecesOnTheBoard.remove(capturedPiece); //remove a peça na lista de peças do jogo
			capturedPieces.add(capturedPiece); //incluie a peça na lista de peças
		return capturedPiece; //retorna a peça captura
	}
	
	//método para desfazer o movimento
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target); //remove a peça que vc colocou no destino. Faz um downcasting (ChessPiece)board
		p.decreaseMoveCount(); //subtrai o número de movimentos
		board.placePiece(p, source);//devolve a peça para a origem
		//se tinha capturado uma peça ela tem que ser devolvida para a posiçao de destino
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target); 
			capturedPieces.remove(capturedPiece); //remove a peça que estava na posição de destino
			piecesOnTheBoard.add(capturedPiece); //adciona a peça na lista de peças do tabuleiro
		}
	}
	
	//método para trocar o turno para o outro jogador
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;//condição ternária para trocar o jogador
		}
	
	//método para devolver o oponente da cor
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : color.WHITE;
	}
	
	//método para localizar o rei de uma determinada cor
	private ChessPiece king(Color color) {
		//usando expressão lambda para filtrar uma lista
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		//para cada peça "p" na lista list testar: 
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is not " + color + "King on the board");
	}
	
	//método par testar se um dos reis está em cheque
	private boolean testCheck(Color color) {
		Position KingPosition = king(color).getChessPosition().toPosition(); //pega a posição do rei em formato de matrix
		//lista de peças oponentes ao rei escolhido para veririficar se está em cheque
		List<Piece> opponetPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		//para cada peça "p" na lista de peças do oponente testar: 
		for (Piece p : opponetPieces) {
			boolean [][] mat = p.possibleMoves(); //matrix de movimentos possíveis da "opponetPieces" 
			if (mat[KingPosition.getRow()][KingPosition.getColumn()]) { //verifica se o rei está em cheque
				return true;
			}
		}
		return false; //o rei não está em cheque
	}
	
	//método par testar cheque mate
	private boolean testCheckMate(Color color) { 
		if(!testCheck(color)) { //não está em cheque mate
			return false;
		}
		//cria lista com todas as peças da cor do jogador em cheque
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList()); 
		//para percorrer as peças pertencentes a list
		for(Piece p: list) { //se tiver alguma peça que tira o rei do cheque mate, retorna false
			//movimentos possíveis da peça "p"
			boolean[][] mat = p.possibleMoves();
			//para percorrer toda a matriz
			for (int i=0; i<board.getRows(); i++) {
				for (int j=0; j<board.getColumns(); j++) {
					if(mat[i][j]) { //é um movimento possível?
						Position source = ((ChessPiece)p).getChessPosition().toPosition(); //tem que fazer um downcasting
						//fazendo o movimento da peça "p" da origem para o destino
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						//teste para verificar se ainda está em cheque
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece); //para desfazer o movimento
						if (!testCheck) { //se não estiver mais em cheque, o movimento tirou do cheque mate, retorna false
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	
	//método que recebe as coordenadas do Chess a1 to h8 
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); //instancia "ChessPosition" e chama método toPosition() para converter converter para posição de matrix
		piecesOnTheBoard.add(piece); //incluie a peça na lista de peças do jogo
	}
	
	//método para setup inicial da partida colocando as peças no tabuleiro
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
