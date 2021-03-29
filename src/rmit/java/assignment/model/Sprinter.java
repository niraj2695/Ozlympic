package rmit.java.assignment.model;

import java.util.Random;

/**
 *
 * 
 * 
 * @author: Carol Benita Saldanha
 * @version 5.0
 * @classDescription Class that represents sprinters.
 * @Superclass: Athlete
 */

public class Sprinter extends Athlete {

	// Constants
	public static final int MINIMUM_SPRINT_TIME = 10;
	public static final int MAXIMUM_SPRINT_TIME = 20;

	// Instance variables
	private String name;
	private String age;
	private String state;
	private String uniqueID;
	private int points = 0;
	private String type = "Sprinters";
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
		// TODO Auto-generated method stub
		return atime;
	}

	/**
	 * This method is used to increment the points of the sprinter.
	 * 
	 * @param int
	 *            newPoints are the number of points that the sprinter earned.
	 */
	@Override
	public void addPoints(int newPoints) {
		this.points += newPoints;
	}

	/**
	 * CONSTRUCTOR
	 * 
	 * @param String
	 *            name: name of the sprinter
	 * @param int
	 *            age: age of the sprinter
	 * @param String
	 *            state: state of teh sprinter
	 * @param int
	 *            uniquID: id of the sprinter
	 * 
	 */
	public Sprinter(String name, String age, String state, String uniqueID) {
		super();
		this.name = name;
		this.age = age;
		this.state = state;
		this.uniqueID = uniqueID;
	}

	/**
	 * This method is used to calculate the sprinter's game time.
	 * 
	 * @return float This returns the time taken for sprinter to complete the
	 *         race.
	 */
	@Override
	public float compete() {

		Random randomGenerator = new Random();
		return MINIMUM_SPRINT_TIME + randomGenerator.nextFloat() * (MAXIMUM_SPRINT_TIME - MINIMUM_SPRINT_TIME);

	}

	/**
	 * This method is used to get points of the sprinter.
	 * 
	 * @return int will return number of points that the sprinter has.
	 */
	@Override
	public int getPoints() {
		return this.points;
	}

	/**
	 * This method is used to get uniqueID of the sprinter.
	 * 
	 * @return int will return the sprinter's unique ID.
	 */
	@Override
	public String getUniqueID() {
		return uniqueID;
	}

	/**
	 * This method is used return a string of the sprinter's parameters for
	 * printing on console.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "name=" + name + ", age=" + age + ", state=" + state + ", uniqueID=" + uniqueID + "type=" + type;
	}

	@Override
	public void setATime(float time) {
		// TODO Auto-generated method stub
		this.atime = time;

	}

	@Override
	public String getAName() {
		// TODO Auto-generated method stub
		return name;
	}

}
