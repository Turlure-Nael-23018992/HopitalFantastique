package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.Sexe;
import hopital.creatures.caracteristiques.VIP;

public class Reptilien extends Creature implements VIP {
    public Reptilien(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }

    @Override
    public void attendre() {
        // TODO
    }
}
