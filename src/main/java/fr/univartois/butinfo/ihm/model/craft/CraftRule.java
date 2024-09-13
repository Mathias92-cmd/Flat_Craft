package fr.univartois.butinfo.ihm.model.craft; /**
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
 * (c) 2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

/**
 * La classe {@link CraftRule} représente une règle de craft, permettant de produire une
 * nouvelle ressource à partir de ressources ayant été collectées sur la carte.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class CraftRule {

    /**
     * La règle indiquant les ingrédients permettant de produire la ressource, ainsi que
     * leur positionnement (le cas échéant).
     */
    private final String rule;

    /**
     * Le résultat produit par l'application de cette règle.
     */
    private final String product;

    /**
     * La quantité de ressource produite par l'application de cette règle.
     */
    private final int quantity;

    /**
     * Crée une nouvelle instance de CraftRule.
     *
     * @param rule La règle indiquant les ingrédients permettant de produire la ressource,
     *        ainsi que leur positionnement (le cas échéant).
     * @param product Le résultat produit par l'application de la règle.
     * @param quantity La quantité de ressource produite par l'application de cette règle.
     */
    CraftRule(String rule, String product, int quantity) {
        this.rule = rule;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Donne la règle indiquant les ingrédients permettant de produire la ressource, ainsi
     * que leur positionnement (le cas échéant).
     *
     * @return La règle indiquant les ingrédients utilisés.
     */
    public String getRule() {
        return rule;
    }

    /**
     * Donne le résultat produit par l'application de cette règle.
     *
     * @return Le résultat produit par l'application de cette règle.
     */
    public String getProduct() {
        return product;
    }

    /**
     * Donne la quantité de ressource produite par l'application de cette règle.
     *
     * @return La quantité de ressource produite par l'application de cette règle.
     */
    public int getQuantity() {
        return quantity;
    }

}
