package hopital.creatures.caracteristiques;

import hopital.creatures.Creature;

public interface Demoralisateur {
    default void demoraliser(Creature creature) {
        creature.getMoral().state(false, 5);
        System.out.println("L'elfe " + ((Creature)this).getName() + " démoralise " + creature.getName());
    }
}
