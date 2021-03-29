package rmit.java.assignment.model;

import java.util.Random;

/**
 *
 * @ClassDescription: Class that represents cyclists. 
 * @Superclass: Athlete
 * @author: Niraj Bohra
 * @version 5.0
 * @classDescription  Class that represents Cycling games. 
 * @Superclass: Game
 */
public class Cyclist extends Athlete {

	public static final int MAXIMUM_CYCLING_TIME = 800;
	public static final int MINIMUM_CYCLING_TIME = 500;

	private String name;
	private String age;
	private String state;
	private String uniqueID;
	private int points = 0;
	private String type = "Cyclist";
	private float atime = getATime();

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

	/**
	 * CONSTRUCTOR
	 * 
	 * @param String
	 *            name: name of the cyclist
	 * @param int
	 *            age: age of the cyclist
	 * @param String
	 *            state: state of teh cyclist
	 * @param int
	 *            uniquID: id of the cyclist
	 * 
	 */
	public Cyclist(String name, String age, String state, String uniqueID) {
		super();
		this.name = name;
		this.age = age;
		this.state = state;
		this.uniqueID = uniqueID;
	}

	/**
	 * This method is used to get points of the cyclist.
	 * 
	 * @return int will return number of points that the athlete has.
	 */
	@Override
	public int getPoints() {
		return points;
	}

	/**
	 * This method is used to increment the points of the cyclist.
	 * 
	 * @param int
	 *            newPoints are the number of points that the athlete earned.
	 */
	@Override
	public void addPoints(int newPoints) {
		this.points += newPoints;
	}

	/**
	 * This method is used return a string of the cyclist's parameters for
	 * printing on console.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "name=" + name + ", age=" + age + ", state=" + state + ", ID=" + uniqueID + "type=" + type;
	}

	/**
	 * This method is used to calculate the cyclist's game time.
	 * 
	 * @return float This returns the time taken for cyclist to complete the
	 *         race.
	 */
	@Override
	public float compete() {

		Random randomGenerator = new Random();

		return MINIMUM_CYCLING_TIME + randomGenerator.nextFloat() * (MAXIMUM_CYCLING_TIME - MINIMUM_CYCLING_TIME);

	}

	/**
	 * This method is used to get uniqueID of the cyclist.
	 * 
	 * @return int will return the cyclist's unique ID.
	 */
	@Override
	public String getUniqueID() {
		return uniqueID;
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

}
