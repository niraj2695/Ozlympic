package application;

//imports
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rmit.java.assignment.controller.Driver;
import rmit.java.assignment.model.Athlete;
import rmit.java.assignment.model.Game;

/**
 * 
 * @author Niraj Bohra
 * @version 5.0
 * @ClassDescription Display Controller is used to display the final results
 *                   after the games it has a table with id , name , age , state
 *                   , type , time of each athlete.
 *
 */
public class DisplayController implements Initializable {

	// Private instance variables for the FXML files.

	@FXML
	private TableView<Athlete> table;

	@FXML
	private TableColumn<Athlete, String> name;

	@FXML
	private TableColumn<Athlete, String> state;

	@FXML
	private TableColumn<Athlete, String> id;

	@FXML
	private TableColumn<Athlete, Float> time;

	@FXML
	private TableColumn<Athlete, String> type;

	@FXML
	private TableColumn<Athlete, String> age;

	@FXML
	private JFXButton home;

	@FXML
	private Label refereeID;

	@FXML
	private Label gameID;

	@FXML
	private Label refereeName;

	// List to get athlete objects to initialize on table.

	private ObservableList<Athlete> athletes;

	/**
	 * 
	 * @param event
	 * @throws Exception
	 *             homeMenu(event) is a method that takes the user to the home
	 *             page or the menu page.
	 * 
	 */
	@FXML
	void homeMenu(ActionEvent event) throws Exception {
		Utility utility = new Utility();
		utility.displayUX(OzlympicController.class, "application/Ozlympic.fxml", null);
	}

	public void initialize(URL url, ResourceBundle rb) {
		// Clear text on UI

		refereeID.setText("");

		// Based on the current game it retrieves the result and the official
		// details
		Game game = Ozlympic.driver.getGame();
		Driver driver = Ozlympic.driver;

		if (game.getCurrentGame().equals(Driver.SWIMMING)) {
			athletes = FXCollections.observableArrayList(driver.displaySwimmingResults());
			refereeID.setText(game.getSwimmingGames().get(game.getSwimmingGames().size() - 1).getOfficial()
					.getUniqueID().toString());
			gameID.setText(game.getSwimmingGames().get(game.getSwimmingGames().size() - 1).getGameID());
			refereeName
					.setText(game.getSwimmingGames().get(game.getSwimmingGames().size() - 1).getOfficial().getName());

		} else if (game.getCurrentGame().equals(Driver.CYCLING)) {
			athletes = FXCollections.observableArrayList(driver.displayCyclingResults());
			refereeID.setText(game.getCyclingGames().get(game.getCyclingGames().size() - 1).getOfficial().getUniqueID()
					.toString());
			gameID.setText(game.getCyclingGames().get(game.getCyclingGames().size() - 1).getGameID());
			refereeName.setText(game.getCyclingGames().get(game.getCyclingGames().size() - 1).getOfficial().getName());
		} else if (game.getCurrentGame().equals(Driver.RUNNING)) {
			athletes = FXCollections.observableArrayList(driver.displayRunningResults());
			refereeID.setText(game.getRunningGames().get(game.getRunningGames().size() - 1).getOfficial().getUniqueID()
					.toString());
			gameID.setText(game.getRunningGames().get(game.getRunningGames().size() - 1).getGameID());
			refereeName.setText(game.getRunningGames().get(game.getRunningGames().size() - 1).getOfficial().getName());
		}

		// Sets data to columns
		id.setCellValueFactory(new PropertyValueFactory<Athlete, String>("uniqueID"));
		name.setCellValueFactory(new PropertyValueFactory<Athlete, String>("name"));
		age.setCellValueFactory(new PropertyValueFactory<Athlete, String>("age"));
		state.setCellValueFactory(new PropertyValueFactory<Athlete, String>("state"));
		type.setCellValueFactory(new PropertyValueFactory<Athlete, String>("type"));
		time.setCellValueFactory(new PropertyValueFactory<Athlete, Float>("atime"));

		table.setItems(athletes);

	}
}