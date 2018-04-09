package squared.game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.util.Pair;
import squared.game.View.Square;

/**
 * Model class contains all useful variable that can be use during game.
 * Contains all algorithms used in game. Morover there are methods that are
 * responsible for handling square's actions.
 * 
 * @author bartl_000
 *
 */
public class Model {

	/**
	 * Maximum number of available colors.
	 */
	private static final int MAXCOLORS = 10;

	/**
	 * Current amount of colors chosen by a player.
	 */
	private static int colorAmount = 5;

	/**
	 * Array holdings all shown squares.
	 */
	private Square[][] squareArray;

	/**
	 * Variable holding first clicked square.
	 */
	private Square firstClicked;

	/**
	 * Variable holding second clicked square.
	 */
	private Square secondClicked;

	/**
	 * Array used to find way between squares.
	 */
	private int[][] pathArray;

	/**
	 * Array of coordinates of all grey squares.
	 */
	private ArrayList<Pair<Integer, Integer>> greySquaresArray;

	/**
	 * Variable holding current score.
	 */
	private LongProperty score = new SimpleLongProperty(0);

	/**
	 * Used to differentiate colors with unique name and Id.
	 * 
	 * @author bartl_000
	 *
	 */
	public static enum Color {
		GREY(0), BLUE(1), GREEN(2), RED(3), ORANGE(4), YELLOW(5), PURPLE(6), PINK(7), AQUAMARINE(8), LAPIZ(9);
		private int id;

		/**
		 * Constructor sets id of the color.
		 * 
		 * @param id
		 *            of certain color
		 */
		private Color(int id) {
			this.id = id;
		}

		/**
		 * Getting color name with given id
		 * 
		 * @param id
		 *            of the color which name we look for
		 * @return color name if color with this id exists, null when color was
		 *         not found
		 */
		public static Color getColor(int id) {
			for (Color color : Color.values()) {
				if (color.id == id) {
					return color;
				}
			}
			return null;
		}
	}

	/**
	 * Model constructor resets GreySquaresArray.
	 * 
	 * @see {@link #resetGreySquaresArray()
	 */
	public Model() {
		resetGreySquaresArray();
	}

	/**
	 * Sets how many colors there will be in a game.
	 * 
	 * @param colorAmount
	 *            it can be a number between 0 and {@link #MAXCOLORS}
	 */
	public void setColorAmount(int colorAmount) {
		if (colorAmount > 0 && colorAmount <= MAXCOLORS)
			Model.colorAmount = colorAmount;
	}

	/**
	 * Gets current score.
	 * 
	 * @return current {@link #score}
	 */
	public LongProperty getScore() {
		return score;
	}

	/**
	 * Sets score to a given value.
	 * 
	 * @param value
	 *            long type value, should be positive
	 */
	public void setScore(long value) {
		this.score.set(value);
	}

