package rmit.java.assignment.model;

import java.util.Random;

import rmit.java.assignment.controller.Driver;

/**
 *
 * Class Description:
 * 
 * @author: Niraj Bohra
 * @version 5.0
 * @classDescription Class that represents swimmers.
 * @Superclass: Athlete
 */
public class SuperAthlete extends Athlete {

	// Instance variables

	private String name;
	private String age;
	private String state;
	private String type = "Super Athletes";
	private float atime = getATime();

	// Getter and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getAtime() {
		return atime;
	}

	public void setAtime(float atime) {
		this.atime = atime;
	}

	@Override
	public float getATime() {

		return atime;
	}

	@Override
	public void setATime(float time) {

		this.atime = time;

	}

	@Override
	public String getAName() {

		return name;
	}

	private String uniqueID;
	private int points = 0;
	private String currentGame = "";

	/**
	 * CONSTRUCTOR
	 * 
	 * @param String
	 *            name: name of the superAthlete
	 * @param int
	 *            age: age of the superAthlete
	 * @param String
	 *            state: state of teh superAthlete
	 * @param int
	 *            uniquID: id of the superAthlete
	 * 
	 */
	public SuperAthlete(String name, String age, String state, String uniqueID) {
		super();
		this.name = name;
		this.age = age;
		this.state = state;
		this.uniqueID = uniqueID;
	}

	/**
	 * This method is used to set what is the current game that the super
	 * athlete is playing
	 * 
	 * @param int
	 *            currentGame: contains either 1,2 or 3 for swimming, cycling or
	 *            runnning respectively.
	 */
	public void setCurrentGame(String currentGame) {
		this.currentGame = currentGame;
	}

	/**
	 * This method is used to increment the points of the superAthlete.
	 * 
	 * @param int
	 *            newPoints are the number of points that the superAthlete
	 *            earned.
	 */
	@Override
	public void addPoints(int newPoints) {
		this.points += newPoints;
	}

	/**
	 * This method is used to calculate the superAthlete's game time.
	 * 
	 * @return float This returns the time taken for superAthlete's to complete
	 *         the race.
	 */
	@Override
	public float compete() {
		Random randomGenerator = new Random();
		System.out.println();
		switch (currentGame) {

		case Driver.SWIMMING:
			return Swimmer.MINIMUM_SWIM_TIME
					+ randomGenerator.nextFloat() * (Swimmer.MAXIMUM_SWIM_TIME - Swimmer.MINIMUM_SWIM_TIME);

		case Driver.RUNNING:

			return Sprinter.MINIMUM_SPRINT_TIME
					+ randomGenerator.nextFloat() * (Sprinter.MAXIMUM_SPRINT_TIME - Sprinter.MINIMUM_SPRINT_TIME);
		case Driver.CYCLING:

			return Cyclist.MINIMUM_CYCLING_TIME
					+ randomGenerator.nextFloat() * (Cyclist.MAXIMUM_CYCLING_TIME - Cyclist.MINIMUM_CYCLING_TIME);
		default:
			;
		}

		return 0;
	}

	/**
	 * This method is used to get uniqueID of the superAthlete.
	 * 
	 * @return int will return the superAthlete's unique ID.
	 */
	@Override
	public String getUniqueID() {
		return uniqueID;
	}

	/**
	 * This method is used return a string of the superAthlete's parameters for
	 * printing on console.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "name=" + name + ", age=" + age + ", state=" + state + ", ID=" + uniqueID + "type=" + type;
	}

	/**
	 * This method is used to get points of the superAthlete.
	 * 
	 * @return int will return number of points that the superAthlete has.
	 */

}
