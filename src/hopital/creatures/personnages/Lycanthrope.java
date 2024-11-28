package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Contamineur;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;

import java.util.ArrayList;
import java.util.Random;

public class Lycanthrope extends Creature implements Contamineur, Triage {
    public Lycanthrope(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }

    @Override
    public void contaminer(ServiceMedical serviceMedical) {
        Random r = new Random();
        if (serviceMedical.getNbPresent() == 0) return;
        int chance = r.nextInt(serviceMedical.getNbPresent());
        Creature newContamine = serviceMedical.getCreatures().get(chance - 1);
        ArrayList<Maladie> maladies = this.getMaladies();

        if (maladies.isEmpty()) return;

        Maladie newMaladie = maladies.get(r.nextInt(maladies.size()));

        if (!newContamine.getMaladies().contains(newMaladie)) {
            newContamine.getMaladies().add(newMaladie);
        }
    }
    @Override
    public Creature getCreature() {
        return this;
    }
    @Override
    public void trepasser() {
        super.trepasser();
        //contaminer(this);
    }


}
