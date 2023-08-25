package controllers;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.Arrays;

import javax.xml.stream.events.Characters;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private Label characterMessageTxt;

    @FXML
    private Pane objectDisplayPane;

    @FXML
    private TextField searchTxtField;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private ListView<String> itemListView;

    public HomeController(Stage stage, Instance instance) {
        this.stage = stage;
        this.instance = instance;
    }

    public void initialize() throws IOException, SQLException {

        // Load all comicbooks and characters
        Comicbook[] comicbooks = instance.comicbookDAO.getComicbooks();
        Character[] characters = instance.characterDAO.getCharacters();

        for (int i = 0; i < comicbooks.length; i++) {
            itemListView.getItems().add(comicbooks[i].getTitle());
            if (characters[i] != null) {
                itemListView.getItems().add(characters[i].getName());
            }
            sortItems();
        }

        itemListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // check if comicbook or character
            try {
                if (instance.getComicbook("title", newValue) != null) {
                    loadComicbookView(
                            instance.getComicbook("title", itemListView.getSelectionModel().getSelectedItem()));
                } else if (instance.getCharacter("name", newValue) != null) {
                    loadCharacterView(instance.getCharacter("name", newValue));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        searchTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
            objectDisplayPane.getChildren().clear();
            itemListView.getItems().clear();
            for (int i = 0; i < comicbooks.length; i++) {
                if (comicbooks[i].getTitle().toLowerCase().contains(newValue.toLowerCase())) {
                    itemListView.getItems().add(comicbooks[i].getTitle());
                }
                if (characters[i] != null && characters[i].getName().toLowerCase().contains(newValue.toLowerCase())) {
                    itemListView.getItems().add(characters[i].getName());
                }
            }
            sortItems();
        });
    }

    public void loadComicbookView(Comicbook comicbook) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Comicbook/ComicbookView.fxml"));
            loader.setControllerFactory(param -> {

                return new ComicbookController(stage, instance, comicbook);

            });

            objectDisplayPane.getChildren().add(loader.load());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadCharacterView(Character character) {

        if (character instanceof Hero) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Character/HeroView.fxml"));
                loader.setControllerFactory(param -> {
                    return new HeroController(stage, instance, (Hero) character, characterMessageTxt);
                });

                objectDisplayPane.getChildren().add(loader.load());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Character/VillainView.fxml"));
                loader.setControllerFactory(param -> {
                    return new VillainController(stage, instance, (model.Villain) character, characterMessageTxt);
                });

                objectDisplayPane.getChildren().add(loader.load());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sortItems() {
        String[] items = new String[itemListView.getItems().size()];
        for (int i = 0; i < itemListView.getItems().size(); i++) {
            items[i] = itemListView.getItems().get(i);
        }
        Arrays.sort(items);
        itemListView.getItems().clear();
        for (int i = 0; i < items.length; i++) {
            itemListView.getItems().add(items[i]);
        }
    }

    public void showStage(VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ComicMan");
        stage.show();
    }

}
