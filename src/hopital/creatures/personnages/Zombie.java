package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Regenerateur;
import hopital.Sexe;

public class Zombie extends Creature implements Regenerateur {
    public Zombie(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }

    @Override
    public void regenerer(Creature creature) {
        // TODO
    }

    @Override
    public void trepasser() {
        super.trepasser();
        regenerer(this);
    }
}
