package chess;

import boardgame.BoardException;

public class ChessException extends BoardException { //para ser uma exce��o opcinal a ser tratada
	private static final long serialVersionUID = 1L;

	//construtor que recebe a mensagem
	public ChessException(String msg) {
		super(msg);
	}

}
