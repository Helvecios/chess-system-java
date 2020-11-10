package boardgame;

public class Board {
	
	//atributos
	private int rows;
	private int columns;
	private Piece[][] pieces; //matrix de pe�as
	
	//construtor com atributos
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) { //programa��o defensiva
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

	//m�todo para retornar a pe�a dada uma lina e coluna
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) { //programa��o defensiva
			throw new BoardException("Position not on the board!");
		}
		return pieces[row][column];
	}
	
	//fazer uma sobrecarga do m�todo piece para receber a posi��o da pe�a
	public Piece piece(Position position) {
		if (!positionExists(position)) { //programa��o defensiva
			throw new BoardException("Position not on the board!");
		}	
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//m�todo para colocar uma pe�a no tabuleiro
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) { //programa��o defensiva
			throw new BoardException("There is already a piece on position " + position);
	}	
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position; //informa que essa pe�a n�o est� mais na posi��o nula
	}
	
	//m�todo auxiliar recebendo uma linha e uma coluna
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	//m�todo para verificar se a posi��o existe
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	//m�todo para verificar se existe uma pe�a na posi��o
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) { //programa��o defensiva
			throw new BoardException("Position not on the board!");
		}
		return piece(position) != null;
		
	}
 }
