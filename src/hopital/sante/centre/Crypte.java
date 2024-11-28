package hopital.sante.centre;

import hopital.creatures.Creature;

import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.caracteristiques.VIP;
import hopital.sante.ServiceMedical;
import hopital.sante.caracteristiques.Budget;

import java.util.ArrayList;

public class Crypte extends ServiceMedical {
    private static ServiceMedical instance;
    public Crypte( String nom,
                   int superficie,
                   int max,
                   int nbPresent,
                   ArrayList<Creature> creatures,
                   Budget budget) {
        super(nom, superficie, max);
    }



    @Override
    public String toString(){
        System.out.println("Crypte");
        return "";
    } //afficher ses caractéristiques ainsi que les caractéristiques des créatures qu'il contient

    public void deleteCreature(Creature creature){
        getCreatures().remove(creature);
    }

    public void soigneCreature(Creature creature){

    }

    public void reviserBudget(){

    }
}
