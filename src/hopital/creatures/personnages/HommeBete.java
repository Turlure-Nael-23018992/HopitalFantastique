package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Contamineur;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;

import java.util.ArrayList;
import java.util.Random;

public class HommeBete extends Creature implements Contamineur, Triage {
    public HommeBete(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }


    @Override
    public void trepasser() {
        super.trepasser();
        contaminer(ServiceMedical.getInstance());
    }




}

