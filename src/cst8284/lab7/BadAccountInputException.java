package cst8284.lab7;

public class BadAccountInputException extends java.lang.RuntimeException {
	
private static final long serialVersionUID = 1L;

	public BadAccountInputException() {
}
	public BadAccountInputException(String message) {
		super(message);
		message.equals("Bad Input: value entered is incorrect.");
	}
}