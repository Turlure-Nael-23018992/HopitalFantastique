package tp4;

import java.util.ArrayList;
import java.util.List;

public class CoupleAlpha {
    // Attributs
    private Lycanthrope maleAlpha;
    private Lycanthrope femelleAlpha;

    // Constructeur
    public CoupleAlpha(Lycanthrope maleAlpha, Lycanthrope femelleAlpha) {
        if (maleAlpha.getSexe() != Lycanthrope.Sexe.MALE || femelleAlpha.getSexe() != Lycanthrope.Sexe.FEMELLE) {
            throw new IllegalArgumentException("Un couple α doit être constitué d'un mâle et d'une femelle.");
        }
        this.maleAlpha = maleAlpha;
        this.femelleAlpha = femelleAlpha;
    }

    // Getter pour le mâle α
    public Lycanthrope getMaleAlpha() {
        return maleAlpha;
    }

    // Getter pour la femelle α
    public Lycanthrope getFemelleAlpha() {
        return femelleAlpha;
    }

    // Méthode pour afficher les caractéristiques du couple
    public void afficherCaracteristiques() {
        System.out.println("Caractéristiques du couple α :");
        System.out.println("Mâle α : " + maleAlpha);
        System.out.println("Femelle α : " + femelleAlpha);
    }

    // Méthode pour réaliser une reproduction
    public List<Lycanthrope> reproduire() {
        List<Lycanthrope> portee = new ArrayList<>();
        int nbEnfants = (int) (Math.random() * 7) + 1; // Portée de 1 à 7
        for (int i = 0; i < nbEnfants; i++) {
            Lycanthrope enfant = new Lycanthrope(
                    "Nouveau-né" + (i + 1),
                    Math.random() < 0.5 ? Lycanthrope.Sexe.MALE : Lycanthrope.Sexe.FEMELLE, // Sexe aléatoire
                    Lycanthrope.CategorieAge.JEUNE,
                    10, // Force par défaut
                    0, // Facteur de domination initial
                    0, // Rang initial
                    0.1, // Facteur d'impétuosité par défaut
                    "Meute" // Appartient à la meute
            );
            portee.add(enfant);
        }
        System.out.println("Reproduction réussie ! " + nbEnfants + " jeunes lycanthropes ont été créés.");
        return portee;
    }
}