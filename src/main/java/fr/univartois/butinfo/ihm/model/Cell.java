package fr.univartois.butinfo.ihm.model; /**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2023-2024 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * La classe {@link Cell} représente une cellule de la carte du jeu Flatcraft.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Cell {

    /**
     * La ligne où se trouve cette cellule dans la carte.
     */
    private int row;

    /**
     * La colonne où se trouve cette cellule dans la carte.
     */
    private int column;

    /**
     * La ressource contenue dans cette cellule.
     * Si la cellule est vide, la valeur de cet attribut est {@code null}.
     */
    private Resource resource;

    /**
     * Le sprite représentant le contenu de cette cellule sur la carte.
     */
    private final ObjectProperty<Image> sprite;

    /**
     * Crée une nouvelle instance de fr.univartois.butinfo.ihm.model.Cell.
     * La cellule créée est initialement vide.
     *
     * @param row La ligne où se trouve la cellule.
     * @param column La colonne où se trouve la cellule.
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        sprite = new SimpleObjectProperty<Image>();
    }

    /**
     * Crée une nouvelle instance de fr.univartois.butinfo.ihm.model.Cell.
     * La cellule créée est initialement vide.
     *
     * @param image L'image représentant la cellule.
     */
    public Cell(Image image) {
        this.sprite = new SimpleObjectProperty<>(image);
    }

    /**
     * Crée une nouvelle instance de fr.univartois.butinfo.ihm.model.Cell.
     * La cellule créée est initialement vide.
     *
     * @param resource La ressource contenue dans la cellule.
     */
    public Cell(Resource resource) {
        this.resource = resource;
        this.sprite = new SimpleObjectProperty<>(resource.getSprite());
    }

    /**
     * Modifie la ligne où se trouve cette cellule dans la carte du jeu.
     *
     * @param row La nouvelle ligne où se trouve cette cellule.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Donne la ligne où se trouve cette cellule dans la carte du jeu.
     *
     * @return La ligne où se trouve cette cellule.
     */
    public int getRow() {
        return row;
    }

    /**
     * Modifie la colonne où se trouve cette cellule dans la carte du jeu.
     *
     * @param column La nouvelle colonne où se trouve cette cellule.
     */
    public void setColum(int column) {
        this.column = column;
    }

    /**
     * Donne la colonne où se trouve cette cellule dans la carte du jeu.
     *
     * @return La colonne où se trouve cette cellule.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Donne le sprite représentant le contenu de cette cellule sur la carte.
     *
     * @return Le sprite représentant cette cellule.
     */
    public Image getSprite() {
        return sprite.get();
    }

    public ObjectProperty<Image> spriteProperty(){
        return sprite;
    }

    /**
     * Modifie la ressource présente sur cette cellule sur la carte.
     *
     * @param resource La ressource à placer sur cette cellule.
     */
    public void setResource(Resource resource) {
        if (resource == null) {
            this.resource = null;
            this.sprite.set(null);

        } else {
            this.resource = resource;
            this.sprite.set(resource.getSprite());
        }
    }

    /**
     * Modifie cette cellule pour qu'elle soit dans le même état que la cellule donnée.
     *
     * @param cell La cellule à copier pour remplacer celle-ci.
     */
    public void replaceBy(Cell cell) {
        this.resource = cell.resource;
        this.sprite.set(cell.getSprite());
    }

    /**
     * Déplace un objet mobile du jeu vers le bas si cette cellule le permet.
     * Par exemple, si une cellule ne contient pas de ressource, l'objet peut "passer à
     * travers" cette cellule.
     *
     * @param movable L'objet mobile à déplacer.
     *
     * @return Si l'objet mobile a été déplacé.
     */
    public boolean move(AbstractMovable movable) {
        if (resource == null) {
            movable.setRow(row);
            return true;
        }
        return false;
    }

    /**
     * Essaye d'extraire la ressource contenue dans cette cellule.
     * Si l'extraction réussit, la ressource est ajoutée à l'inventaire du joueur.
     *
     * @param player Le joueur qui souhaite extraire la ressource.
     *
     * @return Si une ressource a été extraite.
     */
    public boolean dig(Player player) {
        if (resource != null) {
            player.addToInventory(resource);
            setResource(null);
            return true;
        }
        return false;
    }

}
