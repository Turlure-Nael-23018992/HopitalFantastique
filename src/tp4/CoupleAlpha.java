package tp4;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un couple alpha dans une meute.
 * Le couple est composé d'un mâle alpha et d'une femelle alpha.
 */
public class CoupleAlpha {

    // Attributs
    private Lycanthrope maleAlpha;       // Mâle du couple alpha
    private Lycanthrope femelleAlpha;   // Femelle du couple alpha

    /**
     * Constructeur pour initialiser le couple alpha.
     *
     * @param maleAlpha    Lycanthrope mâle du couple.
     * @param femelleAlpha Lycanthrope femelle du couple.
     * @throws IllegalArgumentException Si les sexes des lycanthropes sont incorrects.
     */
    public CoupleAlpha(Lycanthrope maleAlpha, Lycanthrope femelleAlpha) {
        if (maleAlpha.getSexe() != Lycanthrope.Sexe.MALE || femelleAlpha.getSexe() != Lycanthrope.Sexe.FEMELLE) {
            throw new IllegalArgumentException("Un couple α doit être constitué d'un mâle et d'une femelle.");
        }
        this.maleAlpha = maleAlpha;
        this.femelleAlpha = femelleAlpha;
    }

    /**
     * Récupère le mâle alpha.
     *
     * @return Le lycanthrope mâle alpha.
     */
    public Lycanthrope getMaleAlpha() {
        return maleAlpha;
    }

    /**
     * Récupère la femelle alpha.
     *
     * @return Le lycanthrope femelle alpha.
     */
    public Lycanthrope getFemelleAlpha() {
        return femelleAlpha;
    }

    /**
     * Affiche les caractéristiques du couple alpha.
     */
    public void afficherCaracteristiques() {
        System.out.println("Caractéristiques du couple α :");
        System.out.println("Mâle α : " + maleAlpha);
        System.out.println("Femelle α : " + femelleAlpha);
    }

    /**
     * Réalise une reproduction entre les deux membres du couple alpha.
     * Les nouveau-nés sont ajoutés à la portée et sont initialisés comme jeunes lycanthropes.
     *
     * @return Une liste de lycanthropes représentant la portée issue de la reproduction.
     */
    public List<Lycanthrope> reproduire() {
        List<Lycanthrope> portee = new ArrayList<>();
        int nbEnfants = (int) (Math.random() * 7) + 1; // Portée aléatoire entre 1 et 7

        for (int i = 0; i < nbEnfants; i++) {
            Lycanthrope enfant = new Lycanthrope(
                    "Nouveau-né" + (i + 1),
                    Math.random() < 0.5 ? Lycanthrope.Sexe.MALE : Lycanthrope.Sexe.FEMELLE, // Sexe aléatoire
                    Lycanthrope.CategorieAge.JEUNE,
                    10, // Force par défaut
                    0, // Facteur de domination initial
                    0, // Rang initial
                    0.1, // Facteur d'impétuosité par défaut
                    "Meute" // Meute par défaut
            );
            portee.add(enfant);
        }

        System.out.println("Reproduction réussie ! " + nbEnfants + " jeunes lycanthropes ont été créés.");
        return portee;
    }

    /**
     * Modifie le mâle alpha du couple.
     *
     * @param nouveauMale Le nouveau lycanthrope mâle alpha.
     * @throws IllegalArgumentException Si le sexe du lycanthrope n'est pas MALE.
     */
    public void setMaleAlpha(Lycanthrope nouveauMale) {
        if (nouveauMale.getSexe() != Lycanthrope.Sexe.MALE) {
            throw new IllegalArgumentException("Le mâle alpha doit être de sexe MALE.");
        }
        this.maleAlpha = nouveauMale;
    }

    /**
     * Modifie la femelle alpha du couple.
     *
     * @param nouvelleFemelle La nouvelle lycanthrope femelle alpha.
     * @throws IllegalArgumentException Si le sexe du lycanthrope n'est pas FEMELLE.
     */
    public void setFemelleAlpha(Lycanthrope nouvelleFemelle) {
        if (nouvelleFemelle.getSexe() != Lycanthrope.Sexe.FEMELLE) {
            throw new IllegalArgumentException("La femelle alpha doit être de sexe FEMELLE.");
        }
        this.femelleAlpha = nouvelleFemelle;
    }
}