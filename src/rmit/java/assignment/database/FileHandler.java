package rmit.java.assignment.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * 
 * @author Carol Benita Saldanha
 * @version 5.0
 * @classDescription File Handler has read and write functions. It loads the
 *                   data from file into array objects an writes to file.
 *
 */
public class FileHandler {

	// File name
	private String participantList = "participants.txt";
	private String gameFile = "gameResult.txt";

	// Instance variables
	private ArrayList<String> participants = new ArrayList<String>();
	private ArrayList<String> games = new ArrayList<String>();
	@SuppressWarnings("unused")
	private final String SWIMMERS = "swimmers";
	@SuppressWarnings("unused")
	private final String CYCLIST = "cyclist";
	@SuppressWarnings("unused")
	private final String SPRINTERS = "sprinters";
	@SuppressWarnings("unused")
	private final String SUPER = "super";

	// Empty constructor
	public FileHandler() {

	}

	/**
	 * 
	 * @throws IOException
	 *             getsParticipantList from the file and stores into ArrayList
	 *             of strings
	 */
	public void getParticipantList() throws IOException {
		URL url = getClass().getResource(participantList);
		File f = new File(url.getPath());

		@SuppressWarnings("resource")
		BufferedReader b = new BufferedReader(new FileReader(f.getAbsolutePath()));

		String readLine = "";

		while ((readLine = b.readLine()) != null) {
			if (readLine.length() > 0) {
				participants.add(readLine);
			}
		}
	}

	// Getter for list
	public ArrayList<String> getParticipants() {
		return this.participants;
	}

	/**
	 * 
	 * @param string
	 *            It writes game data on file.
	 */
	public void writeFile(String string) {

		URL url = getClass().getResource(gameFile);
		FileWriter writer = null;
		

		try {
			
			writer = new FileWriter(url.getPath(), true);

			writer.write(string.toString() + "\n");

			writer.close();// flushes the stream.

		} catch (IOException e) {
			System.err.println("File cannot be created, or cannot be opened");
			System.exit(0);
		}
	}

	/**
	 * Gets all the games played into an array list.
	 * 
	 * @throws IOException
	 */
	public void getPlayedGames() throws IOException {
		URL url = getClass().getResource(gameFile);
		File f = new File(url.getPath());

		@SuppressWarnings("resource")
		BufferedReader b = new BufferedReader(new FileReader(f.getAbsolutePath()));

		String readLine = "";

		while ((readLine = b.readLine()) != null) {
			if (readLine.length() > 0) {
				games.add(readLine);
			}
		}

	}

	// Getter for games
	public ArrayList<String> getGames() {
		return this.games;
	}

	public void createFile() {
		
		System.out.println("Creating file....");
		URL url = getClass().getResource(gameFile);
		try {
			FileWriter writer = new FileWriter(url.getPath());
			System.out.println("File created : gameResults.txt");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
