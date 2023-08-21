package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Instance;

public class InitialController {

    private Instance instance;
    private Stage stage;
    

    @FXML
    private Button testbtn;

    public InitialController(Stage stage, Instance instance) {
        this.stage = stage;
        this.instance = instance;
    }

    @FXML
    public void initialize() {

        testbtn.setOnAction(e -> {
            System.out.println("Test Button Clicked");
        });
    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Smart Canvas");
        stage.show();
    }
}
