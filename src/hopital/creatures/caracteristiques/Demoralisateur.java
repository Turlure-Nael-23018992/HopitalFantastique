package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;

/**
 * Interface représentant les comportements spécifiques des créatures capables de démoraliser d'autres créatures.
 * Les créatures qui implémentent cette interface peuvent affecter le moral d'autres créatures.
 */
public interface Demoralisateur {

    /**
     * Méthode par défaut permettant à une créature démoralisatrice d'affecter le moral d'une autre créature.
     * Cette méthode diminue le moral de la créature ciblée de 5 points et affiche un message indiquant l'action.
     *
     * @param creature La créature cible qui verra son moral affecté.
     */
    default void demoraliser(Creature creature) {
        // Diminue le moral de la créature cible
        creature.getMoral().state(false, 5);

        // Affiche un message pour indiquer l'action
        System.out.println("L'elfe " + ((Creature)this).getName() + " démoralise " + creature.getName());
    }
}
