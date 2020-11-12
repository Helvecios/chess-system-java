package chess;

public class ChessException extends RuntimeException { //para ser uma exceção opcinal a ser tratada
	private static final long serialVersionUID = 1L;

	//construtor que recebe a mensagem
	public ChessException(String msg) {
		super(msg);
	}

}
