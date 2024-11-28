package tp4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Meute {
    // Attributs
    private CoupleAlpha coupleAlpha; // Couple α de la meute
    private final List<Lycanthrope> lycanthropes; // Liste des lycanthropes dans la meute

    // Constructeur
    public Meute() {
        this.lycanthropes = new ArrayList<>();
        this.coupleAlpha = null;
    }

    // Méthode pour ajouter un lycanthrope à la meute
    public void ajouterLycanthrope(Lycanthrope lycan) {
        if (!lycanthropes.contains(lycan)) {
            lycanthropes.add(lycan);
            lycan.setMeute("Meute");
            System.out.println("Le lycanthrope " + lycan.getNom() + " a été ajouté à la meute.");
        } else {
            System.out.println("Le lycanthrope " + lycan.getNom() + " appartient déjà à la meute.");
        }
    }

    // Méthode pour retirer un lycanthrope de la meute
    public void enleverLycanthrope(Lycanthrope lycan) {
        if (lycanthropes.remove(lycan)) {
            lycan.setMeute("solitaire");
            System.out.println("Le lycanthrope " + lycan.getNom() + " a été retiré de la meute.");
        } else {
            System.out.println("Le lycanthrope " + lycan.getNom() + " n'appartient pas à la meute.");
        }
    }

    // Méthode pour définir un couple α
    public void definirCoupleAlpha(Lycanthrope male, Lycanthrope femelle) {
        if (male.getSexe() == Lycanthrope.Sexe.MALE && femelle.getSexe() == Lycanthrope.Sexe.FEMELLE) {
            this.coupleAlpha = new CoupleAlpha(male, femelle);
            System.out.println("Couple α défini :");
            coupleAlpha.afficherCaracteristiques();
        } else {
            System.out.println("Erreur : Le couple α doit être constitué d'un mâle et d'une femelle.");
        }
    }

    // Méthode pour afficher les caractéristiques de la meute
    public void afficherCaracteristiques() {
        System.out.println("Caractéristiques de la meute :");
        if (coupleAlpha != null) {
            coupleAlpha.afficherCaracteristiques();
        } else {
            System.out.println("Pas de couple α.");
        }
        System.out.println("Nombre de lycanthropes dans la meute : " + lycanthropes.size());
    }

    // Méthode pour afficher les lycanthropes dans la meute
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

    // Méthode pour lancer une reproduction
    public void lancerReproduction() {
        if (coupleAlpha != null) {
            List<Lycanthrope> portee = coupleAlpha.reproduire();
            lycanthropes.addAll(portee);
        } else {
            System.out.println("Impossible de lancer la reproduction : pas de couple α.");
        }
    }

    // Méthode pour créer une nouvelle hiérarchie et définir automatiquement un couple α
    public void creerNouvelleHierarchie(List<Lycanthrope> nouveauxLycanthropes) {
        this.lycanthropes.clear();
        this.lycanthropes.addAll(nouveauxLycanthropes);
        definirCoupleAlphaAutomatiquement();
        System.out.println("Nouvelle hiérarchie créée avec " + nouveauxLycanthropes.size() + " lycanthropes.");
    }

    // Méthode pour définir automatiquement un couple α
    private void definirCoupleAlphaAutomatiquement() {
        Lycanthrope nouveauMaleAlpha = lycanthropes.stream()
                .filter(l -> l.getSexe() == Lycanthrope.Sexe.MALE)
                .max((l1, l2) -> Integer.compare(l1.getRang(), l2.getRang()))
                .orElse(null);

        Lycanthrope nouvelleFemelleAlpha = lycanthropes.stream()
                .filter(l -> l.getSexe() == Lycanthrope.Sexe.FEMELLE)
                .max((l1, l2) -> Integer.compare(l1.getRang(), l2.getRang()))
                .orElse(null);

        if (nouveauMaleAlpha != null && nouvelleFemelleAlpha != null) {
            definirCoupleAlpha(nouveauMaleAlpha, nouvelleFemelleAlpha);
        } else {
            System.out.println("Impossible de définir automatiquement un couple α : critères non remplis.");
        }
    }

    // Méthode pour décrémenter les rangs de domination des lycanthropes
    public void diminuerRangsDomination() {
        for (Lycanthrope lycan : lycanthropes) {
            int nouveauRang = Math.max(0, lycan.getRang() - 1); // Le rang ne peut pas être négatif
            lycan.setRang(nouveauRang);
        }
        definirCoupleAlphaAutomatiquement(); // Mise à jour automatique du couple α après modification des rangs
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

    public void propagerHurlement(Hurlement hurlement) {
        System.out.println("\n=== Propagation du hurlement dans la meute ===");
        for (Lycanthrope lycan : lycanthropes) {
            if (!lycan.equals(hurlement.getEmetteur())) {
                lycan.repondreHurlement(hurlement);
            }
        }
    }

    public void gererTransformation(Lycanthrope lycan) {
        if (lycan.seTransformerEnHumain()) {
            // Si le lycanthrope quitte la meute
            this.enleverLycanthrope(lycan);
            System.out.println("Réorganisation de la meute suite au départ de " + lycan.getNom() + ".");
            definirCoupleAlphaAutomatiquement(); // Mise à jour du couple α si nécessaire
        }
    }

    // Méthode pour transformer toute la meute en humain
    public void transformerMeute() {
        System.out.println("\n=== Transformation de la meute en humains ===");
        Iterator<Lycanthrope> iterator = lycanthropes.iterator();
        while (iterator.hasNext()) {
            Lycanthrope lycan = iterator.next();
            if (lycan.seTransformerEnHumain()) {
                iterator.remove(); // Enlever les lycanthropes quittant la meute
                System.out.println(lycan.getNom() + " a quitté la meute.");
            }
        }
        definirCoupleAlphaAutomatiquement(); // Mise à jour du couple α après transformations
    }
}