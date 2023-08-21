package controllers;

import model.Instance;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InitialController {

    private Stage stage;
    private Instance instance;


    public InitialController(Stage stage, Instance instance) {
        this.stage = stage;
        this.instance = instance;
    }


    @FXML
    public void initialize() {
        
    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Smart Canvas");
        stage.show();
    }
}
