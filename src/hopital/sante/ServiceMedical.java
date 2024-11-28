package hopital.sante;

import hopital.creatures.Creature;
import hopital.sante.caracteristiques.Budget;

import java.util.ArrayList;

public abstract class ServiceMedical {
    private String nom;
    private int superficie;
    private int max;
    private int nbPresent;
    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private Budget budget;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNbPresent() {
        return nbPresent;
    }

    public void setNbPresent(int nbPresent) {
        this.nbPresent = nbPresent;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public void setCreatures(ArrayList<Creature> creatures) {
        this.creatures = creatures;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void addCreature(Creature creature){
        creatures.add(creature);
    }

    @Override
    public abstract String toString(); //afficher ses caractéristiques ainsi que les caractéristiques des créatures qu'il contient


    public abstract void deleteCreature(Creature creature);

    public abstract void soigneCreature(Creature creature);

    public abstract void reviserBudget();
}
