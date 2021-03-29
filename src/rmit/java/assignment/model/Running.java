package rmit.java.assignment.model;

import java.util.ArrayList;
import java.util.HashMap;

import rmit.java.assignment.controller.Driver;
import rmit.java.assignment.database.ParticipantList;

/**
 *
 * Class Description:
 * 
 * @author: Niraj Bohra
 * @version 5.0
 * @classDescription Class that represents Running games.
 * @Superclass: Game
 */
public class Running extends Game {

	private static final int MINIMUM_PARTICIPANT_COUNT = 5;
	private static final int MAXIMUM_PARTICIPANT_COUNT = 8;
	private Athlete userPredictedWinner;
	private ArrayList<Athlete> contestants = new ArrayList<Athlete>();
	private HashMap<Athlete, Float> timings = new HashMap<Athlete, Float>();
	private String gameID;
	private Official official;

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
	 *            gameID: used to set running unique ID
	 * @param Official
	 *            official: used to assign an official to the running game
	 */
	public Running() {

	}

	/**
	 * This method is used to assign the athletes and the official to the
	 * running game.
	 * 
	 * @param ParticipantList
	 *            participantList This parameter contains the list of all the
	 *            participants
	 */
	public void assignContestants(ParticipantList participationList) {
		int random = (int) (MAXIMUM_PARTICIPANT_COUNT
				+ Math.random() * (MAXIMUM_PARTICIPANT_COUNT - MINIMUM_PARTICIPANT_COUNT + 1));

		ArrayList<Athlete> chosenSprinters = new ArrayList<Athlete>();

		for (Athlete athlete : participationList.getSprinters()) {
			chosenSprinters.add(athlete);
		}
		for (Athlete athlete : participationList.getSuperAthletes()) {
			chosenSprinters.add(athlete);
		}

		for (int participantCount = 1; participantCount <= random; participantCount++) {
			int removeID = (int) (Math.random() * chosenSprinters.size());
			chosenSprinters.remove(removeID);
		}
		this.contestants = chosenSprinters;

		for (Athlete athlete : chosenSprinters) {
			if (athlete instanceof SuperAthlete) {
				((SuperAthlete) athlete).setCurrentGame(Driver.RUNNING);
			}
		}
	}

	/**
	 * This method is used to get the contestants of the running game
	 * 
	 * @return ArrayList<Athlete> contestants
	 */
	public ArrayList<Athlete> getContestants() {
		return contestants;
	}

	/**
	 * gets the GameID of the object
	 * 
	 * @param ID
	 */
	public void setGameID(String ID) {
		this.gameID = ID;
	}

	/**
	 * This method is used to get the running Games unique ID
	 * 
	 * @return String returns gameID
	 */
	public String getGameID() {
		return gameID;
	}

	/**
	 * This method is used to get the running Games official
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
	 * @return HashMap<Athlete, Float> timings : returns timings of all athletes
	 */
	public HashMap<Athlete, Float> getTimings() {
		return timings;
	}

	/**
	 * This method is used to set the contestants in the running game
	 * 
	 * @param ArrayList<Athlete>
	 *            contestants
	 */
	public void setContestants(ArrayList<Athlete> contestants) {
		this.contestants = contestants;
	}

	public void addContestant(Athlete contestant) {
		this.contestants.add(contestant);
	}

	public void setOfficial(Official official) {
		this.official = official;
	}

}
