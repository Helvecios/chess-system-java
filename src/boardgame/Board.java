package boardgame;

public class Board {
	
	//atributos
	private int rows;
	private int columns;
	private Piece[][] pieces; //matrix de peças
	
	//construtor com atributos
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //instanciar um objeto pieces
	}

	//getters and setters
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	//método para retornar a peça dada uma lina e coluna
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	
	//fazer uma sobrcarga do método piece para receber a posição da peça
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
 }
