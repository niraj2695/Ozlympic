package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import rmit.java.assignment.controller.Driver;
import rmit.java.assignment.model.Athlete;
import rmit.java.assignment.model.Game;
import rmit.java.assignment.model.Swimming;

/**
 *
 *  
 * 
 * @author : Niraj Bohra
 * @version : 5.0
 * @ClassDescription: The SwimmerAnimation class is where the animation for
 *                    swimming takes place.  The UI elements are linked via
 *                    SwimmingAnimation.fxml         
 */

public class SwimmerAnimationController implements Initializable {

	/**
	 *   These are the FXML variables that are set in SwimmingAnimation.fxml and
	 * linked to this controller.  
	 */

	@FXML
	private ImageView participant1;
	@FXML
	private ImageView participant2;

	@FXML
	private ImageView participant3;

	@FXML
	private ImageView participant4;

	@FXML
	private ImageView participant5;

	@FXML
	private ImageView participant6;

	@FXML
	private ImageView participant7;

	@FXML
	private ImageView participant8;

	@FXML
	private ImageView swimPath;

	@FXML
	private Label playerOne;

	@FXML
	private Label playerTwo;

	@FXML
	private Label playerThree;

	@FXML
	private Label playerFour;

	@FXML
	private Label playerFive;

	@FXML
	private Label playerSix;

	@FXML
	private Label playerSeven;

	@FXML
	private Label playerEight;

	private ArrayList<Label> names = new ArrayList<Label>();

	// TO AVOID MAGIC NUMBERS
	private final int FORWARD = 4;

	/**
	 *   This method is an Action event that takes place when the View Results
	 * button is clicked. The DisplayController class is invoked next.  
	 */

	@FXML
	void displayResults(ActionEvent event) throws Exception {
		Utility utility = new Utility();
		utility.displayUX(DisplayController.class, "application/Display.fxml", null);
	}

	/**
	 *   This method is where all the initialization for the controller takes
	 * place.  
	 */
	public void initialize(URL url, ResourceBundle rb) {
		/**
		 *   time and name are two Array Lists that are created which fetch the
		 * time and name of all the players.  
		 */
		ArrayList<Float> time = new ArrayList<Float>();
		ArrayList<String> name = new ArrayList<String>();
		Game game = Ozlympic.driver.getGame();
		game.setCurrentGame(Driver.SWIMMING);
		int size = game.getSwimmingGames().get(game.getSwimmingGames().size() - 1).getContestants().size();
		ArrayList<Swimming> games = Ozlympic.driver.getGame().getSwimmingGames();
		Swimming gameSwimming = games.get(games.size() - 1);

		for (int i = 0; i < game.getSwimmingGames().size(); i++) {
			for (Athlete athlete : gameSwimming.getTimings().keySet()) {
				time.add(gameSwimming.getTimings().get(athlete));

			}
		}

		for (Athlete athlete : gameSwimming.getTimings().keySet()) {

			name.add(athlete.getAName());

		}
		/**
		 *   This method is used to create parallel transition for Swimming
		 * Animation and then play all these animations.These animations will
		 * vary depending on the number of participants that the user will
		 * chose.  
		 */
		TranslateTransition transition1 = new TranslateTransition();
		TranslateTransition transition2 = new TranslateTransition();
		TranslateTransition transition3 = new TranslateTransition();
		TranslateTransition transition4 = new TranslateTransition();
		TranslateTransition transition5 = new TranslateTransition();
		TranslateTransition transition6 = new TranslateTransition();
		TranslateTransition transition7 = new TranslateTransition();
		TranslateTransition transition8 = new TranslateTransition();

		ArrayList<TranslateTransition> transitions = new ArrayList<TranslateTransition>();
		transitions.add(transition1);
		transitions.add(transition2);
		transitions.add(transition3);
		transitions.add(transition4);
		transitions.add(transition5);
		transitions.add(transition6);
		transitions.add(transition7);
		transitions.add(transition8);

		createArrayListOfLabels();
		ArrayList<ImageView> images = createArrayListOfImages();
		ParallelTransition parallel = new ParallelTransition();

		for (int index = 0; index < size; index++) {
			transitions.get(index).setNode(images.get(index));
			transitions.get(index).setDuration(Duration.seconds(time.get(index) / FORWARD));
			transitions.get(index).setToX(301);
			parallel.getChildren().add(transitions.get(index));
			names.get(index).setText(Integer.toString((index + 1)) + ". " + name.get(index));

		}
		for (int index = size; index < 8; index++) {
			images.get(index).setVisible(false);
		}
		parallel.play();

	}

	// List for animation getters
	private void createArrayListOfLabels() {
		names.add(playerOne);
		names.add(playerTwo);
		names.add(playerThree);
		names.add(playerFour);
		names.add(playerFive);
		names.add(playerSix);
		names.add(playerSeven);
		names.add(playerEight);
	}

	private ArrayList<ImageView> createArrayListOfImages() {
		ArrayList<ImageView> images = new ArrayList<ImageView>();
		images.add(participant1);
		images.add(participant2);
		images.add(participant3);
		images.add(participant4);
		images.add(participant5);
		images.add(participant6);
		images.add(participant7);
		images.add(participant8);
		return images;
	}

}