	/**
	 * Creates new GreySquareArray which has maximal size and holds all squares.
	 */
	public void resetGreySquaresArray() {
		greySquaresArray = new ArrayList<Pair<Integer, Integer>>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				greySquaresArray.add(new Pair<Integer, Integer>(i, j));
			}
		}
	}

	/**
	 * Gets squareArray.
	 * 
	 * @return {@link #squareArray}, if it is empty returns null
	 */
	public Square[][] getSquareArray() {
		return squareArray;
	}

	/**
	 * Sets {@link #firstClicked} to a value given as a parameter.
	 * 
	 * @param firstClicked
	 */
	public void setFirstClicked(Square firstClicked) {
		this.firstClicked = firstClicked;
	}

	/**
	 * Sets {@link #squareArray} to a value given as a parameter and adds 3
	 * random squares to the board.
	 * 
	 * @param square
	 * @see {@link #addRandomSquares(int)}
	 */
	public void setSquareArray(Square[][] square) {
		squareArray = square;
		addRandomSquares(3);
	}

	/**
	 * Sets {@link #firstClicked} or {@link #secondClicked}. If secondClicked
	 * then looks for path.If path was found move squares and looks for lines.
	 * If path was not found adds 3 random squares.
	 * 
	 * @param handledSquare
	 *            square that was clicked
	 * @see {@link #findPath(Integer, Integer)}
	 * @see {@link #moveSquare(Square, Square)}
	 * @see {@link #lookForStraightLines(int, int)}
	 * @see {@link #lookForDiagonalLines(int, int, Color)}
	 * @see {@link #addRandomSquares(int)}
	 */
	public void handleClick(Square handledSquare) {
		if (firstClicked == null) {
			if (handledSquare.getColor() == Color.GREY)
				return;
			firstClicked = handledSquare;
			firstClicked.setClicked();
		} else {
			if (handledSquare.equals(firstClicked)) {
				firstClicked.setUnclicked();
				firstClicked = null;
				return;
			}
			if (handledSquare.getColor() != Color.GREY)
				return;
			Color color = firstClicked.getColor();
			pathArray = new int[9][9];
			secondClicked = handledSquare;
			if (findPath(firstClicked.getIndex().getKey(), firstClicked.getIndex().getValue())) {
				moveSquare(firstClicked, secondClicked);
				boolean straightLine = lookForStraightLines(secondClicked.getIndex().getKey(),
						secondClicked.getIndex().getValue());
				boolean diagonalLine = lookForDiagonalLines(secondClicked.getIndex().getKey(),
						secondClicked.getIndex().getValue(), color);
				if (!straightLine && !diagonalLine)
					addRandomSquares(3);
				firstClicked = null;
			}
			secondClicked = null;
		}
	}

	/**
	 * Recursive method looks for path between {@link #firstClicked} and
	 * {@link #secondClicked}.
	 * 
	 * @param x
	 *            with first use its x coordinate of firstClicked
	 * @param y
	 *            with first use its x coordinate of secondClicked
	 * @return true if path was found, false otherwise
	 */
	private boolean findPath(Integer x, Integer y) {
		if (x.equals(secondClicked.getIndex().getKey()) && y.equals(secondClicked.getIndex().getValue()))
			return true;
		if (x < 0 || x > 8 || y < 0 || y > 8 || pathArray[x][y] == -1)
			return false;
		pathArray[x][y] = -1;
		if (squareArray[x][y].getColor() != Color.GREY
				&& !firstClicked.getIndex().equals(new Pair<Integer, Integer>(x, y)))
			return false;
		if (findPath(x - 1, y) || findPath(x, y + 1) || findPath(x + 1, y) || findPath(x, y - 1))
			return true;
		return false;
	}

	/**
	 * Moves colors between first and second clicked squares.
	 * 
	 * @param firstClicked
	 *            first clicked square
	 * @param secondClicked
	 *            second clicked square
	 * @see {@link View.Square#setColor(Color)}
	 */
	private void moveSquare(Square firstClicked, Square secondClicked) {
		greySquaresArray.add(firstClicked.getIndex());
		greySquaresArray.remove(secondClicked.getIndex());
		secondClicked.setColor(firstClicked.getColor());
		firstClicked.setColor(Color.GREY);
	}

	/**
	 * Adds random squares to the board. If board is full then updates
	 * leaderboard. Additionally looks for straight and diagonal lines.
	 * 
	 * @param counter
	 *            how many squares add
	 * @see {@link HighscoreMenager#addHighscore(long, String)}
	 * @see {@link #lookForStraightLines(int, int)}
	 * @see {@link #lookForDiagonalLines(int, int, Color)}
	 */
	private void addRandomSquares(int counter) {
		Random random = new Random();
		Color color = null;
		int arrayIndex;
		int squareX, squareY;
		for (int i = 0; i < counter; i++) {
			if (greySquaresArray.size() == 0) {
				HighscoreMenager menager = new HighscoreMenager();
				menager.addHighscore(score.longValue(), new SimpleDateFormat("HH:mm dd/MM/yyyy ").format(new Date()));
				score.set(0);
				break;
			}
			color = Color.getColor(random.nextInt(Model.colorAmount-1) + 1);
			arrayIndex = random.nextInt(greySquaresArray.size());
			squareX = greySquaresArray.get(arrayIndex).getKey();
			squareY = greySquaresArray.get(arrayIndex).getValue();
			squareArray[squareX][squareY].setColor(color);
			greySquaresArray.remove(new Pair<Integer, Integer>(squareX, squareY));
			lookForStraightLines(squareX, squareY);
			lookForDiagonalLines(squareX, squareY, color);
		}
	}

	/**
	 * Looks for squares in straight line vertically and horizontally. If line
	 * was found removes that colored squares from board.
	 * 
	 * @param squareX
	 *            x coordinate of a square
	 * @param squareY
	 *            y coordinate of a square
	 * @return true if line was found, false otherwise
	 * @see {@link #removeLine(ArrayList)
	 */
	private boolean lookForStraightLines(int squareX, int squareY) {
		Color color = squareArray[squareX][squareY].getColor();
		ArrayList<Square> vertical = new ArrayList<Square>();
		ArrayList<Square> horizontal = new ArrayList<Square>();
		int x = squareX;
		int y = squareY;
		horizontal.add(squareArray[x][y]);
		while (x >= 0 && squareArray[x][y].getColor().equals(color))
			vertical.add(squareArray[x--][y]);
		x = squareX + 1;
		while (x < 9 && squareArray[x][y].getColor().equals(color))
			vertical.add(squareArray[x++][y]);
		if (vertical.size() >= 5)
			removeLine(vertical);
		x = squareX;
		y = squareY - 1;
		while (y >= 0 && squareArray[x][y].getColor().equals(color))
			horizontal.add(squareArray[x][y--]);
		y = squareY + 1;
		while (y < 9 && squareArray[x][y].getColor().equals(color))
			horizontal.add(squareArray[x][y++]);
		if (horizontal.size() >= 5)
			removeLine(horizontal);
		if (vertical.size() >= 5 || horizontal.size() >= 5)
			return true;
		return false;
	}

	/**
	 * Looks for squares in diagonal lines. If line was found removes that
	 * colored squares from board.
	 * 
	 * @param squareX
	 *            x coordinate of a square
	 * @param squareY
	 *            y coordinate of a square
	 * @param color
	 *            color of the square
	 * @return true if line was found, false otherwise
	 * @see {@link #removeLine(ArrayList)
	 */
	private boolean lookForDiagonalLines(int squareX, int squareY, Color color) {
		ArrayList<Square> diagonalLeft = new ArrayList<Square>();
		ArrayList<Square> diagonalRight = new ArrayList<Square>();
		int x = squareX - 1;
		int y = squareY - 1;
		diagonalLeft.add(squareArray[x + 1][y + 1]);
		diagonalRight.add(squareArray[x + 1][y + 1]);
		while (x >= 0 && y >= 0 && squareArray[x][y].getColor().equals(color))
			diagonalLeft.add(squareArray[x--][y--]);
		x = squareX + 1;
		y = squareY + 1;
		while (y < 9 && x < 9 && squareArray[x][y].getColor().equals(color))
			diagonalLeft.add(squareArray[x++][y++]);
		if (diagonalLeft.size() >= 5)
			removeLine(diagonalLeft);
		x = squareX + 1;
		y = squareY - 1;
		while (x < 9 && y >= 0 && squareArray[x][y].getColor().equals(color))
			diagonalRight.add(squareArray[x++][y--]);
		x = squareX - 1;
		y = squareY + 1;
		while (x >= 0 && y < 9 && squareArray[x][y].getColor().equals(color))
			diagonalRight.add(squareArray[x--][y++]);
		if (diagonalRight.size() >= 5)
			removeLine(diagonalRight);
		if (diagonalRight.size() >= 5 || diagonalLeft.size() >= 5) {
			return true;
		} else
			return false;
	}

	/**
	 * Removes given squares from the board, sets their color to grey. Adds
	 * point to score.
	 * 
	 * @param list
	 *            list of squares that has to be removed
	 */
	private void removeLine(ArrayList<Square> list) {
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			greySquaresArray.add(list.get(i).getIndex());
			list.get(i).setColor(Color.GREY);
		}
		long toAdd = (long) ((Math.pow((listSize - 3) % 3, 2) + 1) * Math.pow(10, Math.floor((listSize - 3) / 3)));
		score.set(score.get() + toAdd * (colorAmount - 4));
	}

	/**
	 * Resets board, {@link #greySquaresArray}, sets first and second clicked
	 * square to null. Updates leaderboard and adds random squares.
	 * 
	 * @see {@link #resetGreySquaresArray()}
	 * @see {@link HighscoreMenager#addHighscore(long, String)}
	 * @see {@link #setScore(long)}
	 * @see {@link #addRandomSquares(int)}
	 */
	public void resetBoard() {
		resetGreySquaresArray();
		setFirstClicked(null);
		HighscoreMenager menager = new HighscoreMenager();
		menager.addHighscore(score.longValue(), new SimpleDateFormat("HH:mm dd/MM/yyyy ").format(new Date()));
		setScore(0);
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				squareArray[i][j].setColor(Color.GREY);
		addRandomSquares(3);
	}

}