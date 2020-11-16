package chess;

import boardgame.Position;

public class ChessPosition {
	
	//atributos
	private char column;
	private int row;
	
	//construtor com argumentos
	public ChessPosition(char column, int row) {
		
		 if (column < 'a' || column > 'h' || row <1 || row > 8) {//programa��o defensiva
			 throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8");
		 }
		 this.column = column;
		 this.row = row;
	}

	//getters
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	//m�todo toPosition(), para converter o ChessPosition para o Chess normal a1 to h8
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}

	//m�todo fromPosition(), para converter a posi��o do Chess normal a1 to h8 para ChessPosition
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());
	}
	
	//converte o objeto para String
	@Override
	public String toString() {
		return "" + column + row; //o "" � um macete para o compilador aceitar que � uma concateniza��o de Strings
	}
}
