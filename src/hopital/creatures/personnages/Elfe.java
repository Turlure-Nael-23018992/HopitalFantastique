package hopital.creatures.personnages;

import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Demoralisateur;
import hopital.Sexe;
import hopital.creatures.caracteristiques.VIP;
import hopital.sante.ServiceMedical;

public class Elfe extends Creature implements Demoralisateur, VIP {
    public Elfe(String name, Sexe sexe, int poids, int taille, int age) {
        super(name, sexe, poids, taille, age);
    }

    @Override
    public void demoraliser(Creature creature) {
        creature.getMoral().state(false, 5);
        System.out.println("L'elfe " + this.getName() + " démoralise " + creature.getName());
    }

    @Override
    public void trepasser() {
        super.trepasser();
        demoraliser(this);
    }


}
