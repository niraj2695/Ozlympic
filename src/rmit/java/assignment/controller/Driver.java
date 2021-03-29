package rmit.java.assignment.controller;

// imports 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import rmit.java.assignment.database.FileHandler;
import rmit.java.assignment.database.ParticipantList;
import rmit.java.assignment.model.Athlete;
import rmit.java.assignment.model.Cycling;
import rmit.java.assignment.model.Game;
import rmit.java.assignment.model.Official;
import rmit.java.assignment.model.Running;
import rmit.java.assignment.model.Swimming;

/**
 *
 * 
 * @author : Niraj Bohra
 * @version 5.0
 * @Class Description: The driver class is where user interaction occurs, and it
 *        uses other classes to manage the games.
 * 
 */

public class Driver {

	// Instance variables
	public static final String SWIMMING = "SWIMMING";
	public static final String CYCLING = "CYCLING";
	public static final String RUNNING = "RUNNING";
	private Game game;
	private ParticipantList participantList;
	@SuppressWarnings("unused")

	private Scanner scanInput = new Scanner(System.in);

	public ParticipantList getParticipantList() {

		return participantList;
		

	}

	public void setParticipantList(ParticipantList participantList) {

		this.participantList = participantList;

	}

	/**
	 * 
	 * CONSTRUCTOR
	 * 
	 * 
	 * 
	 * Intializes participantList, game
	 * 
	 */

	public Driver() {
		// Create objects
		System.out.println("Starting application : Ozlympic.java");
		System.out.println("Database connected : Ozlympic.db");
		participantList = new ParticipantList();

		FileHandler file = new FileHandler();
		file.createFile();
		game = new Game();

	}

	// Getter and setter for game

	public Game getGame() {

		return game;

	}

	public void setGame(Game game) {

		this.game = game;

	}

	/**
	 * 
	 * This method is used to display points of all the athletes in Ozlympics
	 * 
	 */

	public ArrayList<Athlete> displayPoints() {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayList<Athlete> displayList = new ArrayList();

		HashMap<Athlete, Integer> pointsTable = new HashMap<Athlete, Integer>();

		for (Athlete swimmer : participantList.getSwimmers()) {

			pointsTable.put(swimmer, swimmer.getPoints());

		}

		for (Athlete cyclist : participantList.getCyclists()) {

			pointsTable.put(cyclist, cyclist.getPoints());

		}

		for (Athlete sprinter : participantList.getSprinters()) {

			pointsTable.put(sprinter, sprinter.getPoints());

		}

		for (Athlete superAthlete : participantList.getSuperAthletes()) {

			pointsTable.put(superAthlete, superAthlete.getPoints());

		}

		int maxPoints;

		Athlete nextAthlete = null;
		for (Athlete athlete : pointsTable.keySet()) {

			displayList.add(athlete);
		}
		while (!pointsTable.isEmpty()) {

			maxPoints = 0;

			for (Athlete athlete : pointsTable.keySet()) {

				if (pointsTable.get(athlete) >= maxPoints) {

					maxPoints = pointsTable.get(athlete);

					nextAthlete = athlete;

				}

			}

			pointsTable.remove(nextAthlete);

		}

		return displayList;

	}

	/**
	 * 
	 * This method is used to display the results of all games in Ozlympics.
	 * 
	 */

	public ArrayList<Athlete> displaySwimmingResults() {

		return game.displaySwimmingResults();

	}

	public ArrayList<Athlete> displayRunningResults() {

		return game.displayRunningResults();

		// System.out.println();

	}

	public ArrayList<Athlete> displayCyclingResults() {

		return game.displayCyclingResults();

	}

	/**
	 * 
	 * startGame() is used to set participants and get the compete time for each
	 * athlete and retrieve official details
	 * 
	 */

	public void startGame() {

		ArrayList<Athlete> athletes = null;

		Official official;

		switch (game.getCurrentGame()) {

		case SWIMMING:

			Swimming swimming = getLastSwimmingGame();

			athletes = swimming.getContestants();

			for (Athlete athlete : athletes) {

				swimming.recordAthleteTime(athlete.compete(), athlete);

			}

			official = swimming.getOfficial();

			swimming.setContestants(official.computeWinners(swimming.getTimings()));

			break;

		case CYCLING:

			Cycling cycling = getLastCyclingGame();

			athletes = cycling.getContestants();

			for (Athlete athlete : athletes) {

				cycling.recordAthleteTime(athlete.compete(), athlete);

			}

			official = cycling.getOfficial();

			cycling.setContestants(official.computeWinners(cycling.getTimings()));

			break;

		case RUNNING:

			Running running = getLastRunningGame();

			athletes = running.getContestants();

			for (Athlete athlete : athletes) {

				running.recordAthleteTime(athlete.compete(), athlete);

			}

			official = running.getOfficial();

			running.setContestants(official.computeWinners(running.getTimings()));

			break;

		default:
			System.out.println("Please, select a game first.");

			;

		}

	}

	/**
	 * 
	 * This method returns the last running game selected
	 * 
	 * 
	 * 
	 * @return Running
	 * 
	 */

	private Running getLastRunningGame() {

		try {

			ArrayList<Running> runningGames = game.getRunningGames();

			return runningGames.get(runningGames.size() - 1);

		} catch (Exception e) {

			System.err.println(e.getMessage());

		}

		return null;

	}

	/**
	 * 
	 * This method returns the last cycling game selected
	 * 
	 * 
	 * 
	 * @return Cycling
	 * 
	 */

	private Cycling getLastCyclingGame() {

		try {

			ArrayList<Cycling> cyclingGames = game.getCyclingGames();

			return cyclingGames.get(cyclingGames.size() - 1);

		} catch (Exception e) {

			System.err.println(e.getMessage());

		}

		return null;

	}

	/**
	 * 
	 * This method returns the last swimming game selected
	 * 
	 * 
	 * 
	 * @return Swimming
	 * 
	 */

	private Swimming getLastSwimmingGame() {

		try {

			ArrayList<Swimming> swimmingGames = game.getSwimmingGames();

			return swimmingGames.get(swimmingGames.size() - 1);

		} catch (Exception e) {

			System.err.println(e.getMessage());

		}

		return null;

	}

}