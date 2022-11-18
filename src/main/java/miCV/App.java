package miCV;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import miCV.controller.MainController;

public class App extends Application {

	public static Stage primaryStage;

	private MainController mainController = new MainController();

	@Override
	public void start(Stage primaryStage) throws Exception {
		App.primaryStage = primaryStage;

		primaryStage.setTitle("MiCV");
		primaryStage.setScene(new Scene(mainController.getView()));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
