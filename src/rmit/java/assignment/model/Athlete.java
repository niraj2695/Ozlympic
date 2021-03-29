package rmit.java.assignment.model;

/**
 * 
 * @author : Carol Benita Saldanha
 * @version 5.0
 * @classDescription Abstract Class of athletes.
 * @Superclass: Participants
 * @Subclass: Swimmer, Sprinter, SuperAthlete, Cyclist
 */
public abstract class Athlete extends Participants {
	/**
	 * This method is used to calculate the athletes game time.
	 * 
	 * @return float This returns the time taken for athlete to complete the
	 *         race.
	 */
	public abstract float compete();

	/**
	 * This method is used to increment the points of athlete.
	 * 
	 * @param int
	 *            newPoints are the number of points that the athlete earned.
	 */
	public abstract void addPoints(int newPoints);

	/**
	 * This method is used to get athletes points.
	 * 
	 * @return int will return number of points that the athlete has.
	 */
	public abstract int getPoints();

	/**
	 * This method is used to get uniqueID of the athlete.
	 * 
	 * @return int will return the athletes unique ID.
	 */
	/**
	 * @return uniqueID of an athlete
	 */
	public abstract String getUniqueID();

	/**
	 * get time for an athlete
	 * 
	 * @return time
	 */
	public abstract float getATime();

	/**
	 * sets time of an athlete
	 * 
	 * @param time
	 */
	public abstract void setATime(float time);

	/**
	 * Gets an athlete name
	 * 
	 * @return name
	 */
	public abstract String getAName();

}
