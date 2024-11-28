package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Contamineur;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;

import java.util.ArrayList;
import java.util.Random;

public class Orque extends Creature implements Contamineur, Triage {
    public Orque(String name, Sexe sexe, int poids, int taille, int age) {
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
    public void attendre(int temps) { //faire en sorte que plus il attend plus il perd du moral

        getMoral().state(false, 10 * temps);
    }
    @Override
    public void trepasser() {
        super.trepasser();
        //contaminer();
    }

}
