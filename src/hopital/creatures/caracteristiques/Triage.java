package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;

public interface Triage {
    Creature getCreature();
    default void attendre(int temps) {
        getCreature().getMoral().state(false, 10 * temps);
    }

}
