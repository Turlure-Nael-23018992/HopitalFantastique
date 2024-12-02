package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;

/**
 * Interface représentant les comportements spécifiques d'une créature considérée comme VIP.
 * Les créatures VIP ont un comportement spécial lorsqu'elles attendent.
 */
public interface VIP {

    /**
     * Méthode par défaut permettant à une créature VIP d'attendre pendant un certain temps.
     * Cette méthode modifie l'état moral de la créature en fonction du temps d'attente.
     *
     * @param temps Durée de l'attente, en unités de temps.
     *              Cette valeur est utilisée pour ajuster le moral de la créature.
     */
    default void attendre(int temps) {
        // Modifie l'état moral de la créature VIP en fonction du temps passé à attendre.
        ((Creature)this).getMoral().state(false, 10 * temps);
    }

}
