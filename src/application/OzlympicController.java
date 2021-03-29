package application;

// import 
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * 
 * @author Carol Benita Saldanha
 * @version 5.0
 * @classDescription It displays the main page of the UI from which all the
 *                   other functions are available.
 *
 */
public class OzlympicController {

	// Private instance variables for the FXML files.

	@FXML
	private JFXButton running;

	@FXML
	private Text displayText;

	@FXML
	private Button displayGames;

	@FXML
	private Text welcomeText;

	@FXML
	private Button cycling;

	@FXML
	private Button displayAthletePoints;

	// Create object of Utility

	Utility utility = new Utility();

	/**
	 * 
	 * @param event
	 * @throws Exception
	 *             clickRunning(event) is an event that runs UI for selecting
	 *             participants for running
	 */
	@FXML
	void clickRunning(ActionEvent event) throws Exception {
		utility.displayUX(RunningController.class, "application/Running.fxml", null);
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 *             clickCycling(event) is an event that runs UI for selecting
	 *             participants for cycling
	 */
	@FXML
	void clickCycling(ActionEvent event) throws Exception {
		utility.displayUX(CyclingController.class, "application/Cycling.fxml", null);
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 *             clickSwimming(event) is an event that runs UI for selecting
	 *             participants for swimming
	 */
	@FXML
	void clickSwimming(ActionEvent event) throws Exception {
		utility.displayUX(SwimmingController.class, "application/Swimming.fxml", null);
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 *             clickDisplay(event) is an event that runs UI for displaying
	 *             all games
	 */
	@FXML
	void clickDisplay(ActionEvent event) throws Exception {
		System.out.println("Opening played games..");
		utility.displayUX(DisplayAllGames.class, "application/DisplayAllGames.fxml", null);
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 *             displayAllAthletePoints(event)is an event that runs UI for
	 *             displaying all athlete points
	 */
	@FXML
	void displayAllAthletePoints(ActionEvent event) throws Exception {
		System.out.println("Opening display points..");
		utility.displayUX(DisplayPointsController.class, "application/DisplayAthletePoints.fxml", null);
	}

}
