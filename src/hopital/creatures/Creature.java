package hopital.creatures;

import hopital.creatures.caracteristiques.Moral;
import hopital.Sexe;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.caracteristiques.VIP;
import hopital.sante.Maladie;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe abstraite représentant une créature dans un contexte hospitalier.
 * Gère les attributs de base comme le nom, le sexe, le poids, l'âge et la santé.
 */
public abstract class Creature {

    /** Instance unique de la créature. */
    private static Creature instance;

    /** Verrou pour la synchronisation. */
    private final ReentrantLock lock = new ReentrantLock();

    /** Nom de la créature. */
    private String name;

    /** Sexe de la créature. */
    private Sexe sexe;

    /** Poids de la créature en kilogrammes. */
    private int poids;

    /** Taille de la créature en centimètres. */
    private int taille;

    /** Âge de la créature en années. */
    private int age;

    /** État moral de la créature. */
    private Moral moral;

    /** Liste des maladies dont souffre la créature. */
    private ArrayList<Maladie> maladies;

    /**
     * Constructeur principal de la classe Creature.
     *
     * @param name  Nom de la créature.
     * @param sexe  Sexe de la créature.
     * @param poids Poids en kilogrammes.
     * @param taille Taille en centimètres.
     * @param age Âge en années.
     */
    public Creature(String name, Sexe sexe, int poids, int taille, int age) {
        this.name = name;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moral = Moral.AUTOP;
        this.maladies = new ArrayList<>();
        instance = this;
    }

    public Creature(String name, Sexe sexe, int age) {
        this.name = name;
        this.sexe = sexe;
        this.age = age;
        this.moral = Moral.AUTOP;
        this.maladies = new ArrayList<>();
        instance = this;
    }

    /**
     * Retourne l'instance unique de Creature.
     *
     * @return L'instance unique de Creature.
     */
    public static Creature getInstance() {
        return instance;
    }

    /**
     * Fait attendre la créature, ce qui diminue son moral.
     */
    public void attendre() {
        moral.state(false, 20);
        System.out.println("La créature " + this.name + " a attendu et a maintenant un moral de " + this.moral);
    }

    /**
     * La créature hurle si son moral est bas.
     *
     * @return true si la créature a hurlé, false sinon.
     */
    public boolean hurler() {
        if (this.moral == Moral.PASCOOL) {
            System.out.println("La créature " + this.name + " a hurlé car son moral est au plus bas.");
            return true;
        } else {
            System.out.println("La créature " + this.name + " se sent bien et ne peut hurler pour le moment.");
            return false;
        }
    }

    /**
     * Fait s'emporter la créature si son moral est bas.
     */
    public void semporter() {
        if (this.moral == Moral.PASCOOL) {
            for (int i = 0; i < 5; i++) {
                hurler();
            }
        } else {
            System.out.println("La créature " + this.name + " se sent bien et ne peut s'emporter pour le moment.");
        }
    }

    /**
     * Fait tomber malade la créature en ajoutant une maladie aléatoire.
     */
    public void tomberMalade() {
        Maladie[] maladiesPossibles = Maladie.values();
        Random random = new Random();
        Maladie nouvellesMaladie = maladiesPossibles[random.nextInt(maladiesPossibles.length)];

        if (!maladies.contains(nouvellesMaladie)) {
            maladies.add(nouvellesMaladie);
            System.out.println("La créature " + this.name + " est tombée malade de " + nouvellesMaladie.getNomComplet());
        } else {
            nouvellesMaladie.augmenterNiveau(1);
            System.out.println("La maladie " + nouvellesMaladie.getNomComplet() + " de " + this.name + " s'est aggravée");
        }
    }

    /**
     * Soigne la créature en supprimant une maladie.
     *
     * @param maladie La maladie à soigner.
     */
    public void etreSoignee(Maladie maladie) {
        maladies.remove(maladie);
        System.out.println("La créature " + this.name + " a été soignée de " + maladie.getNomComplet());
    }

    /**
     * Vérifie si la créature doit mourir selon le nombre de maladies qu'elle a.
     */
    public void trepasser() {
        if (maladies.size() >= 3) {
            System.out.println("La créature " + this.name + " est décédée.");
        } else {
            System.out.println("La créature " + this.name + " est encore en vie.");
        }
    }

    /**
     * Retourne le nom de la créature.
     *
     * @return Le nom de la créature.
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom de la créature.
     *
     * @param name Le nouveau nom de la créature.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne le sexe de la créature.
     *
     * @return Le sexe de la créature.
     */
    public Sexe getSexe() {
        return sexe;
    }

    /**
     * Modifie le sexe de la créature.
     *
     * @param sexe Le nouveau sexe de la créature.
     */
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    /**
     * Retourne le poids de la créature.
     *
     * @return Le poids en kilogrammes.
     */
    public int getPoids() {
        return poids;
    }

    /**
     * Modifie le poids de la créature.
     *
     * @param poids Le nouveau poids en kilogrammes.
     */
    public void setPoids(int poids) {
        this.poids = poids;
    }

    /**
     * Retourne la taille de la créature.
     *
     * @return La taille en centimètres.
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Modifie la taille de la créature.
     *
     * @param taille La nouvelle taille en centimètres.
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * Retourne l'âge de la créature.
     *
     * @return L'âge en années.
     */
    public int getAge() {
        return age;
    }

    /**
     * Modifie l'âge de la créature.
     *
     * @param age Le nouvel âge en années.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retourne l'état moral de la créature.
     *
     * @return L'état moral actuel.
     */
    public Moral getMoral() {
        return moral;
    }

    /**
     * Modifie l'état moral de la créature.
     *
     * @param moral Le nouvel état moral.
     */
    public void setMoral(Moral moral) {
        this.moral = moral;
    }

    /**
     * Retourne la liste des maladies de la créature.
     *
     * @return Liste des maladies.
     */
    public ArrayList<Maladie> getMaladies() {
        return maladies;
    }

    /**
     * Modifie la liste des maladies de la créature.
     *
     * @param maladies La nouvelle liste des maladies.
     */
    public void setMaladies(ArrayList<Maladie> maladies) {
        this.maladies = maladies;
    }

    /**
     * Fait attendre la créature, avec prise en compte du temps pour les VIP et triages.
     *
     * @param creatures Liste des créatures en attente.
     * @param temps Temps d'attente en millisecondes.
     */
    public void attendre(ArrayList<Creature> creatures, int temps) {
        try {
            System.out.println("Avant sleep");
            Thread.sleep(temps);
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

    /**
     * Retourne une représentation textuelle de la créature.
     *
     * @return Chaîne contenant les informations de la créature.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" (").append(sexe).append(") : ")
                .append("Age: ").append(age).append(" ans");

        if (poids > 0) {
            sb.append(", Poids: ").append(poids).append(" kg");
        }
        if (taille > 0) {
            sb.append(", Taille: ").append(taille).append(" cm");
        }

        sb.append(", Moral: ").append(moral);

        if (!maladies.isEmpty()) {
            sb.append("\n   Maladies: ");
            for (Maladie maladie : maladies) {
                sb.append("\n   - ").append(maladie.getNomComplet());
            }
        }

        return sb.toString();
    }
}
