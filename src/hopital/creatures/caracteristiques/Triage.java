package hopital.creatures.caracteristiques;
import hopital.creatures.Creature;

public interface Triage {
    default void attendre(int temps) {
        ((Creature)this).getMoral().state(false, 10 * temps);
    }
}