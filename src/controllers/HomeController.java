package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Character;
import model.Collection;
import model.Comicbook;
import model.Entry;
import model.Hero;
import model.Instance;
import model.Villain;

public class HomeController {

    private Instance instance;
    private Stage stage;
    private ObservableList<Entry> observableEntryList;
    private ArrayList<Collection> collectionArrayList;

    @FXML
    private ListView<String> collectionHomeListView;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<Entry, String> colCategory;

    @FXML
    private TableColumn<Entry, String> colItem;

    @FXML
    private TableView<Entry> itemTableView;

    @FXML
    private Pane objectDisplayPane;

    @FXML
    private TextField searchTxtField;

    @FXML
    private Button newCollectionBtn;

    @FXML
    private TextField newCollectionTxtField;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    public HomeController(Stage stage, Instance instance) {
        this.stage = stage;
        this.instance = instance;
    }

    public void initialize() throws IOException, SQLException {

        // Load all collections
        collectionArrayList = instance.getCollections();
        for (int i = 0; i < collectionArrayList.size(); i++) {
            collectionHomeListView.getItems().add(collectionArrayList.get(i).getName());
        }

        // Set tableview
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Change tableview based on current collection
        collectionHomeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    searchTxtField.clear();
                    objectDisplayPane.getChildren().clear();
                    if (newValue == null) {
                        return;
                    }

                    for (int i = 0; i < collectionArrayList.size(); i++) {
                        if (collectionArrayList.get(i).getName().equals(newValue)) {
                            setObservableEntryList(collectionArrayList.get(i).getEntries());
                            itemTableView.setItems(observableEntryList);
                        }
                    }
                    sortEntries();

                });

        // Change object display pane based on selected item
        itemTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // check if comicbook or character
            objectDisplayPane.getChildren().clear();
            addBtn.setDisable(false);
            try {
                if (newValue == null) {
                    return;
                } else if (instance.getComicbook("title", newValue.getItem()) != null) {
                    loadComicbookView(
                            instance.getComicbook("title",
                                    itemTableView.getSelectionModel().getSelectedItem().getItem()));
                } else if (instance.getCharacter("name", newValue.getItem()) != null) {
                    loadCharacterView(instance.getCharacter("name",
                            itemTableView.getSelectionModel().getSelectedItem().getItem()));
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // Search/Filter tableview
        searchTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (observableEntryList == null) {
                return;
            }
            itemTableView.setItems(
                    observableEntryList.filtered(entry -> entry.getItem().toLowerCase().contains(newValue.toLowerCase())
                            || entry.getCategory().toLowerCase().contains(newValue.toLowerCase() )));
        });

        // Add current entry to collection
        addBtn.setOnAction(e -> {
            if (itemTableView.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/views/Collection/CollectionModalView.fxml"));
                loader.setControllerFactory(param -> {
                    return new CollectionModalController(itemTableView.getSelectionModel().getSelectedItem(), collectionArrayList);
                });

                Pane root = loader.load();
                CollectionModalController controller = loader.getController();
                controller.showStage(root);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Add new collection
        newCollectionBtn.setOnAction(e -> {
            if (newCollectionTxtField.getText().isEmpty()) {
                return;
            }
            // check if collection already exists
            for (int i = 0; i < collectionArrayList.size(); i++) {
                if (collectionArrayList.get(i).getName().equals(newCollectionTxtField.getText())) {
                    return;
                }
            }

            Collection newCollection = new Collection(newCollectionTxtField.getText(), new Entry[0]);
            instance.addCollection(newCollection);
            collectionHomeListView.getItems().add(newCollection.getName());
            newCollectionTxtField.clear();
        });
    }

    public void loadComicbookView(Comicbook comicbook) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Comicbook/ComicbookView.fxml"));
            loader.setControllerFactory(param -> {

                return new ComicbookController(stage, comicbook);

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
                    return new HeroController(stage, (Hero) character);
                });

                objectDisplayPane.getChildren().add(loader.load());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Character/VillainView.fxml"));
                loader.setControllerFactory(param -> {
                    return new VillainController(stage, (Villain) character);
                });

                objectDisplayPane.getChildren().add(loader.load());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sortEntries() {
        observableEntryList.sort((entry1, entry2) -> entry1.getItem().compareTo(entry2.getItem()));
    }

    public void setObservableEntryList(Entry[] entryList) {
        observableEntryList = FXCollections.observableArrayList(entryList);
    }

    public void showStage(VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ComicMan");
        stage.show();

    }

}
