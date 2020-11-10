package boardgame;

public class Board {
	
	//atributos
	private int rows;
	private int columns;
	private Piece[][] pieces; //matrix de pe�as
	
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
}
