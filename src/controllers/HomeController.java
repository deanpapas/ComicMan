package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Hero;
import model.Instance;
import model.Character;

public class HomeController {

    private Instance instance;
    private Stage stage;


    @FXML
    private Pane objectDisplayPane;

    @FXML
    private Button testbtn;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    public HomeController(Stage stage, Instance instance) {
        this.stage = stage;
        this.instance = instance;
    }

    public void initialize() throws IOException {

        testbtn.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Comicbook/ComicbookView.fxml"));
                loader.setControllerFactory(param -> {
                    return new ComicbookController(stage, instance);
                });

                objectDisplayPane.getChildren().add(loader.load());

                //instance.characterDAO.setCharacter("batman","hero");
                System.out.println("Character added");
                Character character = instance.characterDAO.getCharacter("name", "batman");
                System.out.println(character.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        );
    }

    public void showStage(VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ComicMan");
        stage.show();
    }

}
