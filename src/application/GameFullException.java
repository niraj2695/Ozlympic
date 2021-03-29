package application;

/**
 * 
 * @author Niraj Bohra
 * @version 5.0
 * @superclass Exception
 * @ClassDescription GameFullException displays custom made exception if the
 *                   number of athletes are 8.
 *
 */
@SuppressWarnings("serial")
public class GameFullException extends Exception {
	// Empty constructor with custom message
	public GameFullException() {
		super("Cannot add more than 8 participants");
	}
}
