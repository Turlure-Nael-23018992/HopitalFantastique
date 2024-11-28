package hopital.sante.centre;



import hopital.sante.ServiceMedical;
import hopital.creatures.Creature;
import hopital.sante.caracteristiques.Budget;

import java.util.ArrayList;

public class Quarantaine extends ServiceMedical {
    public Quarantaine(String nom,
                       int superficie,
                       int max,
                       int nbPresent,
                       ArrayList<Creature> creatures,
                       Budget budget) {
        super(nom, superficie, max);
        super.setNbPresent(super.getNbPresent() + nbPresent);
    }


    public void addCreature(Creature creature){
        getCreatures().add(creature);
    }

    public void deleteCreature(Creature creature){
        getCreatures().remove(creature);
    }

    public void soigneCreature(Creature creature){

    }

    public void reviserBudget(){

    }
}
