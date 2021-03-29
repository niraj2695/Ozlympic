package rmit.java.assignment.model;

import java.util.Random;

/**
 *
 * 
 * 
 * @author: Carol Saldanha Benita
 * @version 5.0
 * @classDescription: Class that represents swimmers.
 * @Superclass: Athlete
 */

public class Swimmer extends Athlete {

	public static final int MINIMUM_SWIM_TIME = 100;
	public static final int MAXIMUM_SWIM_TIME = 200;

	private String name;
	private String age;
	private String state;
	private String uniqueID;
	private int points;
	private String type = "Swimmers";
	private float atime = getATime();

	/**
	 * CONSTRUCTOR
	 * 
	 * @param String
	 *            name: name of the swimmer
	 * @param int
	 *            age: age of the swimmer
	 * @param String
	 *            state: state of the swimmer
	 * @param int
	 *            uniquID: id of the swimmer
	 * 
	 */
	public Swimmer(String name, String age, String state, String uniqueID) {
		super();
		this.name = name;
		this.age = age;
		this.state = state;
		this.uniqueID = uniqueID;
	}

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

	/**
	 * This method is used to get uniqueID of the swimmer.
	 * 
	 * @return int will return the swimmer's unique ID.
	 */
	@Override
	public String getUniqueID() {
		return uniqueID;
	}

	/**
	 * This method is used to increment the points of the swimmer.
	 * 
	 * @param int
	 *            newPoints are the number of points that the swimmer earned.
	 */
	@Override
	public void addPoints(int newPoints) {
		this.points += newPoints;
	}

	/**
	 * This method is used to calculate the swimmer's game time.
	 * 
	 * @return float This returns the time taken for swimmer's to complete the
	 *         race.
	 */
	@Override
	public float compete() {
		Random randomGenerator = new Random();

		return MINIMUM_SWIM_TIME + randomGenerator.nextFloat() * (MAXIMUM_SWIM_TIME - MINIMUM_SWIM_TIME);
	}

	/**
	 * This method is used to get points of the swimmer.
	 * 
	 * @return int will return number of points that the swimmer has.
	 */
	@Override
	public int getPoints() {
		return this.points;
	}

	/**
	 * This method is used return a string of the swimmer's parameters for
	 * printing on console.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "name=" + name + ", age=" + age + ", state=" + state + ", ID=" + uniqueID + "type=" + type;
	}

}
