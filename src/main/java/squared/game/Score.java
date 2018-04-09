package squared.game;

import java.io.Serializable;
import java.lang.Long;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class which instances hold scores and dates when this scores were set. Thanks
 * to this class serialization of data can be performed.
 * 
 * @author bartl_000
 *
 */
public class Score implements Serializable, Comparable<Score> {

	/**
	 * Used as a version control in a Serializable class.
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * Variable that holds current score.
	 */
	private long score;

	/**
	 * Variable that holds date when certain score was set.
	 */
	private String date;

	/**
	 * Contructor that creates object and sets date to actual time.
	 * 
	 * @param score
	 *            - score that has to be set
	 */
	public Score(long score) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy ");
		Date date = new Date();
		this.date = dateFormat.format(date);
		this.score = score;
	}

	/**
	 * Getting score.
	 * 
	 * @return {@link #score}
	 */
	public Long getScore() {
		return score;
	}

	/**
	 * Getting date binded with score
	 * 
	 * @return {@link #date}
	 */
	public String getDate() {
		return date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Score o) {
		return (Integer) ((o.getScore()).compareTo(getScore()));
	}

}