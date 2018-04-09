package squared.game;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * Controls all actions that can happen when Menu.fxml is loaded.
 * @author bartl_000
 *
 */
public class MenuController {

	/**
	 * Instance of class {@link View}.
	 */
	private View view;

	/**
	 * Instance of class {@link Model}.
	 */
	private Model model;

	/**
	 * Variable to handle normalModeIcon actions.
	 */
	@FXML
	private ImageView normalModeIcon;

	/**
	 * Variable to handle optionsIcon actions.
	 */
	@FXML
	private ImageView optionsIcon;

	/**
	 * Variable to handle ExitIcon actions.
	 */
	@FXML
	private ImageView exitIcon;

	/**
	 * Sets current scene to NormalMode.
	 * 
	 * @see {@link View#loadNormalMode(Model)
	 */
	@FXML
	private void openNormalMode() {
		view.loadNormalMode(model);
	}

	/**
	 * Sets current scene to Options.
	 * 
	 * @see {@link View#loadOptions(Model)
	 */
	@FXML
	private void openOptions() {
		view.loadOptions(model);
	}

	/**
	 * Exits game.
	 */
	@FXML
	private void exitSquared() {
		Platform.exit();
	}

	/**
	 * Sets {@link MenuController#normalModeIcon} to P_NormalModeIcon.png and
	 * plays menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterNormalModeIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(normalModeIcon, "P_NormalModeIcon.png");
	}

	/**
	 * Sets {@link MenuController#normalModeIcon} to NormalModeIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitNormalModeIcon() {
		view.setImageView(normalModeIcon, "NormalModeIcon.png");
	}

	/**
	 * Sets {@link MenuController#optionsIcon} to P_OptionsIcon.png and plays
	 * menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterOptionsIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(optionsIcon, "P_OptionsIcon.png");
	}

	/**
	 * Sets {@link MenuController#optionsIcon} to OptionsIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitOptionsIcon() {
		view.setImageView(optionsIcon, "OptionsIcon.png");
	}

	/**
	 * Sets {@link MenuController#exitIcon} to P_ExitIcon.png and plays
	 * menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterExitIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(exitIcon, "P_ExitIcon.png");
	}

	/**
	 * Sets {@link MenuController#exitIcon} to ExitIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitExitIcon() {
		view.setImageView(exitIcon, "ExitIcon.png");
	}

	/**
	 * Sets {@link MenuController#model} variable.
	 * 
	 * @param model - instance of class {@link Model}
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Sets {@link MenuController#view} variable.
	 * 
	 * @param view -  instance of class {@link View}
	 */
	public void setView(View view) {
		this.view = view;
	}

}
