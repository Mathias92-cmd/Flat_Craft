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

import fr.univartois.butinfo.ihm.model.Resource;
import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * La classe {@link Crafter} représente un objet permettant d'appliquer des
 * {@link CraftRule} à un ensemble de ressources données.
 * Typiquement, il peut s'agir du fourneau ou de la table de craft.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Crafter {

    /**
     * Les règles de craft, indexées par la règle qui les définit.
     */
    private final Map<String, CraftRule> craftRules;

    /**
     *
     * Crée une nouvelle instance de Crafter.
     *
     * @param craftRules Les règles de craft, indexées par la règle qui les définit.
     */
    Crafter(Map<String, CraftRule> craftRules) {
        this.craftRules = craftRules;
    }

    /**
     * Donne la règle à appliquer à partir des ressources donner pour obtenir une nouvelle
     * ressource.
     *
     * @param resources Les ressources qui constituent les ingrédients de la règle à
     *        appliquer.
     *
     * @return La règle à appliquer, ou {@link Optional#empty()} si aucune règle ne
     *         correspond.
     */
    public Optional<CraftRule> getRuleFor(List<Resource> resources) {
        String rule = resources.stream().map(Resource::getName).collect(joining("_"));
        return Optional.ofNullable(craftRules.get(rule));
    }

}
