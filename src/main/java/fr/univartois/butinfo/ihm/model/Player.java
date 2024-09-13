package fr.univartois.butinfo.ihm.model;

import java.util.NoSuchElementException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;



/**
 * La classe fr.univartois.butinfo.ihm.model.Player représente le personnage du joueur qui se déplace sur la carte du jeu.
 *
 * @author Alwin Wallon
 *
 * @version 0.1.0
 */
public final class Player extends AbstractMovable {


    /**
     * Crée une nouvelle instance de fr.univartois.butinfo.ihm.model.Player.
     *
     * @param game Le jeu dans lequel le joueur évolue.
     * @param sprite Le sprite représentant le joueur.
     */

    private ObservableList<Resource> inventaire;

    public Player(FlatcraftGame game, Image sprite) {
        super(game, sprite, 3);
        this.inventaire = FXCollections.observableArrayList();
    }

    /**
     * Ajoute un objet à l'inventaire de ce joueur.
     *
     * @param resource L'objet à ajouter.
     */
    public void addToInventory(Resource resource) {
        inventaire.add(resource);
    }

    public ObservableList<Resource> getInventaire() {
        return inventaire;
    }

    /**
     * Retire un objet de l'inventaire de ce joueur.
     *
     * @param resource L'objet à retirer.
     *
     * @throws NoSuchElementException Si l'objet n'est pas présent dans l'inventaire.
     */
    public void removeFromInventory(Resource resource) {
        if(inventaire.size() != 0){
            inventaire.remove(resource);
        }
    }

}
