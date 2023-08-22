package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Instance;

public class InitialController {

    private Instance instance;
    private Stage stage;

    @FXML
    private Button startbtn;

    public InitialController(Stage stage, Instance instance) {
        this.stage = stage;
        this.instance = instance;
    }

    @FXML
    public void initialize() {

        startbtn.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home/HomeView.fxml"));
                loader.setControllerFactory(param -> {
                    return new HomeController(stage, instance);
                });

                VBox root = loader.load();
                HomeController controller = loader.getController();
                controller.showStage(root);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void showStage(VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ComicMan");
        stage.show();
    }
}
