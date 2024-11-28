package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.Sexe;
import hopital.creatures.caracteristiques.VIP;

public class Nain extends Creature implements VIP {
    public Nain(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }
}