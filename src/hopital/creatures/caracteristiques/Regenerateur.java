package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;

/**
 * Interface représentant les comportements spécifiques des créatures régénératrices.
 * Les créatures qui implémentent cette interface peuvent régénérer leur moral.
 */
public interface Regenerateur {

    /**
     * Méthode par défaut permettant à une créature régénératrice de restaurer son moral à une valeur maximale.
     * Cette méthode réinitialise le moral de la créature à 100.
     */
    default void regenerer() {
        // Réinitialise le moral de la créature à 100
        ((Creature)this).getMoral().setValeur(100);
    }
}
