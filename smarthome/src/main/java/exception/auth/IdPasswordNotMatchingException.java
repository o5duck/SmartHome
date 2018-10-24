package exception.auth;

public class IdPasswordNotMatchingException extends RuntimeException{
	public IdPasswordNotMatchingException(String message){
		super(message);
	}
}
