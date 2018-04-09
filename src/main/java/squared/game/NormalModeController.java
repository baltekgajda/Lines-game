package squared.game;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import squared.game.View.Square;
import javafx.scene.layout.VBox;

/**
 * Controls all actions that can happen when NormalMode.fxml is loaded. Contain
 * methods that add handlers to squares in game.
 * 
 * @author bartl_000
 *
 */
public class NormalModeController {

	/**
	 * Instance of class {@link View}.
	 */
	private View view;

	/**
	 * Instance of class {@link Model}.
	 */
	private Model model;

	/**
	 * Instance of class {@link HighscoreMenager}.
	 */
	private HighscoreMenager scoreMenager;

	/**
	 * Flowpane used to hold all clickable squares.
	 */
	@FXML
	private FlowPane squareFlowPane;

	/**
	 * Flowpane used to hold label with highscores.
	 */
	@FXML
	private VBox vBox;

	/**
	 * Variable to handle scoreIcon actions.
	 */
	@FXML
	private Label scoreIcon;

	/**
	 * Variable to handle menuIcon actions.
	 */
	@FXML
	private ImageView menuIcon;

	/**
	 * Variable to handle newGameIcon actions.
	 */
	@FXML
	private ImageView newGameIcon;

	/**
	 * Variable to handle muteIcon actions.
	 */
	@FXML
	private ImageView muteIcon;

	/**
	 * Returns to main manu scene after reseting board of squares,
	 * greySquareArray.
	 * 
	 * @see Model#resetBoard()
	 * @see Model#resetGreySquaresArray()
	 */
	@FXML
	private void returnToMainMenu() {
		model.resetBoard();
		model.resetGreySquaresArray();
		model.setFirstClicked(null);
		view.loadMainMenu(model);
	}

	/**
	 * Resets board of squares and updates leaderboard.
	 * 
	 * @see Model#resetBoard()
	 * @see View#addLeaderboard(VBox, java.util.ArrayList)
	 */
	@FXML
	private void resetSquares() {
		model.resetBoard();
		view.addLeaderboard(vBox, scoreMenager.getHighscore());
	}

	/**
	 * Mutes background music.
	 * 
	 * @see View#muteBackgroundMusic()
	 */
	@FXML
	private void muteBackgroundMusic() {
		view.muteBackgroundMusic();
	}

	/**
	 * Sets {@link NormalModeController#newGameIcon} to P_NewGameSquareIcon.png
	 * and plays menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterNewGameIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(newGameIcon, "P_NewGameSquareIcon.png");
	}

	/**
	 * Sets {@link NormalModeController#newGameIcon} to NewGameSquareIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitNewGameIcon() {
		view.setImageView(newGameIcon, "NewGameSquareIcon.png");
	}

	/**
	 * Sets {@link NormalModeController#menuIcon} to P_menuSquareIcon.png and
	 * plays menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterMenuIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(menuIcon, "P_menuSquareIcon.png");
	}

	/**
	 * Sets {@link NormalModeController#menuIcon} to menuSquareIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitMenuIcon() {
		view.setImageView(menuIcon, "menuSquareIcon.png");
	}

	/**
	 * Sets {@link NormalModeController#muteIcon} to P_muteSquareIcon.png and
	 * plays menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterMuteIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(muteIcon, "P_muteSquareIcon.png");
	}

	/**
	 * Sets {@link NormalModeController#muteIcon} to muteSquareIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitMuteIcon() {
		view.setImageView(muteIcon, "muteSquareIcon.png");
	}

	/**
	 * Getting FlowPane object containing all squares.
	 * 
	 * @return {@link #squareFlowPane}
	 */
	public FlowPane getFlowPane() {
		return squareFlowPane;
	}

	/**
	 * Sets {@link NormalModeController#model} variable and score to 0 points.
	 * Binds {@link #scoreIcon} with current score, which is held in Model
	 * object
	 * 
	 * @param model
	 * @see Model#setScore(long)
	 */
	public void setModel(Model model) {
		this.model = model;
		model.setScore(0);
		scoreIcon.textProperty().bind(model.getScore().asString());
	}

	/**
	 * Sets {@link MenuController#view} variable.
	 * 
	 * @param view
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * Shows actual leaderboard and adds event handler to all buttons held in
	 * Square array.
	 * 
	 * @param square
	 *            - array of squares we want to add handlers to
	 * @see {@link Model#handleClick(Square)
	 * @see {@link View#addLeaderboard(VBox, java.util.ArrayList)
	 */
	public void setSquareHandle(Square[][] square) {
		scoreMenager = new HighscoreMenager();
		view.addLeaderboard(vBox, scoreMenager.getHighscore());
		model.setSquareArray(square);
		EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Square handledSquare = (Square) event.getSource();
				model.handleClick(handledSquare);
				vBox.getChildren().clear();
				view.addLeaderboard(vBox, scoreMenager.getHighscore());
			}
		};

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				square[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
			}
		}
	}

}
