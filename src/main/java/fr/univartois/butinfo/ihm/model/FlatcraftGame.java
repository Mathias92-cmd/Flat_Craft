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

import fr.univartois.butinfo.ihm.model.craft.Crafter;
import fr.univartois.butinfo.ihm.model.craft.RuleParser;
import java.io.IOException;

/**
 * La classe {@link FlatcraftGame} permet de gérer une partie du jeu Flatcraft.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class FlatcraftGame {

    /**
     * La largeur de la carte du jeu affichée (en pixels).
     */
    private final int width;

    /**
     * La hauteur de la carte du jeu affichée (en pixels).
     */
    private final int height;

    /**
     * L'instance e {@link SpriteStore} utilisée pour créer les sprites du jeu.
     */
    private SpriteStore spriteStore;

    /**
     * L'instance de {@link CellFactory} utilisée pour créer les cellules du jeu.
     */
    private CellFactory cellFactory;

    /**
     * La carte du jeu, sur laquelle le joueur évolue.
     */
    private GameMap map;

    /**
     * La représentation du joueur.
     */
    private Player player;

    private IFlatcraftController controller;

    /**
     * Crée une nouvelle instance de fr.univartois.butinfo.ihm.model.FlatcraftGame.
     *
     * @param width La largeur de la carte du jeu (en pixels).
     * @param height La hauteur de la carte du jeu (en pixels).
     */
    public FlatcraftGame(int width, int height) {
        this.width = width;
        this.height = height;
        this.spriteStore = new SpriteStore();
        this.cellFactory = new CellFactory(spriteStore);
    }

    /**
     * Donne la largeur de la carte du jeu affichée (en pixels).
     *
     * @return La largeur de la carte du jeu affichée (en pixels).
     */
    public int getWidth() {
        return width;
    }

    /**
     * Donne la hauteur de la carte du jeu affichée (en pixels).
     *
     * @return La hauteur de la carte du jeu affichée (en pixels).
     */
    public int getHeight() {
        return height;
    }

    private Crafter crafter;

    /**
     * Prépare la partie de Flatcraft avant qu'elle ne démarre.
     */
    public void prepare() throws IOException {
        map = GameMapGenerator.generatePlainMap(getHeight(), getWidth(), cellFactory); // Genere une map
        controller.initGame(map); // On utilise la fonction de l'interface et on init la map
        player = new Player(this, spriteStore.createSprite("tool_diamondpick")); // this fait reference au game donc this ici = FlatcraftGame et le nom de l'image pour le sprite
        player.setRow(map.getSoilHeight() - 1); // On le place sur la ligne au milieu (ici le sol)
        player.setColumn(0); //Toute à gauche
        controller.showMovable(player); // est on ajoute le player
        controller.setHealthProperty(player.healthProperty());
        RuleParser parser = new RuleParser("C:\\Users\\mathi\\Downloads\\FlatCraft-main\\rules\\craftrules.txt");
        parser.parse();
        crafter = parser.getCrafter();
    }

    /**
     * Fait se déplacer le joueur vers la gauche.
     */
    public void moveLeft() {
        moveLeft(player);
    }

    public void moveUp(){
        moveUp(player);
    }

    public void moveDown(){
        moveDown(player);
    }

    /**
     * Fait se déplacer un objet mobile vers la gauche.
     *
     * @param movable L'objet mobile à déplacer.
     */
    public void moveLeft(AbstractMovable movable) {
        int column = movable.getColumn();
        if (((column - 1) >= 0)) {
            controller.removeMovable(player);
            movable.setColumn(column - 1);
            controller.showMovable(player);
        }
    }

    public void moveDown(AbstractMovable movable) {
        int row = movable.getRow();
        if (((row + 1) >= 0)) {
            controller.removeMovable(player);
            movable.setRow(row + 1);
            controller.showMovable(player);
        }
    }

    public void moveUp(AbstractMovable movable) {
        int row = movable.getRow();
        if ((row - 1) >= 0) {
            controller.removeMovable(player);
            movable.setRow(row - 1);
            controller.showMovable(player);
        }
    }


    /**
     * Fait se déplacer le joueur vers la droite.
     */
    public void moveRight() {
        moveRight(player);
    }

    /**
     * Fait se déplacer un objet mobile vers la droite.
     *
     * @param movable L'objet mobile à déplacer.
     */
    public void moveRight(AbstractMovable movable) {
        int column = movable.getColumn();
        if (((column + 1) < map.getWidth())) {
            controller.removeMovable(player);
            movable.setColumn(column + 1);
            controller.showMovable(player);
        }
    }




    /**
     * Déplace un objet mobile en tenant compte de la gravité.
     *
     * @param movable L'objet à déplacer.
     */
    private void move(AbstractMovable movable) {
        // On applique la gravité.
        Cell currentCell = getCellOf(movable);
        controller.removeMovable(movable);
        for (int row = currentCell.getRow() + 1; row < map.getHeight(); row++) {
            Cell below = map.getAt(row, currentCell.getColumn());
            if (!below.move(movable)) {
                break;
            }
        }
        controller.showMovable(movable);
    }

    /**
     * Fait creuser le joueur vers le bas.
     */
    public void digDown() {
        Cell currentCell = getCellOf(player);
        if ((currentCell.getRow() + 1) < map.getHeight()) {
            map.getAt(currentCell.getRow() + 1, currentCell.getColumn()).dig(player);
            moveDown(player);
        }
    }

    public void digUp(){
        Cell currentCell = getCellOf(player);
        if ((currentCell.getRow() - 1) < map.getHeight()) {
            map.getAt(currentCell.getRow() - 1, currentCell.getColumn()).dig(player);
            move(player);
        }
    }

    /**
     * Fait creuser le joueur vers la gauche.
     */
    public void digLeft() {
        Cell currentCell = getCellOf(player);
        if ((currentCell.getColumn() - 1) > 0) {
            map.getAt(currentCell.getRow(), currentCell.getColumn() - 1).dig(player);
            move(player);
        }
    }

    /**
     * Fait creuser le joueur vers la droite.
     */
    public void digRight() {
        Cell currentCell = getCellOf(player);
        if ((currentCell.getColumn() + 1) < map.getWidth()) {
            map.getAt(currentCell.getRow(), currentCell.getColumn() + 1).dig(player);
            move(player);
        }
    }

    /**
     * Retire un objet mobile du jeu.
     *
     * @param movable L'objet mobile à retirer.
     */
    public void removeMovable(AbstractMovable movable) {
        controller.removeMovable(movable);
    }

    /**
     * Récupére la cellule correspondant à la position d'un objet mobile.
     *
     * @param movable L'objet mobile dont la cellule doit être récupérée.
     *
     * @return La cellule occupée par l'objet mobile.
     */
    private Cell getCellOf(AbstractMovable movable) {
        int row = movable.getRow();
        int column = movable.getColumn();
        return map.getAt(row, column);
    }

    public void setController(IFlatcraftController controller){
        this.controller = controller;
    }

}
