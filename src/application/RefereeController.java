package application;

// imports 
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import rmit.java.assignment.controller.Driver;
import rmit.java.assignment.database.ParticipantList;
import rmit.java.assignment.model.Game;

/**
 * 
 * @author Niraj Bohra
 * @version 5.0
 * @classDescription Referee Controller is where the user selects a referee for
 *                   the game.
 *
 */
public class RefereeController implements Initializable {

	// Private instance variables for the FXML files.
	@FXML
	private ListView<String> selectedReferee;

	@FXML
	private Button left;

	@FXML
	private Button right;

	@FXML
	private ListView<String> addReferee;

	@FXML
	private Button Next;

	// constants
	private static final int MAXIMUM_REFEREE = 1;

	// Instance variables
	private String selectedAddRefereeList = "";

	private String selectedRefereeList = "";

	// For Officials
	public static final ObservableList<String> officials = FXCollections.observableArrayList();

	// Driver Object
	Driver driver = Ozlympic.driver;
	// Get participation object
	ParticipantList get = driver.getParticipantList();
	// Get Current Game
	Game game = driver.getGame();

	/**
	 * 
	 * @param event
	 * @throws Exception
	 * @return void
	 * 
	 *         back( event) is an event that helps the user get back to home
	 *         screen.
	 */
	@FXML
	void back(ActionEvent event) throws Exception {
		// Get current game and load the UX
		if (game.getCurrentGame().equals(Driver.SWIMMING)) {
			Utility utility = new Utility();
			utility.displayUX(SwimmingController.class, "application/Swimming.fxml", null);
		} else if (game.getCurrentGame().equals(Driver.CYCLING)) {
			Utility utility = new Utility();
			utility.displayUX(CyclingController.class, "application/Cycling.fxml", null);
		} else {
			Utility utility = new Utility();
			utility.displayUX(RunningController.class, "application/Running.fxml", null);
		}
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 * @return void
	 * 
	 *         addReferee(event) is an event that helps the user to select a
	 *         referee for the game
	 * 
	 */

	@FXML
	void addReferee(ActionEvent event) {

		addReferee.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		System.out.println("adding referee");

	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 * @return void
	 * 
	 *         runGame( event) is a method that assigns offical and run the game
	 *         and compete the time
	 */

	@FXML
	void runGame(ActionEvent event) throws Exception {

		if (selectedReferee.getItems().size() + 1 == MAXIMUM_REFEREE) {
			try {
				throw new NoRefereeException();
			} catch (NoRefereeException e) {
				System.out.println(e.getMessage());
				// Show dialog box if no referee found
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error Message");
				alert.setHeaderText("Error : No Match Referee Exception");
				alert.setContentText(e.getMessage());
				alert.showAndWait();

			}
		} else if (game.getCurrentGame().equals("SWIMMING")) {

			for (int i = 0; i < get.getOfficials().size(); i++) {
				for (int j = 0; j < selectedReferee.getItems().size(); j++) {
					String item = selectedReferee.getItems().get(j);

					if (get.getOfficials().get(i).getUniqueID()
							.equals(item.substring(item.indexOf("ID=") + 3, item.indexOf("ID=") + 9))) {
						game.getSwimmingGames().get(game.getSwimmingGames().size() - 1)
								.setOfficial(get.getOfficials().get(i));
						System.out.println("Official Selected : " + get.getOfficials().get(i));
					}
				}
			}
			// Start game
			System.out.println("Game starting...");
			driver.startGame();
			System.out.println("Game ended");
			driver.displayPoints();
			// Show UI Screen of display.fxml
			Utility utility = new Utility();
			utility.displayUX(SwimmerAnimationController.class, "application/SwimmingAnimation.fxml", null);

		} else if (game.getCurrentGame().equals("RUNNING")) {
			for (int i = 0; i < get.getOfficials().size(); i++) {
				for (int j = 0; j < selectedReferee.getItems().size(); j++) {
					String item = selectedReferee.getItems().get(j);

					if (get.getOfficials().get(i).getUniqueID()
							.equals(item.substring(item.indexOf("ID=") + 3, item.indexOf("ID=") + 9))) {
						game.getRunningGames().get(game.getRunningGames().size() - 1)
								.setOfficial(get.getOfficials().get(i));
						System.out.println("Official Selected : " + get.getOfficials().get(i));
					}
				}
			}
			System.out.println("Game starting...");
			driver.startGame();
			System.out.println("Game ended");
			driver.displayPoints();
			Utility utility = new Utility();
			utility.displayUX(RunningAnimationController.class, "application/RunningAnimation.fxml", null);
		}

		else if (game.getCurrentGame().equals("CYCLING")) {
			for (int i = 0; i < get.getOfficials().size(); i++) {
				for (int j = 0; j < selectedReferee.getItems().size(); j++) {
					String item = selectedReferee.getItems().get(j);

					if (get.getOfficials().get(i).getUniqueID()
							.equals(item.substring(item.indexOf("ID=") + 3, item.indexOf("ID=") + 9))) {

						game.getCyclingGames().get(game.getCyclingGames().size() - 1)
								.setOfficial(get.getOfficials().get(i));
						System.out.println("Official Selected : " + get.getOfficials().get(i));
					}
				}

			}
			System.out.println("Game starting...");
			driver.startGame();
			System.out.println("Game ended");
			driver.displayPoints();
			Utility utility = new Utility();
			utility.displayUX(CyclingAnimationController.class, "application/CyclingAnimation.fxml", null);
		}

	}

	/**
	 * gets selected referee from the list view and set the referee for the
	 * game.
	 */
	public void initialize(URL url, ResourceBundle rb) {
		officials.clear();

		for (int i = 0; i < get.getOfficials().size(); i++) {
			officials.add(get.getOfficials().get(i).toString());
		}

		addReferee.setItems(officials);

		// When top and bottom button are clicked for adding participants
		right.setOnAction((ActionEvent event) -> {

			addReferee.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			if (addReferee.getSelectionModel().getSelectedItem() != null)

				selectedAddRefereeList = addReferee.getSelectionModel().getSelectedItem();
			if (selectedAddRefereeList.equals("")) {
				// Do Nothing
			} else if (selectedReferee.getItems().size() + 1 == MAXIMUM_REFEREE) {
				// Do Nothing
				addReferee.getItems().remove(selectedAddRefereeList);
				selectedReferee.getItems().addAll(selectedAddRefereeList);

			}

		});

		left.setOnAction((ActionEvent event) -> {
			selectedReferee.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			if (selectedReferee.getSelectionModel().getSelectedItem() != null)

				selectedRefereeList = selectedReferee.getSelectionModel().getSelectedItem();
			if (selectedRefereeList.equals("")) {
				// DO Nothing
			} else {
				boolean isValid = false;
				while (!isValid) {

					selectedReferee.getItems().remove(selectedRefereeList);
					if (addReferee.getItems().contains(selectedRefereeList)) {

					} else {
						addReferee.getItems().addAll(selectedRefereeList);
					}
					isValid = true;
				}

			}

		});

	}

}
