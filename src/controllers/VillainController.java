package controllers;

import java.io.File;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Instance;
import model.Villain;

public class VillainController {


    @FXML
    private Text abilitiesTxt;

    @FXML
    private Text firstAppearanceTxt;

    @FXML
    private ImageView imageView;

    @FXML
    private Text nameTxt;

    @FXML
    private Text nemesisTxt;

    @FXML
    private Text universeTxt;

    private Stage stage;

    private Instance instance;

    private Villain villain;

    private Label message;


    public VillainController(Stage stage, Instance instance, Villain villain, Label message) {
        this.stage = stage;
        this.instance = instance;
        this.villain = villain;
        this.message = message;
    }

    public void initialize() throws SQLException {

        File file = new File(villain.getImage());
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);

        nameTxt.setText(villain.getName());
        universeTxt.setText(villain.getUniverse());
        firstAppearanceTxt.setText(villain.getFirstAppearance());
        abilitiesTxt.setText(String.join(",", villain.getAbilities()));
        nemesisTxt.setText(villain.getNemesis());
        message.setText(villain.getCatchPhrase());

        

    }

    public void showStage(AnchorPane root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ComicMan");
        stage.show();
    }

}