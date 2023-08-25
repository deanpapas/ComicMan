package controllers;

import java.io.IOException;
import java.sql.SQLException;

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
import model.Comicbook;

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

    public void initialize() throws IOException, SQLException {

        Comicbook comicbook1 = instance.comicbookDAO.getComicbook("title", "Detective Comics #27");
        System.out.println(comicbook1.getTitle() + "----------------------------------------------------------");
        testbtn.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Comicbook/ComicbookView.fxml"));
                loader.setControllerFactory(param -> {
                    return new ComicbookController(stage, instance, comicbook1);
                });

                objectDisplayPane.getChildren().add(loader.load());

                Character character = instance.characterDAO.getCharacter("name", "Batman");
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
