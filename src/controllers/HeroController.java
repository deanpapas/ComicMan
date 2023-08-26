package controllers;

import java.io.File;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Hero;


public class HeroController {

    @FXML
    private Text abilitiesTxt;

    @FXML
    private Text alliesTxt;

    @FXML
    private HBox comicImageViewHBox;

    @FXML
    private Text firstAppearanceTxt;

    @FXML
    private Text identityTxt;

    @FXML
    private ImageView imageView;

    @FXML
    private Text nameTxt;

    @FXML
    private Text teamsTxt;

    @FXML
    private Text universeTxt;

    @FXML
    private Text villainsTxt;

    private Stage stage;

    private Hero hero;

    public HeroController(Stage stage, Hero hero) {
        this.stage = stage;
        this.hero = hero;
    }

    public void initialize() throws SQLException {

        File file = new File(hero.getImage());
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);

        nameTxt.setText(hero.getName());
        identityTxt.setText(hero.getIdentity());
        universeTxt.setText(hero.getUniverse());
        firstAppearanceTxt.setText(hero.getFirstAppearance());
        alliesTxt.setText(String.join(", ", hero.getAllies()));
        villainsTxt.setText(String.join(", ", hero.getVillains()));
        teamsTxt.setText(String.join(", ", hero.getTeams()));
        abilitiesTxt.setText(String.join(", ", hero.getAbilities()));

    }

    public void showStage(AnchorPane root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ComicMan");
        stage.show();
    }

}