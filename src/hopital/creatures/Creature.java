package hopital.creatures;

import hopital.creatures.caracteristiques.Moral;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.caracteristiques.VIP;
import hopital.sante.Maladie;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature {
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
    }

    public Creature(String name, Sexe sexe, int age) {
        this.name = name;
        this.sexe = sexe;
        this.age = age;
    }

    public void attendre() {
        moral.state(false, 5);
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

    public void tomberMalade(Maladie maladie) {
        maladies.add(maladie);
        System.out.println("La créature " + this.name + " est tombée malade de " + maladie.getNomComplet());
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

    public ArrayList<Maladie> getMaladies() {
        return maladies;
    }

    public void setMaladies(ArrayList<Maladie> maladies) {
        this.maladies = maladies;
    }

    public void attendre(ArrayList<Creature> creatures, int temps) {
        try {
            Thread.sleep(temps * 1000);
            if (this instanceof VIP) {
                attendreVIP(temps);
            } else if (this instanceof Triage) {
                attendreTriage(creatures, temps);
            }
        } catch (InterruptedException e) {
            System.err.println("Interruption pendant l'attente: " + e.getMessage());
        }

    }

    private void attendreVIP(int temps) { //faire en sorte que plus il attend plus il perd du moral

        getMoral().state(false, 10 * temps);
    }

    private void attendreTriage(ArrayList<Creature> creatures, int temps) { //il perd de moin en moin de moral quand il y a des collegues (+ il y a des potes moin il perd du moral)
        creatures.remove(this);
        int countTriage = 1;
        for (Creature creature : creatures) {
            if (creature instanceof Triage) {
                countTriage++;
            }

        }
        getMoral().state(false, 10 / countTriage );
    }
}