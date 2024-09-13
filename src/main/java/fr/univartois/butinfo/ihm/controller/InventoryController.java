package fr.univartois.butinfo.ihm.controller;

import fr.univartois.butinfo.ihm.model.Resource;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InventoryController {

  private Stage stage;

  private Scene scene;

  public void getInventory(ObservableList<Resource> inventory){
    listInventory.setItems(inventory);
  }

  public void setGameScene(Scene scene){
    this.scene = scene;
  }

  public void setStage(Stage stage){
    this.stage = stage;
  }

  @FXML
  private ImageView imageInventory;

  @FXML
  private Button closeInventory;

  @FXML
  private Label labelInventory;

  @FXML
  private ListView<Resource> listInventory;

  @FXML
  void onClickClose(ActionEvent event) {
    stage.setScene(scene);
  }

  @FXML
  private void initialize(){
    listInventory.getSelectionModel().selectedItemProperty().addListener((observable , oldValue , ressourceSelected) -> {
      if(ressourceSelected != null){
        imageInventory.setImage(ressourceSelected.getSprite());
        labelInventory.setText(ressourceSelected.getName());
      }
    });
  }

}
