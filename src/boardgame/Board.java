package boardgame;

public class Board {
	
	//atributos
	private int rows;
	private int columns;
	private Piece[][] pieces; //matrix de peças
	
	//construtor com atributos
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) { //programação defensiva
			throw new BoardException("Error creating board: there must be at least 1 row and 2 column!");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //instanciar um objeto pieces
	}

	//getters and setters
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	//método para retornar a peça dada uma lina e coluna
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) { //programação defensiva
			throw new BoardException("Position not on the board!");
		}
		return pieces[row][column];
	}
	
	//fazer uma sobrecarga do método piece para receber a posição da peça
	public Piece piece(Position position) {
		if (!positionExists(position)) { //programação defensiva
			throw new BoardException("Position not on the board!");
		}	
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//método para colocar uma peça no tabuleiro
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) { //programação defensiva
			throw new BoardException("There is already a piece on position " + position);
	}	
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position; //informa que essa peça não está mais na posição nula
	}
	
	//método para remover a peça do tabuleiro
	public Piece removePiece(Position position) {
		if (!positionExists(position)) { //programação defensiva
			throw new BoardException("Position not on the board!");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position); //pega a peça a ser retirada
		//coloca a informaçao que não existe mais peça na posição
		aux.position = null; 
		pieces[position.getRow()][position.getColumn()] = null;
		return aux; //retorna a peça que foi retirada
	}
	
	//método auxiliar recebendo uma linha e uma coluna
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	//método para verificar se a posição existe
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
		
	}
	
	//método para verificar se existe uma peça na posição
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) { //programação defensiva
			throw new BoardException("Position not on the board!");
		}
		return piece(position) != null;
		
	}
 }
