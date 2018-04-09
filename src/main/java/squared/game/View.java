package squared.game;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * Class which methods are used to load Scenes and update elements on the screen
 * 
 * @author bartl_000
 *
 */
public class View {

	/**
	 * Variable holding object loaded from FXML file.
	 */
	private FXMLLoader loader;

	/**
	 * Variable holding backgroundMusic.
	 */
	private MediaPlayer backgroundMusic;

	/**
	 * Variable holding main Pane to which new elements can be added
	 */
	private static Pane mainPane;

	/**
	 * Constructor which sets bacground music and loads Menu.fxml.
	 * 
	 * @see {@link #setBackgroundMusic()}
	 * @see {@link #setDefaultStageSettings(Stage)}
	 * 
	 * @param primaryStage
	 *            stage to which all scenes will be added
	 */
	public View(Stage primaryStage) {
		setBackgroundMusic();
		setDefaultStageSettings(primaryStage);
		this.loader = new FXMLLoader(this.getClass().getResource("/fxml/Menu.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane = pane;
		Scene scene = new Scene(pane, 1260, 840);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Sets default stage setting, for instance title and icon.
	 * 
	 * @param primaryStage
	 *            stage to which all scenes will be added
	 */
	private void setDefaultStageSettings(Stage primaryStage) {
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/BLUE.png"))); // IKONKA
		primaryStage.setTitle("SQUARED");
		primaryStage.centerOnScreen();
	}

	/**
	 * Sets background music and its volume. Sets this track to be played in
	 * loop.
	 */
	private void setBackgroundMusic() {
		URL resource = getClass().getResource("/sounds/backgroundMusic.mp3");
		backgroundMusic = new MediaPlayer(new Media(resource.toString()));
		backgroundMusic.setVolume(0.07);
		backgroundMusic.setOnEndOfMedia(new Runnable() {
			public void run() {
				backgroundMusic.seek(Duration.ZERO);
			}
		});
		backgroundMusic.play();
	}

	/**
	 * Mutes background music.
	 */
	public void muteBackgroundMusic() {
		if (backgroundMusic.getVolume() != 0)
			backgroundMusic.setVolume(0);
		else
			backgroundMusic.setVolume(0.07);
	}

	/**
	 * Sets object's image to a picture with a name given as a parameter
	 * 
	 * @param imageView
	 *            image we want to change image
	 * @param image
	 *            name of picture
	 */
	public void setImageView(ImageView imageView, String image) {
		imageView.setImage(new Image(this.getClass().getResourceAsStream("/images/" + image)));
	}

	/**
	 * Plays sound that name was given as a parameter.
	 * 
	 * @param sound
	 *            name of the track
	 */
	public void addSound(String sound) {
		Media media = new Media(this.getClass().getResource("/sounds/" + sound).toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}

	/**
	 * Sets FXML loader, loads it to pane and adds it to {@link #mainPane}
	 * 
	 * @param loader
	 *            loader we want to load
	 */
	private void setLoader(FXMLLoader loader) {
		this.loader = loader;
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().add(pane);
	}

	/**
	 * Loads main menu from Menu.fxml file. Sets menu controller.
	 * 
	 * @param model
	 *            instance of class {@link Model}
	 */
	public void loadMainMenu(Model model) {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Menu.fxml"));
		setLoader(loader);
		MenuController menuController = loader.getController();
		menuController.setModel(model);
		menuController.setView(this);
	}

	/**
	 * Loads options from Options.fxml file. Sets options controller.
	 * 
	 * @param model
	 *            instance of class {@link Model}
	 */
	public void loadOptions(Model model) {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Options.fxml"));
		setLoader(loader);
		OptionsController optionsController = loader.getController();
		optionsController.setModel(model);
		optionsController.setView(this);
	}

	/**
	 * Loads normal mode from NormalMode.fxml file. Sets normal mode controller.
	 * 
	 * @param model
	 *            instance of class {@link Model}
	 */
	public void loadNormalMode(Model model) {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/NormalMode.fxml"));
		setLoader(loader);
		NormalModeController normalModeController = loader.getController();
		normalModeController.setModel(model);
		normalModeController.setView(this);
		normalModeController.setSquareHandle(loadSquares(normalModeController.getFlowPane()));
	}

	/**
	 * Getting menu controller from loader
	 * 
	 * @return instance of class {@link MenuController}
	 */
	public MenuController getMenuController() {
		MenuController menuController = this.loader.getController();
		return menuController;
	}

	/**
	 * Creates adday of squares and adds them to flowPane given as an argument.
	 * 
	 * @param flowPane
	 *            pane to add squares
	 * @return identifier of created square array
	 */
	private Square[][] loadSquares(FlowPane flowPane) {
		Square[][] square = new Square[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				square[i][j] = new Square(i, j);
				flowPane.getChildren().add(square[i][j]);
			}
		}
		return square;
	}

	/**
	 * Creates leaderboard table, adds highscores to it.
	 * 
	 * @param vBoxId
	 *            vBox where scores will be added
	 * @param highscores
	 *            array holding highscores
	 */
	public void addLeaderboard(VBox vBoxId, ArrayList<Score> highscores) {
		Label[] label = new Label[10];
		vBoxId.getChildren().clear();
		for (int i = 0; i < highscores.size(); i++) {
			if (i == 9)
				label[i] = new Label(i + 1 + ".  " + highscores.get(i).getScore().toString());
			else
				label[i] = new Label(i + 1 + ".   " + highscores.get(i).getScore().toString());
			label[i].setFont(Font.font("FORCED SQUARE", 60));
			label[i].setTextFill(Paint.valueOf("#838181"));
			vBoxId.getChildren().add(label[i]);
		}
		for (int i = highscores.size(); i < 10; i++) {
			if (i == 9)
				label[i] = new Label(i + 1 + ".  0");
			else
				label[i] = new Label(i + 1 + ".   0");
			label[i].setFont(Font.font("FORCED SQUARE", 60));
			label[i].setTextFill(Paint.valueOf("#838181"));
			vBoxId.getChildren().add(label[i]);
		}
	}

	/**
	 * Class that enable creating custom imageView. Contains methods used to for
	 * instance setColor of square given.
	 * 
	 * @author bartl_000
	 *
	 */
	public class Square extends ImageView {

		/**
		 * Color of the square
		 */
		private Model.Color color = Model.Color.GREY;

		/**
		 * index of the square.
		 */
		private Pair<Integer, Integer> index;

		/**
		 * Constructor that sets default color and sets coordinates.
		 * 
		 * @param x
		 *            first coordinate
		 * @param y
		 *            second coordinate
		 * @see {@link #setSquareImage(String)}
		 */
		public Square(Integer x, Integer y) {
			index = new Pair<Integer, Integer>(x, y);
			setSquareImage(color.toString());
		}

		/**
		 * Sets square color.
		 * 
		 * @param color
		 *            given color we want to change it to
		 */
		private void setSquareImage(String color) {
			setImage(new Image(this.getClass().getResourceAsStream("/images/" + color + ".png")));
		}

		/**
		 * Sets color of the square.
		 * 
		 * @param color
		 *            to which we want to change square
		 */
		public void setColor(Model.Color color) {
			this.color = color;
			setSquareImage(color.toString());
		}

		/**
		 * Getting color of the square.
		 * 
		 * @return color of certain square
		 */
		public Model.Color getColor() {
			return color;
		}

		/**
		 * Getting x and y coordinates of the square.
		 * 
		 * @return coordinates of certain square
		 */
		public Pair<Integer, Integer> getIndex() {
			return index;
		}

		/**
		 * Sets square to clicked changing its color and playing sound.
		 */
		public void setClicked() {
			setSquareImage("P_" + color.toString());
			addSound("click.mp3");
		}

		/**
		 * Sets square to unclicked changing its color.
		 */
		public void setUnclicked() {
			setSquareImage(color.toString());
		}
	}

}
