package response;

public class ExceptionResponse {
	private String message;
	private int exceptionCode;
	
	public ExceptionResponse(){}
	public ExceptionResponse(int code, String msg){
		this.exceptionCode = code;
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	
}
