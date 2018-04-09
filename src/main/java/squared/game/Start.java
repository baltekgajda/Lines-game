package squared.game;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class that is the first class launched when application starts working.
 * Creates objects of Model, View ond MenuController classes.
 * 
 * @author bartl_000
 * @version 1.0
 * @see {@link Model}
 * @see {@link View}
 * @see {@link MenuController}
 */
public class Start extends Application {

	/**
	 * Main method in application. Launches a standalone application.
	 * 
	 * @param args
	 *            main method arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		View view = new View(primaryStage);
		Model model = new Model();
		MenuController menuController = view.getMenuController();
		menuController.setView(view);
		menuController.setModel(model);
	}

}
