package hopital.creatures;

import hopital.creatures.caracteristiques.Moral;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.caracteristiques.VIP;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Creature {
    private static Creature instance ;
    private final ReentrantLock lock = new ReentrantLock();
    private String name;
    private Sexe sexe;
    private int poids;
    private int taille;
    private int age;
    private Moral moral;
    private ArrayList<Maladie> maladies;

    public Creature (String name, Sexe sexe, int poids, int taille, int age) {
        this.name = name;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moral = Moral.AUTOP;
        this.maladies = new ArrayList<Maladie>();
        instance = this;
    }

    public Creature(String name, Sexe sexe, int age) {
        this.name = name;
        this.sexe = sexe;
        this.age = age;
        instance = this;
    }
    public ReentrantLock getLock() {
        return lock;
    }
    public static Creature getInstance() {
        return instance;
    }
    public void attendre() {
        moral.state(false, 20);
        System.out.println("La créature " + this.name + " a attendu et a maintenant un moral de " + this.moral);
    }

    public boolean hurler() {
        if (this.moral == Moral.PASCOOL) {
            System.out.println("La créature " + this.name + " a hurlé car son moral est au plus bas.");
            return true;
        } else {
            System.out.println("La créature " + this.name + " se sent bien et ne peut hurler pour le moment.");
            return false;
        }
    }

    public void semporter() {
        if (this.moral == Moral.PASCOOL) {
            for (int i = 0; i < 5; i++) {
                hurler();
            }
        } else {
            System.out.println("La créature " + this.name + " se sent bien et ne peut s'emporter pour le moment.");
        }
    }

    public void tomberMalade() {
        // Récupérer toutes les maladies possibles
        Maladie[] maladiesPossibles = Maladie.values();

        // Sélectionner une maladie aléatoire
        Random random = new Random();
        Maladie nouvellesMaladie = maladiesPossibles[random.nextInt(maladiesPossibles.length)];

        // Vérifier si la créature n'a pas déjà cette maladie
        if (!maladies.contains(nouvellesMaladie)) {
            maladies.add(nouvellesMaladie);
            System.out.println("La créature " + this.name + " est tombée malade de " + nouvellesMaladie.getNomComplet());
        } else {
            // Si la créature a déjà cette maladie, on augmente son niveau
            nouvellesMaladie.augmenterNiveau(1);
            System.out.println("La maladie " + nouvellesMaladie.getNomComplet() + " de " + this.name + " s'est aggravée");
        }
    }

    public void etreSoignee(Maladie maladie) {
        maladies.remove(maladie);
        System.out.println("La créature " + this.name + " a été soignée de " + maladie.getNomComplet());
    }

    public void trepasser() {
        if (maladies.size() >= 3) {
            System.out.println("La créature " + this.name + " est décédée.");
        } else {
            System.out.println("La créature " + this.name + " est encore en vie.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Moral getMoral() {
        return moral;
    }

    public void setMoral(Moral moral) {
        this.moral = moral;
    }

    public Creature getCreature() {
        return this;
    }
    public ArrayList<Maladie> getMaladies() {
        return maladies;
    }

    public void setMaladies(ArrayList<Maladie> maladies) {
        this.maladies = maladies;
    }

    public void attendre(ArrayList<Creature> creatures, int temps) {
        try {
            System.out.println("Avant sleep");
            Thread.sleep(1);
            System.out.println("Après sleep");

            if (this instanceof VIP) {
              System.out.println("VIP attendre");
                ((VIP) this).attendre(temps);
            } else if (this instanceof Triage) {
                ((Triage) this).attendre(creatures, temps);
            }
        } catch (InterruptedException e) {
            System.err.println("Interruption pendant l'attente: " + e.getMessage());
        }

    }

}