package application;

/**
 * 
 * @author Carol Benita Saldanha
 * @version 5.0
 * @superclass Exception
 * @ClassDescription WrongTypeException displays custom made exception if the
 *                   athlete is not playing the game he belongs too.
 * 
 *                   For example : If user selects a swimmer for a runnng game.
 *
 */
@SuppressWarnings("serial")
public class WrongTypeException extends Exception {

	public WrongTypeException() {
		super("Athlete assigned to a wrong game");
	}
}
