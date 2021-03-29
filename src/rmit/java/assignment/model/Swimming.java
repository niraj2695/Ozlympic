package rmit.java.assignment.model;

import java.util.ArrayList;
import java.util.HashMap;

import rmit.java.assignment.controller.Driver;
import rmit.java.assignment.database.ParticipantList;

/**
 *
 * 
 * 
 * @author: Niraj Bohra
 * @version 5.0
 * @classDescription: Class that represents Swimming games.
 * @Superclass: Game
 * 
 * 
 */
public class Swimming extends Game {

	private static final int MINIMUM_PARTICIPANT_COUNT = 5;
	private static final int MAXIMUM_PARTICIPANT_COUNT = 8;
	private String gameID;
	private Official official;
	private ArrayList<Athlete> contestants = new ArrayList<Athlete>();
	private HashMap<Athlete, Float> timings = new HashMap<Athlete, Float>();
	private Athlete userPredictedWinner;

	/**
	 * This method is used to get the user predicted winner for this game
	 * 
	 * @return Athlete userPredictedWinner returns user predicted winner
	 */
	public Athlete getUserPredictedWinner() {
		return userPredictedWinner;
	}

	/**
	 * This method is used to set the user predicted winner for this game
	 * 
	 * @param Athlete
	 *            userPredictedWinner
	 */
	public void setUserPredictedWinner(Athlete userPredictedWinner) {
		this.userPredictedWinner = userPredictedWinner;
	}

	/**
	 * CONSTRUCTOR
	 * 
	 * @param String
	 *            gameID: used to set swimming unique ID
	 * @param Official
	 *            official: used to assign an official to the swimming game
	 */
	public Swimming() {

	}

	/**
	 * This method is used to assign the athletes and the official to the
	 * swimming game.
	 * 
	 * @param ParticipantList
	 *            participantList This parameter contains the list of all the
	 *            participants
	 */
	public void assignContestants(ParticipantList participationList) {
		int random = (int) (MAXIMUM_PARTICIPANT_COUNT
				+ Math.random() * (MAXIMUM_PARTICIPANT_COUNT - MINIMUM_PARTICIPANT_COUNT + 1));

		ArrayList<Athlete> chosenSwimmers = new ArrayList<Athlete>();

		for (Athlete athlete : participationList.getSwimmers()) {
			chosenSwimmers.add(athlete);
		}
		for (Athlete athlete : participationList.getSuperAthletes()) {
			chosenSwimmers.add(athlete);
		}

		for (int participantCount = 1; participantCount <= random; participantCount++) {
			int removeID = (int) (Math.random() * chosenSwimmers.size());

			chosenSwimmers.remove(removeID);
		}
		this.contestants = chosenSwimmers;

		for (Athlete athlete : chosenSwimmers) {
			if (athlete instanceof SuperAthlete) {
				((SuperAthlete) athlete).setCurrentGame(Driver.SWIMMING);
			}
		}
	}

	public void setGameID(String ID) {
		this.gameID = ID;
	}

	/**
	 * This method is used to get the swimming Games unique ID
	 * 
	 * @return String returns gameID
	 */
	public String getGameID() {
		return gameID;
	}

	/**
	 * This method is used to get the contestants of the swimming game
	 * 
	 * @return ArrayList<Athlete> contestants
	 */
	public ArrayList<Athlete> getContestants() {
		return contestants;
	}

	/**
	 * This method is used to get the swimming Games official
	 * 
	 * @return Official official
	 */
	public Official getOfficial() {
		return official;
	}

	/**
	 * This method is used to record an athletes race time
	 * 
	 * @param float
	 *            timing : athletes game time
	 * @param Athlete
	 *            athlete : the athlete whose time needs to be recorded
	 */
	public void recordAthleteTime(float timing, Athlete athlete) {
		timings.put(athlete, timing);

	}

	/**
	 * This method is used to get the timings of all athletes
	 * 
	 * @return HashMap<Athlete, Float> timings: returns timings of all athletes
	 */
	public HashMap<Athlete, Float> getTimings() {
		return timings;
	}

	/**
	 * This method is used to set the contestants in the swimming game
	 * 
	 * @param ArrayList<Athlete>
	 *            contestants
	 */
	public void setContestants(ArrayList<Athlete> contestants) {
		this.contestants = contestants;
	}

	public void setOfficial(Official official) {
		this.official = official;
	}

	public void addContestant(Athlete contestant) {
		this.contestants.add(contestant);
	}
}
