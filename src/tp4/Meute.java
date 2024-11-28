package tp4;

import java.util.ArrayList;
import java.util.List;

public class Meute {
    // Attributs
    private Lycanthrope maleAlpha; // Mâle alpha de la meute
    private Lycanthrope femelleAlpha; // Femelle alpha de la meute
    private final List<Lycanthrope> lycanthropes; // Liste des lycanthropes de la meute

    // Constructeur
    public Meute() {
        this.lycanthropes = new ArrayList<>();
        this.maleAlpha = null;
        this.femelleAlpha = null;
    }

    // Méthode pour afficher les caractéristiques de la meute
    public void afficherCaracteristiques() {
        System.out.println("Caractéristiques de la meute :");
        if (maleAlpha != null && femelleAlpha != null) {
            System.out.println("Couple α : " + maleAlpha.getNom() + " et " + femelleAlpha.getNom());
        } else {
            System.out.println("Pas de couple α.");
        }
        System.out.println("Nombre de lycanthropes dans la meute : " + lycanthropes.size());
    }

    // Méthode pour afficher les caractéristiques des lycanthropes
    public void afficherLycanthropes() {
        if (lycanthropes.isEmpty()) {
            System.out.println("La meute ne contient aucun lycanthrope.");
        } else {
            System.out.println("Caractéristiques des lycanthropes :");
            for (Lycanthrope lycan : lycanthropes) {
                System.out.println(lycan);
            }
        }
    }

    // Méthode pour créer une nouvelle hiérarchie de meute
    public void creerNouvelleHierarchie(List<Lycanthrope> nouveauxLycanthropes) {
        this.lycanthropes.clear();
        this.lycanthropes.addAll(nouveauxLycanthropes);
        definirCoupleAlphaAutomatiquement();
        System.out.println("Nouvelle hiérarchie créée avec " + nouveauxLycanthropes.size() + " lycanthropes.");
    }

    // Méthode pour définir le couple α automatiquement
    private void definirCoupleAlphaAutomatiquement() {
        Lycanthrope nouveauMaleAlpha = lycanthropes.stream()
                .filter(l -> l.getSexe() == Lycanthrope.Sexe.MALE && l.isSolitaire() == false)
                .max((l1, l2) -> Integer.compare(l1.getRang(), l2.getRang()))
                .orElse(null);

        Lycanthrope nouvelleFemelleAlpha = lycanthropes.stream()
                .filter(l -> l.getSexe() == Lycanthrope.Sexe.FEMELLE && l.isSolitaire() == false)
                .max((l1, l2) -> Integer.compare(l1.getRang(), l2.getRang()))
                .orElse(null);

        this.maleAlpha = nouveauMaleAlpha;
        this.femelleAlpha = nouvelleFemelleAlpha;
    }

    // Méthode pour constituer un couple α
    public void definirCoupleAlpha(Lycanthrope male, Lycanthrope femelle) {
        if (male.getSexe() == Lycanthrope.Sexe.MALE && femelle.getSexe() == Lycanthrope.Sexe.FEMELLE) {
            this.maleAlpha = male;
            this.femelleAlpha = femelle;
            System.out.println("Couple α défini : " + male.getNom() + " et " + femelle.getNom());
        } else {
            System.out.println("Erreur : Le couple α doit être constitué d'un mâle et d'une femelle.");
        }
    }

    // Méthode pour lancer la reproduction des lycanthropes
    public void lancerReproduction() {
        if (maleAlpha != null && femelleAlpha != null) {
            int nbEnfants = (int) (Math.random() * 7) + 1; // Portée de 1 à 7
            for (int i = 0; i < nbEnfants; i++) {
                Lycanthrope nouveauLycan = new Lycanthrope(
                        "Nouveau-né" + (i + 1),
                        Lycanthrope.Sexe.MALE,
                        Lycanthrope.CategorieAge.JEUNE,
                        10,
                        0,
                        0,
                        0.1,
                        "Meute");
                lycanthropes.add(nouveauLycan);
            }
            System.out.println("Reproduction réussie ! " + nbEnfants + " jeunes lycanthropes ont été ajoutés.");
        } else {
            System.out.println("Impossible de lancer la reproduction : pas de couple α.");
        }
    }

    // Méthode pour décrémenter les rangs de domination
    public void diminuerRangsDomination() {
        for (Lycanthrope lycan : lycanthropes) {
            int nouveauRang = Math.max(0, lycan.getRang() - 1); // Le rang ne peut pas être négatif
            lycan.setRang(nouveauRang);
        }
        System.out.println("Rangs de domination diminués pour tous les lycanthropes.");
    }

    // Méthode pour déclarer un lycanthrope ω (rang 0)
    public void declarerOmega(Lycanthrope lycan) {
        if (lycanthropes.contains(lycan)) {
            lycan.setRang(0);
            System.out.println("Le lycanthrope " + lycan.getNom() + " est maintenant ω.");
        } else {
            System.out.println("Le lycanthrope n'appartient pas à la meute.");
        }
    }

    // Méthode pour ajouter un lycanthrope
    public void ajouterLycanthrope(Lycanthrope lycan) {
        if (!lycanthropes.contains(lycan)) {
            lycanthropes.add(lycan);
            lycan.setMeute("Meute");
            System.out.println("Le lycanthrope " + lycan.getNom() + " a été ajouté à la meute.");
        } else {
            System.out.println("Le lycanthrope " + lycan.getNom() + " appartient déjà à la meute.");
        }
    }

    // Méthode pour enlever un lycanthrope
    public void enleverLycanthrope(Lycanthrope lycan) {
        if (lycanthropes.remove(lycan)) {
            lycan.setMeute("solitaire");
            System.out.println("Le lycanthrope " + lycan.getNom() + " a été retiré de la meute.");
        } else {
            System.out.println("Le lycanthrope " + lycan.getNom() + " n'appartient pas à la meute.");
        }
    }
}