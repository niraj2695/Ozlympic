package rmit.java.assignment.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import rmit.java.assignment.database.FileHandler;
import rmit.java.assignment.database.ParticipantList;
import rmit.java.assignment.database.SQLConnection;

/**
 *
 * 
 * 
 * @author: Carol Benita Saldanha
 * @version 5.0
 * @classDescription Description: Class that represents all games in ozlympics
 */

public class Game {
	// Instance variables
	private ArrayList<Integer> uniqueCyclingID = new ArrayList<Integer>();
	private ArrayList<Integer> uniqueRunningID = new ArrayList<Integer>();
	private ArrayList<Integer> uniqueSwimmingID = new ArrayList<Integer>();
	private ArrayList<Swimming> swimmingGames = new ArrayList<Swimming>();

	private ArrayList<Cycling> cyclingGames = new ArrayList<Cycling>();
	private ArrayList<Running> runningGames = new ArrayList<Running>();
	HashMap<Athlete, Float> swimmwerTimings = null;
	HashMap<Athlete, Float> cyclingTimings = null;
	HashMap<Athlete, Float> sprinterTimings = null;
	private String currentGame;

	private static final char CYCLING_ID = 'C';
	private static final char RUNNING_ID = 'R';
	private static final char SWIMMING_ID = 'S';
	private static final int OFFICIALS_COUNT = 8;

	private SQLConnection con = new SQLConnection();
	FileHandler set = new FileHandler();
	private Connection connection = null;

	/**
	 * CONSTRUCTOR
	 * 
	 * Adds starting elements in uniqueCyclingID, uniqueRunningID and
	 * uniqueSwimmingID
	 */
	public Game() {
		super();
		uniqueCyclingID.add(0);
		uniqueRunningID.add(0);
		uniqueSwimmingID.add(0);

	}

	public HashMap<Athlete, Float> getSwimmwerTimings() {
		return swimmwerTimings;
	}

	public void setSwimmwerTimings(HashMap<Athlete, Float> swimmwerTimings) {
		this.swimmwerTimings = swimmwerTimings;
	}

	/**
	 * This method is used to set the game that is currently selected
	 * 
	 * @param int
	 *            currentGame: contains either 1,2 or 3 for swimming, cycling or
	 *            runnning respectively.
	 */
	public void setCurrentGame(String currentGame) {
		this.currentGame = currentGame;
	}

	/**
	 * This method is used to get the game that is currently selected
	 * 
	 * @return int currentGame: contains either 1,2 or 3 for swimming, cycling
	 *         or runnning respectively.
	 */
	public String getCurrentGame() {
		return currentGame;
	}

	/**
	 * This method is used to get an array list of all swimming games selected
	 * 
	 * @return ArrayList<Swimming> swimmingGames
	 */
	public ArrayList<Swimming> getSwimmingGames() {
		return swimmingGames;
	}

	/**
	 * This method is used to get an array list of all cycling games selected
	 * 
	 * @return ArrayList<Cycling> cyclingGames
	 */
	public ArrayList<Cycling> getCyclingGames() {
		return cyclingGames;
	}

	/**
	 * This method is used to get an array list of all running games selected
	 * 
	 * @return ArrayList<Running> runningGames
	 */
	public ArrayList<Running> getRunningGames() {
		return runningGames;
	}

	/**
	 * This method is used to generate a unique ID for a new cycling game
	 * 
	 * @return String unique ID for a new cycling game
	 */
	private String generateUniqueCyclingID() {
		int maxUniqueID = Collections.max(uniqueCyclingID) + 1;
		uniqueCyclingID.add(maxUniqueID);
		if (maxUniqueID < 10)
			return CYCLING_ID + "0" + Integer.toString(maxUniqueID);
		else
			return CYCLING_ID + Integer.toString(maxUniqueID);

	}

	/**
	 * This method is used to generate a unique ID for a new running game
	 * 
	 * @return String unique ID for a new running game
	 */
	private String generateUniqueRunningID() {
		int maxUniqueID = Collections.max(uniqueRunningID) + 1;
		uniqueRunningID.add(maxUniqueID);
		if (maxUniqueID < 10)
			return RUNNING_ID + "0" + Integer.toString(maxUniqueID);
		else
			return RUNNING_ID + Integer.toString(maxUniqueID);

	}

