package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;

public interface Regenerateur {
    default void regenerer() {
        ((Creature)this).getMoral().setValeur(100);
    }
}