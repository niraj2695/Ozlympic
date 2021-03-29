package application;

/**
 * 
 * @author Carol Benita Saldanha
 * @version 5.0
 * @superclass Exception
 * @ClassDescription NoRefereeException displays custom made exception if the
 *                   official is not assigned
 *
 */
@SuppressWarnings("serial")
public class NoRefereeException extends Exception {
	// Empty constructor with custom message
	public NoRefereeException() {
		super("You need to assign at least one Referee for this game");
	}
}
