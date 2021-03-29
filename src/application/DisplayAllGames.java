package application;

// imports
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import rmit.java.assignment.database.FileHandler;
import rmit.java.assignment.database.ParticipantList;
import rmit.java.assignment.model.Athlete;

/**
 * 
 * @author Carol Benita Saldanha
 * @version 5.0
 * @ClassDescription Display all games displays all the games played by the user
 *                   in the format game type ( running , swimmming , cycling ) ,
 *                   official ID , official name , timestamp and the winners of
 *                   the game.
 * 
 *
 */
public class DisplayAllGames implements Initializable {

	// Private instance variables for the FXML files.

	@FXML
	private ListView<String> list;

	@FXML
	private ListView<String> list1;

	@FXML
	private Pagination pagination;

	@FXML
	private TableColumn<String, String> id;

	@FXML
	private TableColumn<Athlete, String> time;

	@FXML
	private TableView<Athlete> table;

	@FXML
	private TableColumn<Athlete, String> points;

	@FXML
	private JFXButton home;

	// File handler object to use functions
	FileHandler fileHandler = new FileHandler();

	// participation to get the list of players
	ParticipantList pList = new ParticipantList();

	// Observable list used to load data on the UI
	ObservableList<String> rAthletes = FXCollections.observableArrayList();
	ObservableList<String> sAthletes = FXCollections.observableArrayList();

	/**
	 * 
	 * @param event
	 * @throws Exception
	 *             homeMenu(event) is used to take the user to the main menu to
	 *             continue with other operations
	 */
	@FXML
	void homeMenu(ActionEvent event) throws Exception {
		Utility util = new Utility();
		util.displayUX(OzlympicController.class, "application/Ozlympic.fxml", null);
	}

	public void initialize(URL url, ResourceBundle rb) {
		// Handle exception if file not found
		try {
			fileHandler.getPlayedGames();

		} catch (IOException e) {
			// Throw if file not found
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText("Error : Database exception");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			System.exit(0);
		}

		// Call method if found
		displayByFile();
	}

	void displayByFile() {
		int len = 0;
		// Loop through all the games
		for (int i = 0; i < fileHandler.getGames().size(); i++) {
			// Check for all players
			if (fileHandler.getGames().get(i).substring(0, 2).equals("oz")) {

				len = fileHandler.getGames().get(i).lastIndexOf(",") + 1;

				// Check for players with a 0
				if (!fileHandler.getGames().get(i).substring(len).equals("0")) {

					len = fileHandler.getGames().get(i).indexOf(",");

					// Get ID of the winner
					String ID = fileHandler.getGames().get(i).substring(0, len);

					// Find from participant list
					for (int j = 0; j < pList.getSwimmers().size(); j++) {

						if (pList.getSwimmers().get(j).getUniqueID().equals(ID)) {
							rAthletes.add(ID + "\t" + pList.getSwimmers().get(j).getAName());
						}
					}
					for (int k = 0; k < pList.getSprinters().size(); k++) {

						if (pList.getSprinters().get(k).getUniqueID().equals(ID)) {
							rAthletes.add(ID + "\t" + pList.getSprinters().get(k).getAName());
						}

					}
					for (int l = 0; l < pList.getCyclists().size(); l++) {

						if (pList.getCyclists().get(l).getUniqueID().equals(ID)) {
							rAthletes.add(ID + "\t" + pList.getCyclists().get(l).getAName());
						}

					}
					for (int m = 0; m < pList.getSuperAthletes().size(); m++) {

						if (pList.getSuperAthletes().get(m).getUniqueID().equals(ID)) {

							rAthletes.add(ID + "\t" + pList.getSuperAthletes().get(m).getAName());
						}

					}

				}

			} else {

				int start = fileHandler.getGames().get(i).indexOf(",") + 1;
				// Get game type by slicing until length
				String gameType = fileHandler.getGames().get(i).substring(0, start - 1);

				// Substring till ","
				len = fileHandler.getGames().get(i).lastIndexOf(",");

				// find oID from string using substring
				String oID = fileHandler.getGames().get(i).substring(start, len);

				for (int n = 0; n < pList.getOfficials().size(); n++) {
					// Display official name if equals to ID
					if (pList.getOfficials().get(n).getUniqueID().equals(oID)) {

						rAthletes.add("---------------------------------------------------------------------"
								+ "\nGame Type: " + gameType + "\t \nReferee Name:  " + oID + "\t \nReferee Name: "
								+ pList.getOfficials().get(n).getName() + "\nTimestamp: "
								+ fileHandler.getGames().get(i).substring(10));
					}
				}
			}
		}
		// Set the list to the UI
		list.setItems(rAthletes);
	}
}