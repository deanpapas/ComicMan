package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Collection;
import model.Entry;
import java.util.ArrayList;

public class CollectionModalController {

    @FXML
    private Text breadcrumbTxt;

    @FXML
    private ListView<String> collectionListView;

    @FXML
    private Button addToCollectionBtn;

    private Entry selectedEntry;
    private ArrayList<Collection> collectionArrayList;
    private Collection selectedCollection;

    public CollectionModalController(Entry selectedEntry, ArrayList<Collection> collectionArrayList) {
        this.selectedEntry = selectedEntry;
        this.collectionArrayList = collectionArrayList;
    }

    

    public void initialize() {

        addToCollectionBtn.setDisable(true);
        // Load the collections into the listview
        for (int i = 0; i < collectionArrayList.size(); i++) {
            collectionListView.getItems().add(collectionArrayList.get(i).getName());
        }

        

        collectionListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() != -1) {
                addToCollectionBtn.setDisable(false);
            }

        breadcrumbTxt.setText(selectedEntry.getItem() + " > " + collectionListView.getSelectionModel().getSelectedItem());
        selectedCollection = collectionArrayList.get(collectionListView.getSelectionModel().getSelectedIndex());

        });

        addToCollectionBtn.setOnAction(e -> {
            // Check if the entry already exists in the collection
            Entry[] entries = selectedCollection.getEntries();
            boolean exists = false;
            for (int i = 0; i < entries.length; i++) {
                if (entries[i].getItem().equals(selectedEntry.getItem())) {
                    exists = true;
                }
            }
            if (!exists) {
                Entry[] temp = new Entry[entries.length + 1];
                for (int i = 0; i < entries.length; i++) {
                    temp[i] = entries[i];
                }
                temp[entries.length] = selectedEntry;
                selectedCollection.setEntries(temp);
            }
    
            Stage stage = (Stage) addToCollectionBtn.getScene().getWindow();
            stage.close();
        });

    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("ComicMan");
        stage.show();
    }

}
