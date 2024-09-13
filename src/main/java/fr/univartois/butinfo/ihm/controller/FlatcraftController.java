/**
 * Sample Skeleton for 'flatcraft-view.fxml' Controller Class
 */

package fr.univartois.butinfo.ihm.controller;

import fr.univartois.butinfo.ihm.model.AbstractMovable;
import fr.univartois.butinfo.ihm.model.FlatcraftGame;
import fr.univartois.butinfo.ihm.model.GameMap;
import fr.univartois.butinfo.ihm.model.IFlatcraftController;
import fr.univartois.butinfo.ihm.model.Player;
import java.io.IOException;
import java.security.Key;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FlatcraftController implements IFlatcraftController {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int CELLS_LENGHT = 30;

    private Stage stage;

    private Player player;

    private FlatcraftGame game;

    private IntegerProperty healthProperty;


    @FXML // fx:id="background"
    private GridPane background; // Value injected by FXMLLoader

    @FXML // fx:id="mainpane"
    private GridPane mainpane; // Value injected by FXMLLoader

    @FXML // fx:id="niveauVie"
    private Label niveauVie; // Value injected by FXMLLoader

    private ImageView[][] backgroundCell;

    private ImageView[][] mainCellFrame;

    private Scene scene;


    @FXML
    void onClickShowCraft(ActionEvent event) {

    }

    @FXML
    void onClickShowFurnace(ActionEvent event) {

    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    @FXML
    void onClickShowInventory(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../inventory.fxml"));
        Parent viewContent = fxmlLoader.load();

        InventoryController inventoryController = fxmlLoader.getController();
        inventoryController.setStage(stage);
        inventoryController.getInventory(player.getInventaire());
        inventoryController.setGameScene(this.scene);
        Scene sceneInventory = new Scene(viewContent);

        stage.setScene(sceneInventory);
    }

    @FXML
    private void initialize() {
        backgroundCell = new ImageView[HEIGHT / CELLS_LENGHT][WIDTH / CELLS_LENGHT];
        mainCellFrame = new ImageView[HEIGHT / CELLS_LENGHT][WIDTH / CELLS_LENGHT];

        for (int i = 0; i < HEIGHT / CELLS_LENGHT; i++) {
            for (int j = 0; j < WIDTH / CELLS_LENGHT; j++) {
                backgroundCell[i][j] = new ImageView(); //On créer des images view
                backgroundCell[i][j].setFitWidth(CELLS_LENGHT); // On leur donne la taille width
                backgroundCell[i][j].setFitHeight(CELLS_LENGHT); // On leur donne la taille width
                background.add(backgroundCell[i][j], j, i); // On l'ajoute dans la grille (En gros on créer nous même les lignes et column)

                mainCellFrame[i][j] = new ImageView(); // same que au dessus
                mainCellFrame[i][j].setFitWidth(CELLS_LENGHT);
                mainCellFrame[i][j].setFitHeight(CELLS_LENGHT);
                mainpane.add(mainCellFrame[i][j], j, i);
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setFlatcraftGame(FlatcraftGame game) {
        this.game = game;
    }

    private boolean ifShiftPress = false;

    @Override
    public void initGame(GameMap map) {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                backgroundCell[i][j].imageProperty().bind(map.getAt(i, j).spriteProperty());
            }
        }
        stage.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            KeyCode code = e.getCode();
            if(e.isShiftDown()){
                if(code == KeyCode.LEFT || code.equals(KeyCode.Q)){
                    game.digLeft();
                } else if(code == KeyCode.RIGHT || code.equals(KeyCode.D)){
                    game.digRight();
                } else if(code == KeyCode.DOWN || code.equals(KeyCode.S)){
                    game.digDown();
                }
            }
            if (code == KeyCode.RIGHT || code.equals(KeyCode.D)) {
                game.moveRight();
            } else if (code == KeyCode.LEFT || code.equals(KeyCode.Q)) {
                game.moveLeft();
            }
            else if (code == KeyCode.DOWN || code.equals(KeyCode.S)){
                game.moveDown();
            }
            else if (code == KeyCode.UP || code.equals(KeyCode.Z)){
                game.moveUp();
            }
        });
    }

        @Override
        public void showMovable (AbstractMovable movable){
            mainCellFrame[movable.getRow()][movable.getColumn()].setImage(movable.getSprite());
            if(movable instanceof Player p){
                setPlayer(p);
            }

        }

        @Override
        public void removeMovable (AbstractMovable movable){
            mainCellFrame[movable.getRow()][movable.getColumn()].setImage(null);
        }

        @Override
        public void setHealthProperty (IntegerProperty healthProperty){
            this.healthProperty = healthProperty;
            niveauVie.textProperty().bind(healthProperty.asString());
        }

        public void setPlayer(Player player){
            this.player = player;
        }
    }
