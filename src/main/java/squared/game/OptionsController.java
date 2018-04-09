package squared.game;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * Controls all actions that can happen when Options.fxml is loaded.
 * @author bartl_000
 *
 */
public class OptionsController {

	/**
	 * Instance of class {@link View}
	 */
	private View view;

	/**
	 * Instance of class {@link Model}
	 */
	private Model model;

	/**
	 * Variable to handle easyIcon actions.
	 */
	@FXML
	private ImageView easyIcon;

	/**
	 * Variable to handle mediumIcon actions.
	 */
	@FXML
	private ImageView mediumIcon;

	/**
	 * Variable to handle hardIcon actions.
	 */
	@FXML
	private ImageView hardIcon;

	/**
	 * Variable to handle resetIcon actions.
	 */
	@FXML
	private ImageView resetIcon;

	/**
	 * Variable to handle muteIcon actions.
	 */
	@FXML
	private ImageView muteIcon;

	/**
	 * Variable to handle menuIcon actions.
	 */
	@FXML
	private ImageView menuIcon;

	/**
	 * Sets current scene to Menu.
	 * 
	 * @see {@link View#loadMainMenu(Model)}
	 */
	@FXML
	private void returnToMainMenu() {
		view.loadMainMenu(model);
	}

	/**
	 * Sets color amount to 5.
	 * @see {@link Model#setColorAmount(int)}
	 */
	@FXML
	private void setEasyMode() {
		model.setColorAmount(5);
	}

	/**
	 * Sets color amount to 7.
	 * @see {@link Model#setColorAmount(int)}
	 */
	@FXML
	private void setMediumMode() {
		model.setColorAmount(7);
	}

	/**
	 * Sets color amount to 9.
	 * @see {@link Model#setColorAmount(int)}
	 */
	@FXML
	private void setHardMode() {
		model.setColorAmount(10);
	}

	/**
	 * Resets leaderboard.
	 * @see {@link HighscoreMenager#resetFile()}
	 */
	@FXML
	private void resetLeaderboard() {
		HighscoreMenager menager = new HighscoreMenager();
		menager.resetFile();
	}

	/**
	 * Mutes sound.
	 * @see {@link View#muteBackgroundMusic()}
	 */
	@FXML
	private void muteSound() {
		view.muteBackgroundMusic();
	}

	/**
	 * Sets {@link OptionsController#easyIcon} to P_EasyIcon.png and
	 * plays menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterEasyIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(easyIcon, "P_EasyIcon.png");
	}

	/**
	 * Sets {@link OptionsController#easyIcon} to EasyIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitEasyIcon() {
		view.setImageView(easyIcon, "EasyIcon.png");
	}

	/**
	 * Sets {@link OptionsController#mediumIcon} to P_MediumIcon.png and
	 * plays menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterMediumIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(mediumIcon, "P_MediumIcon.png");
	}

	/**
	 * Sets {@link OptionsController#mediumIcon} to MediumIconIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitMediumIcon() {
		view.setImageView(mediumIcon, "MediumIcon.png");
	}

	/**
	 * Sets {@link OptionsController#hardIcon} to P_HardIcon.png and
	 * plays menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterHardIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(hardIcon, "P_HardIcon.png");
	}

	/**
	 * Sets {@link OptionsController#hardIcon} to HardIconIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitHardIcon() {
		view.setImageView(hardIcon, "HardIcon.png");
	}

	/**
	 * Sets {@link OptionsController#resetIcon} to P_ResetLeaderboard.png and
	 * plays menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterResetIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(resetIcon, "P_ResetLeaderboard.png");
	}

	/**
	 * Sets {@link OptionsController#resetIcon} to ResetLeaderboard.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitResetIcon() {
		view.setImageView(resetIcon, "ResetLeaderboard.png");
	}

	/**
	 * Sets {@link OptionsController#muteIcon} to P_MuteIcon.png and
	 * plays menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterMuteIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(muteIcon, "P_MuteIcon.png");
	}

	/**
	 * Sets {@link OptionsController#muteIcon} to MuteIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitMuteIcon() {
		view.setImageView(muteIcon, "MuteIcon.png");
	}

	/**
	 * Sets {@link OptionsController#menuIcon} to P_MenuIcon.png and
	 * plays menuClick.mp3.
	 * 
	 * @see View#addSound
	 * @see View#setImageView
	 */
	@FXML
	private void enterMenuIcon() {
		view.addSound("menuClick.mp3");
		view.setImageView(menuIcon, "P_MenuIcon.png");
	}

	/**
	 * Sets {@link OptionsController#menuIcon} to MenuIcon.png.
	 * 
	 * @see View#setImageView
	 */
	@FXML
	private void exitMenuIcon() {
		view.setImageView(menuIcon, "MenuIcon.png");
	}

	/**
	 * Sets {@link OptionsController#model} variable.
	 * 
	 * @param model - instance of class {@link Model}
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Sets {@link OptionsController#view} variable.
	 * 
	 * @param view -  instance of class {@link View}
	 */
	public void setView(View view) {
		this.view = view;
	}

}
