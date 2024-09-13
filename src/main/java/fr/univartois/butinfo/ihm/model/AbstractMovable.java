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
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

/**
 * La classe fr.univartois.butinfo.ihm.model.AbstractMovable est la classe parente des différents objets mobiles pouvant
 * se déplacer dans le jeu Flatcraft.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public abstract class AbstractMovable {

    /**
     * Le jeu Flatcraft dans lequel cet objet mobile se déplace.
     */
    protected final FlatcraftGame game;

    /**
     * La ligne où se trouve cet objet mobile.
     */
    private int row;

    /**
     * La colonne où se trouve cet objet mobile.
     */
    private int column;

    /**
     * L'image représentant cet objet mobile.
     */
    private final Image sprite;

    /**
     * Les points de vie restants pour cet objet mobile.
     */
    private final IntegerProperty health;

    /**
     * Crée une nouvelle instance d'fr.univartois.butinfo.ihm.model.AbstractMovable.
     *
     * @param game Le jeu Flatcraft dans lequel l'objet se déplace.
     * @param sprite L'image représentant l'objet.
     * @param initialHealth Les points de vie initiaux de l'objet.
     */
    protected AbstractMovable(FlatcraftGame game, Image sprite, int initialHealth) {
        this.game = game;
        this.sprite = sprite;
        this.health = new SimpleIntegerProperty(initialHealth);
    }

    /**
     * Modifie la ligne où se trouve cet objet mobile.
     *
     * @param row La nouvelle ligne où se trouve cet objet mobile.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Donne la ligne où se trouve cet objet mobile.
     *
     * @return La ligne où se trouve cet objet mobile.
     */
    public int getRow() {
        return row;
    }

    /**
     * Modifie la colonne où se trouve cet objet mobile.
     *
     * @param column La nouvelle colonne où se trouve cet objet mobile.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Donne la colonne où se trouve cet objet mobile.
     *
     * @return La colonne où se trouve cet objet mobile.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Donne l'image représentant cet objet mobile.
     *
     * @return L'image représentant cet objet mobile.
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Donne les points de vie restants pour cet objet mobile.
     *
     * @return Les points de vie restants pour cet objet mobile.
     */
    public int getHealth() {
        return health.get();
    }

    public IntegerProperty healthProperty(){
        return health;
    }

    /**
     * Augmente les points de vie de cet objet mobile.
     */
    public void incrementHealth() {
        health.set(health.get()+1);
    }

    /**
     * Diminue les points de vie de cet objet mobile.
     */
    public void decrementHealth() {
        health.set(health.get()-1);
        if (getHealth() == 0) {
            game.removeMovable(this);
        }
    }

}