	/**
	 * This method is used to generate a unique ID for a new swimming game
	 * 
	 * @return String unique ID for a new swimming game
	 */
	private String generateUniqueSwimmingID() {
		int maxUniqueID = Collections.max(uniqueSwimmingID) + 1;
		uniqueSwimmingID.add(maxUniqueID);
		if (maxUniqueID < 10)
			return SWIMMING_ID + "0" + Integer.toString(maxUniqueID);
		else
			return SWIMMING_ID + Integer.toString(maxUniqueID);

	}

	/**
	 * This method is used to print the participants of the game
	 * 
	 * @return int athleteCount number of athletes that are a part of the game
	 */
	public int showParticipants(ArrayList<Athlete> athletes) {
		int athleteCount = 0;
		for (Athlete athlete : athletes) {
			System.out.print(++athleteCount + ". ");
			System.out.println(athlete);
		}
		return athleteCount;
	}

	/**
	 * This method is used to get an official randomly from the participant
	 * list.
	 * 
	 * @param ParticipantList
	 *            participantList
	 * @return Official official
	 */
	public Official assignOfficial(ParticipantList participantList) {
		int random = (int) (Math.random() * OFFICIALS_COUNT);

		return participantList.getOfficials().get(random);
	}

	/**
	 * TIMESTAMP
	 */
	public Timestamp getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}

	/**
	 * This class is used to store data into file and database.
	 * 
	 * @return swimmers
	 * 
	 */
	public ArrayList<Athlete> displaySwimmingResults() {

		ArrayList<Athlete> swimmers = null;
		String sID = null;
		String oID = null;
		String aID = null;
		float times = 0;
		String insertResult = null;
		PreparedStatement stm = null;

		Swimming swimming = swimmingGames.get(swimmingGames.size() - 1);

		swimmers = swimming.getContestants();
		// System.out.println(swimmers);
		swimmwerTimings = swimming.getTimings();

		sID = generateUniqueSwimmingID();
		oID = swimming.getOfficial().getUniqueID();
		int count = 0;

		for (Athlete swimmer : swimmers)
			swimmer.setATime(swimmwerTimings.get(swimmer));

		swimming.setGameID(sID);
		set.writeFile(sID + "," + oID + "," + getTimeStamp() + "\n");

		try {

			connection = con.createConnection();

			for (Athlete swimmer : swimmers) {

				connection.setAutoCommit(false);

				times = swimmwerTimings.get(swimmer);
				aID = swimmer.getUniqueID();

				if (count == 0) {
					set.writeFile(aID + "," + Float.toString(times) + "," + "5");

				} else if (count == 1) {
					set.writeFile(aID + "," + Float.toString(times) + "," + "2");
					insertResult = "INSERT INTO results VALUES ('" + sID + "','" + oID + "','" + aID + "','"
							+ Float.toString(times) + "','" + 2 + "');";
				} else if (count == 2) {
					set.writeFile(aID + "," + Float.toString(times) + "," + "1");
					insertResult = "INSERT INTO results VALUES ('" + sID + "','" + oID + "','" + aID + "','"
							+ Float.toString(times) + "','" + 1 + "');";
				} else {
					set.writeFile(aID + "," + Float.toString(times) + "," + "0");
					insertResult = "INSERT INTO results VALUES ('" + sID + "','" + oID + "','" + aID + "','"
							+ Float.toString(times) + "','" + 0 + "');";
				}

				if (connection.isClosed() == true) {
					if (count == 0) {

						insertResult = "INSERT INTO results VALUES ('" + sID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 5 + "');";
					} else if (count == 1) {

						insertResult = "INSERT INTO results VALUES ('" + sID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 2 + "');";
					} else if (count == 2) {

						insertResult = "INSERT INTO results VALUES ('" + sID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 1 + "');";
					} else {

						insertResult = "INSERT INTO results VALUES ('" + sID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 0 + "');";
					}

					stm = connection.prepareStatement(insertResult);
					stm.executeUpdate();

				}

				count++;

			}
			connection.commit();

			set.writeFile("\n" + "\n");
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Error occured : class not found");

		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {

					System.err.println("Error : Exception occured");
				}
			}
		}

		return swimmers;
	}

	/**
	 * This class is used to store the sprinter data into file and database.
	 * 
	 * @return sprinters
	 * 
	 */
	public ArrayList<Athlete> displayRunningResults() {

		ArrayList<Athlete> sprinters = null;

		String rID = null;
		String oID = null;
		float times = 0;
		String aID = null;
		String insertResult = null;
		PreparedStatement stm = null;

		Running running = runningGames.get(runningGames.size() - 1);

		sprinters = running.getContestants();
		sprinterTimings = running.getTimings();

		rID = generateUniqueRunningID();
		oID = running.getOfficial().getUniqueID();
		running.setGameID(rID);
		int count = 0;

		set.writeFile(rID + "," + oID + "," + getTimeStamp() + "\n");

		try {
			connection = con.createConnection();

			for (Athlete sprinter : sprinters) {

				connection.setAutoCommit(false);

				sprinter.setATime(sprinterTimings.get(sprinter));
				times = sprinterTimings.get(sprinter);
				aID = sprinter.getUniqueID();

				if (count == 0) {
					set.writeFile(aID + "," + Float.toString(times) + "," + 5);

				} else if (count == 1) {
					set.writeFile(aID + "," + Float.toString(times) + "," + 2);

				} else if (count == 2) {
					set.writeFile(aID + "," + Float.toString(times) + "," + 1);

				} else {
					set.writeFile(aID + "," + Float.toString(times) + "," + 0);

				}
				// If connection is closed then
				if (connection.isClosed() == true) {
					if (count == 0) {

						insertResult = "INSERT INTO results VALUES ('" + rID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 5 + "');";
					} else if (count == 1) {

						insertResult = "INSERT INTO results VALUES ('" + rID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 2 + "');";
					} else if (count == 2) {

						insertResult = "INSERT INTO results VALUES ('" + rID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 1 + "');";
					} else {

						insertResult = "INSERT INTO results VALUES ('" + rID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 0 + "');";
					}

					stm = connection.prepareStatement(insertResult);
					stm.executeUpdate();
				}
				count++;

			}
			connection.commit();

			set.writeFile("\n" + "\n");

		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Error : Exception occured");
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					System.out.println("Error : Exception occured");
				}
			}

		}
		return sprinters;
	}

	/**
	 * This method is used to stores the reults of cycling games
	 * 
	 * @return cyclists
	 * 
	 */
	public ArrayList<Athlete> displayCyclingResults() {

		ArrayList<Athlete> cyclists = null;

		String cID = null;
		String oID = null;
		float times = 0;
		String aID = null;
		String insertResult = null;
		PreparedStatement stm = null;

		Cycling cycling = cyclingGames.get(cyclingGames.size() - 1);

		cyclists = cycling.getContestants();
		cyclingTimings = cycling.getTimings();

		cID = generateUniqueCyclingID();
		cycling.setGameID(cID);
		oID = cycling.getOfficial().getUniqueID();
		int count = 0;

		set.writeFile(cID + "," + oID + "," + getTimeStamp() + "\n");

		try {
			connection = con.createConnection();

			for (Athlete cyclist : cyclists) {

				connection.setAutoCommit(false);
				cyclist.setATime(cyclingTimings.get(cyclist));
				times = cyclingTimings.get(cyclist);
				aID = cyclist.getUniqueID();

				if (count == 0) {
					set.writeFile(aID + "," + Float.toString(times) + "," + 5);

				} else if (count == 1) {
					set.writeFile(aID + "," + Float.toString(times) + "," + 2);

				} else if (count == 2) {
					set.writeFile(aID + "," + Float.toString(times) + "," + 1);

				} else {
					set.writeFile(aID + "," + Float.toString(times) + "," + 0);
				}

				if (connection.isClosed() == true) {
					if (count == 0) {

						insertResult = "INSERT INTO results VALUES ('" + cID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 5 + "');";
					} else if (count == 1) {

						insertResult = "INSERT INTO results VALUES ('" + cID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 2 + "');";
					} else if (count == 2) {

						insertResult = "INSERT INTO results VALUES ('" + cID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 1 + "');";
					} else {

						insertResult = "INSERT INTO results VALUES ('" + cID + "','" + oID + "','" + aID + "','"
								+ Float.toString(times) + "','" + 0 + "');";
					}

					stm = connection.prepareStatement(insertResult);
					stm.executeUpdate();
				}
				count++;

			}

			connection.commit();

			set.writeFile("\n" + "\n");
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Error : Exception has occured.");
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {

					System.out.println("Exception has occured");
				}
			}
		}

		set.writeFile("\n" + "\n");

		return cyclists;
	}

}
