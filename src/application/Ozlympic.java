package application;

// imports 
import javafx.application.Application;
import javafx.stage.Stage;
import rmit.java.assignment.controller.Driver;

/**
 * 
 * @author Niraj Bohra
 * @version 5.0
 * @superclass Application
 * @classDescription Main class
 *
 */
public class Ozlympic extends Application {
	public static Driver driver = new Driver();

	@Override
	public void start(Stage primaryStage) throws Exception {
		// create object of utility and displayUX(parameters)
		Utility utility = new Utility();
		utility.displayUX(OzlympicController.class, "application/Ozlympic.fxml", null);
	}

	public static void main(String[] args) {
		// launch application
		launch(args);

	}
}
