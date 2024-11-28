package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;

import java.util.ArrayList;

public interface VIP {
    default void attendre(int temps) {
        ((Creature)this).getMoral().state(false, 10 * temps);
    }

}
