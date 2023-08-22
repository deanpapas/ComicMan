package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Instance;

public class ComicbookController {

    @FXML
    private Text comicAuthorTxt;

    @FXML
    private Text comicCharacterTxt;

    @FXML
    private TextArea comicDescriptionTxtField;

    @FXML
    private Text comicGenreTxt;

    @FXML
    private Text comicPublisherTxt;

    @FXML
    private Text comicReleaseDateTxt;

    @FXML
    private Text comicTitleTxt;

    private Stage stage;

    private Instance instance;

    public ComicbookController(Stage stage, Instance instance) {
        this.stage = stage;
        this.instance = instance;
    }

    public void showStage(AnchorPane root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ComicMan");
        stage.show();
    }

}