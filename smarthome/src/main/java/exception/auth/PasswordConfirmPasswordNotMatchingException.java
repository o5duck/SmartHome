package exception.auth;

public class PasswordConfirmPasswordNotMatchingException extends RuntimeException {
	public PasswordConfirmPasswordNotMatchingException(String message){
		super(message);
	}
}
