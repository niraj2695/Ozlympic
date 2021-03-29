package application;

// imports 
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import rmit.java.assignment.controller.Driver;
import rmit.java.assignment.database.ParticipantList;
import rmit.java.assignment.model.Cycling;
import rmit.java.assignment.model.Game;
import rmit.java.assignment.model.SuperAthlete;

/**
 * 
 * @author Niraj Bohra
 * @version 5.0
 * @ClassDescrption Cycling Controller is a class that controls all data
 *                  manipulation on Cycling.fxml
 * 
 *
 */
public class CyclingController implements Initializable {

	// Defined constants values

	private static final String TYPE_1 = "Cyclist";

	private static final String TYPE_2 = "Super Athletes";

	private static final int MAXIMUM_PARTICIPANTS = 8;

	private static final int MINIMUM_PARTICIPANTS = 4;

	// Private instance variables for the FXML files.

	@FXML
	private ListView<String> selectedParticipants;

	@FXML
	private Button left;

	@FXML
	private Button right;

	@FXML
	private ListView<String> addParticipants;

	@FXML
	private JFXButton Next;

	@FXML
	private JFXButton back;

	// Instance variables

	private String selectedAddParticipantList = "";

	private String selectedParticipantList = "";

	// For Athletes
	public static final ObservableList<String> athletes = FXCollections.observableArrayList();

	// Get object of main class
	Driver driver = Ozlympic.driver;

	// Object of participant list to get participants
	ParticipantList get = driver.getParticipantList();

	// Gets current game
	Game get1 = driver.getGame();

	// Get object of cycling
	Cycling cycling = new Cycling();

	/**
	 * 
	 * @param event
	 * @return void addParticipants(event) is a function that adds events from
	 *         list of selected participants
	 * 
	 */
	@FXML
	void addParticipants(ActionEvent event) {

		addParticipants.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

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

		Utility utility = new Utility();
		utility.displayUX(OzlympicController.class, "application/Ozlympic.fxml", null);

	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 * @return void
	 * 
	 *         nextPage(event) is used to check for exception and if not found
	 *         then the user can proceed with his game.
	 */
	@FXML
	void nextPage(ActionEvent event) throws Exception {
		if (selectedParticipants.getItems().size() < MINIMUM_PARTICIPANTS) {
			try {
				throw new TooFewAthleteException();
			} catch (TooFewAthleteException e) {
				System.out.println(e.getMessage());
				// Throw dialog box
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText("Error: To Few Athlete Exception ");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		}

		else {

			cycling.setCurrentGame(Driver.CYCLING);

			for (int i = 0; i < get.getCyclists().size(); i++) {
				for (int j = 0; j < selectedParticipants.getItems().size(); j++) {
					String item = selectedParticipants.getItems().get(j);
					if (get.getCyclists().get(i).getUniqueID()
							.equals(item.substring(item.indexOf("ID=") + 3, item.indexOf("ID=") + 9))) {

						cycling.addContestant(get.getCyclists().get(i));
					}
				}
			}

			for (int i = 0; i < get.getSuperAthletes().size(); i++) {
				for (int j = 0; j < selectedParticipants.getItems().size(); j++) {
					String item = selectedParticipants.getItems().get(j);
					if (get.getSuperAthletes().get(i).getUniqueID()
							.equals(item.substring(item.indexOf("ID=") + 3, item.indexOf("ID=") + 9))) {
						((SuperAthlete) (get.getSuperAthletes().get(i))).setCurrentGame(Driver.CYCLING);
						cycling.addContestant(get.getSuperAthletes().get(i));
					}

				}

			}
			// Adds game to arrayList of games
			System.out.println("Participants Selected....");
			get1.getCyclingGames().add(cycling);
			// set current game
			Ozlympic.driver.getGame().setCurrentGame(Driver.CYCLING);
			Utility utility = new Utility();
			utility.displayUX(RefereeController.class, "application/Referee.fxml", null);
		}
	}

	/**
	 * It initializes all the values in the controller
	 */
	public void initialize(URL url, ResourceBundle rb) {
		System.out.print("initializing ");
		System.out.println(Driver.CYCLING);
		athletes.clear();

		for (int i = 0; i < get.getSwimmers().size(); i++) {
			athletes.add(get.getSwimmers().get(i).toString());
		}
		for (int i = 0; i < get.getCyclists().size(); i++) {
			athletes.add(get.getCyclists().get(i).toString());
		}
		for (int i = 0; i < get.getSprinters().size(); i++) {
			athletes.add(get.getSprinters().get(i).toString());
		}
		for (int i = 0; i < get.getSuperAthletes().size(); i++) {
			athletes.add(get.getSuperAthletes().get(i).toString());
		}

		addParticipants.setItems(athletes);

		// When top and bottom buttons are clicked

		right.setOnAction((ActionEvent event) -> {

			addParticipants.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			if (addParticipants.getSelectionModel().getSelectedItem() != null)
				selectedAddParticipantList = addParticipants.getSelectionModel().getSelectedItem();

			if (selectedAddParticipantList.equals("")) {
				// Do nothing
			} else {
				boolean isValid = false;
				String type = "";
				while (!isValid) {
					if (selectedParticipants.getItems().size() + 1 <= MAXIMUM_PARTICIPANTS) {

						addParticipants.getSelectionModel().clearSelection();
						System.out.println(selectedAddParticipantList);
						type = selectedAddParticipantList.substring(selectedAddParticipantList.indexOf("type=") + 5,
								selectedAddParticipantList.length());
						if (!type.equals(TYPE_1) && !type.equals(TYPE_2)) {
							try {
								throw new WrongTypeException();
							} catch (WrongTypeException e) {
								// Trhow dialog for wrong type
								System.out.println(e.getMessage());
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Error Message");
								alert.setHeaderText("Error : Wrong Type Exception");
								alert.setContentText(e.getMessage());
								alert.showAndWait();

							}

						} else {

							addParticipants.getItems().remove(selectedAddParticipantList);
							if (selectedParticipants.getItems().contains(selectedAddParticipantList)) {

							} else {
								selectedParticipants.getItems().addAll(selectedAddParticipantList);
							}
						}
					}
					isValid = true;

				}

				if (selectedParticipants.getItems().size() == MAXIMUM_PARTICIPANTS) {

					try {
						throw new GameFullException();
					} catch (GameFullException e) {
						// GameFullException to be thrown as dialog box
						System.out.println(e.getMessage());
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Error Message");
						alert.setHeaderText("Error : Game Full Exception");
						alert.setContentText(e.getMessage());
						alert.showAndWait();

					}
					isValid = true;

				}
			}

		});

		left.setOnAction((ActionEvent event) -> {
			selectedParticipants.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			if (selectedParticipants.getSelectionModel().getSelectedItem() != null)

				selectedParticipantList = selectedParticipants.getSelectionModel().getSelectedItem();
			if (selectedParticipantList.equals("")) {
				// Do nothing
			} else {
				boolean isValid = false;
				while (!isValid) {

					if (selectedParticipantList != null) {

						selectedParticipants.getItems().remove(selectedParticipantList);
						if (addParticipants.getItems().contains(selectedParticipantList)) {

						} else {
							addParticipants.getItems().addAll(selectedParticipantList);
						}
						isValid = true;
					}

				}
			}

		});

	}

}
