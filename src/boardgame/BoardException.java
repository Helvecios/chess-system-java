package boardgame;

public class BoardException extends RuntimeException{ //para ser uma exce��o opcinal a ser tratada
	private static final long serialVersionUID = 1L; 
	
	//construtor que recebe a mensagem
	public BoardException(String msg) {
		super(msg);
	}
}
