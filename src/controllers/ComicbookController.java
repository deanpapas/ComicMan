package controllers;

import java.io.File;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Comicbook;
import model.Instance;

public class ComicbookController {

    @FXML
    private Text comicAuthorTxt;

    @FXML
    private Text comicCharactersTxt;

    @FXML
    private HBox comicImageViewHBox;

    @FXML
    private Text comicPublisherTxt;

    @FXML
    private Text comicReleaseDateTxt;

    @FXML
    private Text comicTitleTxt;

    @FXML
    private ImageView coverImageView;

    private Stage stage;

    private Instance instance;

    private Comicbook comicbook;

    public ComicbookController(Stage stage, Instance instance, Comicbook comicbook) {
        this.stage = stage;
        this.instance = instance;
        this.comicbook = comicbook;
    }

    public void initialize() throws SQLException {
        if (comicbook != null) {
            File file = new File(comicbook.getCover());
            Image image = new Image(file.toURI().toString());
            coverImageView.setImage(image);

            comicTitleTxt.setText(comicbook.getTitle());
            comicAuthorTxt.setText(String.join(", ", comicbook.getAuthors()));
            comicPublisherTxt.setText(comicbook.getPublisher());
            comicReleaseDateTxt.setText(comicbook.getReleaseDate());
            comicCharactersTxt.setText(String.join(", ", comicbook.getCharacters()));
        }

        coverImageView.fitWidthProperty().bind(comicImageViewHBox.widthProperty());
        // coverImageView.fitHeightProperty().bind(comicImageViewHBox.heightProperty());
        coverImageView.setPreserveRatio(true);

        comicImageViewHBox.setAlignment(javafx.geometry.Pos.CENTER);

    }

    public void showStage(AnchorPane root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ComicMan");
        stage.show();
    }

}