
import java.io.IOException;
import java.sql.SQLException;

import controllers.InitialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Instance;


/*
 * The main class used to access the Smart Canvas application
 */

public class Main extends Application {
	private Instance instance;

	//Initialize New Model
	@Override
	public void init() {
		instance = new Instance();
	}

	// Load Login View
	@Override
	public void start(Stage primaryStage) {
		try {
			instance.setup();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/InitialView.fxml"));
			
			// Customize controller instance
			Callback<Class<?>, Object> controllerFactory = param -> {
				return new InitialController(primaryStage, instance);
			};
			
			loader.setControllerFactory(controllerFactory);
			VBox root = loader.load();

			InitialController initialController = loader.getController();
			initialController.showStage(root);
		} catch (IOException | SQLException | RuntimeException e) {
			Scene scene = new Scene(new Label(e.getMessage()), 200, 100);
			primaryStage.setTitle("Error");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}