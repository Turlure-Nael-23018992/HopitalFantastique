package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.caracteristiques.VIP;

import java.util.ArrayList;

public class Reptilien extends Creature implements VIP {
    public Reptilien(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }

}
