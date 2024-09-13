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

import java.util.HashMap;
import java.util.Map;

/**
 * La classe {@link CrafterBuilder} permet de créer des instances de {@link Crafter} en
 * ajoutant progressivement les règles reconnues par cet objet.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class CrafterBuilder {

    /**
     * Les règles de craft, indexées par la règle qui les définit.
     */
    private final Map<String, CraftRule> rules = new HashMap<>();

    /**
     * Désactive l'instanciation par des classes externes.
     */
    private CrafterBuilder() {
        // Rien à faire ici.
    }

    /**
     * Crée une nouvelle instance de {@link CrafterBuilder}.
     *
     * @return L'instance créée.
     */
    public static CrafterBuilder newInstance() {
        return new CrafterBuilder();
    }

    /**
     * Ajoute une règle de craft à l'objet {@link Crafter} en cours de création.
     *
     * @param rule La règle indiquant les ingrédients permettant de produire la ressource,
     *        ainsi que leur positionnement (le cas échéant).
     * @param product Le résultat produit par l'application de la règle.
     *
     * @return Ce constructeur.
     */
    public CrafterBuilder withRule(String rule, String product) {
        return withRule(rule, product, 1);
    }

    /**
     * Ajoute une règle de craft à l'objet {@link Crafter} en cours de création.
     *
     * @param rule La règle indiquant les ingrédients permettant de produire la ressource,
     *        ainsi que leur positionnement (le cas échéant).
     * @param product Le résultat produit par l'application de la règle.
     * @param quantity La quantité de ressource produite par l'application de cette règle.
     *
     * @return Ce constructeur.
     */
    public CrafterBuilder withRule(String rule, String product, int quantity) {
        rules.put(rule, new CraftRule(rule, product, quantity));
        return this;
    }

    /**
     * Ajoute une règle de craft à l'objet {@link Crafter} en cours de création.
     *
     * @param rule La règle à ajouter.
     *
     * @return Ce constructeur.
     */
    public CrafterBuilder withRule(CraftRule rule) {
        rules.put(rule.getRule(), rule);
        return this;
    }

    /**
     * Crée l'objet {@link Crafter} ayant été construit.
     *
     * @return L'objet {@link Crafter} construit.
     */
    public Crafter build() {
        return new Crafter(rules);
    }

}
