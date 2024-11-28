package hopital.sante;

import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.caracteristiques.VIP;
import hopital.sante.caracteristiques.Budget;
import hopital.sante.centre.Crypte;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public abstract class ServiceMedical {
    private static ServiceMedical instance ;
    private final ReentrantLock lock = new ReentrantLock();
    private String nom;
    private int superficie;
    private int max;
    private int nbPresent;
    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private Budget budget;

    public ServiceMedical(String nom, int superficie, int max) {
        this.nom = nom;
        this.superficie = superficie;
        this.max = max;
    }

    public void addCreature(Creature creature) {
        if (nbPresent < max) {
            creatures.add(creature);
            nbPresent++;  // Mise à jour du nombre de présents
            System.out.println(creature.getName() + " a été ajouté au service " + this.nom);
        } else {
            System.out.println("Le service est plein, impossible d'ajouter " + creature.getName());
        }
    }
    public ReentrantLock getLock() {
        return lock;
    }
    public static ServiceMedical getInstance() {
        return instance;
    }
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



    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void setCreatures(ArrayList<Creature> creatures) {
        this.creatures = creatures;
        this.nbPresent = creatures.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // En-tête avec les informations du service
        sb.append("\n=== Service Médical: ").append(nom).append(" ===\n");
        sb.append("Superficie: ").append(superficie).append(" m²\n");
        sb.append("Capacité maximale: ").append(max).append(" créatures\n");
        sb.append("Nombre de patients présents: ").append(this.nbPresent).append("\n");
        sb.append("Budget actuel: ").append(this.budget).append("\n");

        // Liste des créatures
        sb.append("\nListe des patients:\n");
        if (this.creatures.isEmpty()) {
            sb.append("Aucun patient présent\n");
        } else {
            for (Creature creature : creatures) {
                sb.append("- ").append(creature.toString()).append("\n");
            }
        }

        sb.append("=====================================\n");

        return sb.toString();
    }


    public abstract void deleteCreature(Creature creature);

    public abstract void soigneCreature(Creature creature);

    public abstract void reviserBudget();
}
