package squared.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that is used to handle all actions connected with highscores. Mainly
 * used to reset scores, update or load score file.
 * 
 * @author bartl_000
 *
 */
public class HighscoreMenager {

	/**
	 * Array list holding scores from File.
	 */
	private ArrayList<Score> highscores;

	/**
	 * Stream using which date is saved to file.
	 */
	private ObjectOutputStream outputStream = null;

	/**
	 * Stream to which data from file is saved.
	 */
	private ObjectInputStream inputStream = null;

	/**
	 * Name of the file where scores are.
	 */
	private final static String FILE = "highscore.dat";

	/**
	 * Maximal amount of scores.
	 */
	private final static int SCORESAMOUNT = 10;

	/**
	 * Constructor which set highscores to a new arrayList.
	 */
	public HighscoreMenager() {
		highscores = new ArrayList<Score>();
	}

	/**
	 * Method that removes all scores from file and change them to 0.
	 * 
	 * @see #remove()
	 * @exception FileNotFoundException
	 *                - file with name saved in {@link HighscoreMenager#FILE}
	 *                doesnt exist
	 * @exception IOException
	 *                - stream was not handled properly
	 */
	public void resetFile() {
		for (int i = highscores.size(); i > 0; i--)
			highscores.remove(i - 1);
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(FILE));
			outputStream.writeObject(highscores);
		} catch (FileNotFoundException e) {
			System.out.println("[RESET]FILE NOT FOUND EXCEPTION: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[RESET]INPUT/OUTPUT EXCEPTION: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[RESET]INPUT/OUTPUT EXCEPTION: " + e.getMessage());
			}
		}
	}

	/**
	 * Loads file to inputStream.
	 * 
	 * @exception FileNotFoundException
	 *                - file with name saved in {@link HighscoreMenager#FILE}
	 *                doesnt exist
	 * @excpetion ClassNotFoundException - class was not found while
	 *            readingObject from stream
	 * @exception IOException
	 *                - stream was not handled properly
	 */
	private void loadFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(FILE));
			highscores = (ArrayList<Score>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("[LOAD]FILE NOT FOUND EXCEPTION: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[LOAD]INPUT/OUTPUT EXCEPTION: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[LOAD]CLASS NOT FOUND: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[LOAD]INPUT/OUTPUT EXCEPTION: " + e.getMessage());
			}
		}
	}

	/**
	 * Loads highscores to outputStream, saves 10 best scores to file.
	 * 
	 * @exception FileNotFoundException
	 *                - file with name saved in {@link HighscoreMenager#FILE}
	 *                doesnt exist
	 * @exception IOException
	 *                - stream was not handled properly
	 */
	private void updateFile() {
		try {
			while (highscores.size() > SCORESAMOUNT)
				highscores.remove(highscores.size() - 1);
			outputStream = new ObjectOutputStream(new FileOutputStream(FILE));
			outputStream.writeObject(highscores);
		} catch (FileNotFoundException e) {
			System.out.println("[UPDATE]FILE NOT FOUND EXCEPTION: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[UPDATE]INPUT/OUTPUT EXCEPTION: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[UPDATE]INPUT/OUTPUT EXCEPTION: " + e.getMessage());
			}
		}
	}

	/**
	 * Getting highscores from file after loading file.
	 * 
	 * @return {@link #highscores} - containing best actual scores
	 * @see #loadFile()
	 */
	public ArrayList<Score> getHighscore() {
		loadFile();
		return highscores;
	}

	/**
	 * Adds new highscore to highscores array. Sorts array.
	 * 
	 * @param score
	 *            - number we want to add
	 * @param date
	 *            - current date as string
	 */
	public void addHighscore(long score, String date) {
		loadFile();
		highscores.add(new Score(score));
		Collections.sort(highscores);
		for (int i = 0; i < highscores.size(); i++)
			updateFile();
	}

}
