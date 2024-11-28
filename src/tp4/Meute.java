import java.util.ArrayList;
import java.util.List;

public class Meute {
    // Attributs
    private Lycanthrope maleAlpha;
    private Lycanthrope femelleAlpha;
    private List<Lycanthrope> lycanthropes;

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
        this.lycanthropes = nouveauxLycanthropes;
        System.out.println("Nouvelle hiérarchie créée avec " + nouveauxLycanthropes.size() + " lycanthropes.");
    }

    // Méthode pour constituer un couple α
    public void definirCoupleAlpha(Lycanthrope male, Lycanthrope femelle) {
        this.maleAlpha = male;
        this.femelleAlpha = femelle;
        System.out.println("Couple α défini : " + male.getNom() + " et " + femelle.getNom());
    }

    // Méthode pour lancer la reproduction des lycanthropes
    public void lancerReproduction() {
        if (maleAlpha != null && femelleAlpha != null) {
            Lycanthrope nouveauLycan = new Lycanthrope("Nouveau-né");
            lycanthropes.add(nouveauLycan);
            System.out.println("Reproduction réussie ! Un nouveau lycanthrope a été ajouté.");
        } else {
            System.out.println("Impossible de lancer la reproduction : pas de couple α.");
        }
    }

    // Méthode pour décrémenter les rangs de domination
    public void diminuerRangsDomination() {
        for (Lycanthrope lycan : lycanthropes) {
            lycan.decrementerRangDomination();
        }
        System.out.println("Rangs de domination diminués pour tous les lycanthropes.");
    }

    // Méthode pour déclarer un lycanthrope ω
    public void declarerOmega(Lycanthrope lycan) {
        lycan.setRangDomination(0);
        System.out.println("Le lycanthrope " + lycan.getNom() + " est maintenant ω.");
    }

    // Méthode pour ajouter un lycanthrope
    public void ajouterLycanthrope(Lycanthrope lycan) {
        lycanthropes.add(lycan);
        System.out.println("Le lycanthrope " + lycan.getNom() + " a été ajouté à la meute.");
    }

    // Méthode pour enlever un lycanthrope
    public void enleverLycanthrope(Lycanthrope lycan) {
        if (lycanthropes.remove(lycan)) {
            System.out.println("Le lycanthrope " + lycan.getNom() + " a été retiré de la meute.");
        } else {
            System.out.println("Le lycanthrope " + lycan.getNom() + " n'appartient pas à la meute.");
        }
    }
}