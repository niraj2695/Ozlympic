
package application;

// imports
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
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
import rmit.java.assignment.model.Swimming;
import rmit.java.assignment.model.SuperAthlete;

/**
 *
 * 
 * @author : Carol Benita Saldanha
 * @version : 5.0 
 * @ClassDescription: The SwimmerAnimation class is where the
 *          animation for swimming takes place. The UI elements are linked via
 *          SwimmingAnimation.fxml
 * 
 * 
 * 
 * 
 */
public class SwimmingController implements Initializable {

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

	@FXML
	private JFXDialog except;

	private static final String TYPE_1 = "Swimmers";

	private static final String TYPE_2 = "Super Athletes";

	private static final int MAXIMUM_PARTICIPANTS = 8;

	private static final int MINIMUM_PARTICIPANTS = 4;

	private String selectedAddParticipantList = "";

	public String selectedParticipantList = "";
	// private Ozlympic ozlympic = new Ozlympic();

	// For Athletes
	public static final ObservableList<String> athletes = FXCollections.observableArrayList();

	// For Officials
	public static final ObservableList<String> officials = FXCollections.observableArrayList();

	Driver driver = Ozlympic.driver;
	ParticipantList get = driver.getParticipantList();

	Game get1 = driver.getGame();

	Swimming swimming = new Swimming();

	@FXML
	void addParticipants(ActionEvent event) {

		addParticipants.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	@FXML
	void back(ActionEvent event) throws Exception {

		Utility utility = new Utility();
		utility.displayUX(OzlympicController.class, "application/Ozlympic.fxml", null);

	}

	@SuppressWarnings("static-access")
	@FXML
	void nextPage(ActionEvent event) throws Exception {
		if (selectedParticipants.getItems().size() < MINIMUM_PARTICIPANTS) {
			try {
				throw new TooFewAthleteException();
			} catch (TooFewAthleteException e) {
				System.out.println(e.getMessage());
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText("Error: To Few Athlete Exception ");
				alert.setContentText(e.getMessage());
				alert.showAndWait();

			}
		}

		else {

			System.out.println(selectedParticipants.getItems());
			swimming.setCurrentGame(Driver.SWIMMING);

			System.out.println("current game " + swimming.getCurrentGame());

			for (int i = 0; i < get.getSwimmers().size(); i++) {
				for (int j = 0; j < selectedParticipants.getItems().size(); j++) {
					String item = selectedParticipants.getItems().get(j);
					if (get.getSwimmers().get(i).getUniqueID()
							.equals(item.substring(item.indexOf("ID=") + 3, item.indexOf("ID=") + 9))) {

						swimming.addContestant(get.getSwimmers().get(i));
					}

				}

			}

			for (int i = 0; i < get.getSuperAthletes().size(); i++) {
				for (int j = 0; j < selectedParticipants.getItems().size(); j++) {
					String item = selectedParticipants.getItems().get(j);
					if (get.getSuperAthletes().get(i).getUniqueID()
							.equals(item.substring(item.indexOf("ID=") + 3, item.indexOf("ID=") + 9))) {
						((SuperAthlete) (get.getSuperAthletes().get(i))).setCurrentGame(Ozlympic.driver.SWIMMING);
						swimming.addContestant(get.getSuperAthletes().get(i));
					}

				}

			}

			System.out.println("Participants Selected....");
			get1.getSwimmingGames().add(swimming);
			Ozlympic.driver.getGame().setCurrentGame(Driver.SWIMMING);
			Utility utility = new Utility();
			utility.displayUX(RefereeController.class, "application/Referee.fxml", null);
		}

	}

	public void initialize(URL url, ResourceBundle rb) {

		System.out.print("initializing ");
		System.out.println(Driver.SWIMMING);
		addParticipants.getItems().clear();
		athletes.clear();
		selectedParticipants.getItems().clear();

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

						type = selectedAddParticipantList.substring(selectedAddParticipantList.indexOf("type=") + 5,
								selectedAddParticipantList.length());

						System.out.println(selectedAddParticipantList);
						if (!type.equals(TYPE_1) && !type.equals(TYPE_2)) {
							try {
								throw new WrongTypeException();
							} catch (WrongTypeException e) {
								System.out.println(type);

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
								// Do nothing
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

					if (selectedParticipants.getSelectionModel().getSelectedItem() != null) {
						System.out.println(selectedParticipantList);
						selectedParticipants.getItems().remove(selectedParticipantList);
					}
					if (addParticipants.getItems().contains(selectedParticipantList)) {
						// Do nothing
					} else {
						addParticipants.getItems().addAll(selectedParticipantList);

					}
					isValid = true;
				}

			}

		});

	}

}