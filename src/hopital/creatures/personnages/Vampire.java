package hopital.creatures.personnages;

import hopital.Sexe;
import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.*;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;

import java.util.ArrayList;
import java.util.Random;

public class Vampire extends Creature implements Demoralisateur, Contamineur, Regenerateur, Triage {
    public Vampire(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }
    @Override
    public void trepasser() {
        super.trepasser();
        contaminer(ServiceMedical.getInstance());
        demoraliser(Creature.getInstance());
        this.regenerer();
    }



}
