package tp4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Représente une meute de lycanthropes avec un couple alpha et des interactions internes.
 */
public class Meute {
    private CoupleAlpha coupleAlpha;            // Couple α de la meute
    private final List<Lycanthrope> lycanthropes; // Liste des lycanthropes de la meute

    /**
     * Constructeur pour initialiser une meute vide.
     */
    public Meute() {
        this.lycanthropes = new ArrayList<>();
        this.coupleAlpha = null;
    }

    /**
     * Ajoute un lycanthrope à la meute.
     *
     * @param lycan Le lycanthrope à ajouter.
     */
    public void ajouterLycanthrope(Lycanthrope lycan) {
        if (!lycanthropes.contains(lycan)) {
            lycanthropes.add(lycan);
            lycan.setMeute("Meute");
            System.out.println("Le lycanthrope " + lycan.getNom() + " a été ajouté à la meute.");
        } else {
            System.out.println("Le lycanthrope " + lycan.getNom() + " appartient déjà à la meute.");
        }
    }

    /**
     * Retire un lycanthrope de la meute.
     *
     * @param lycan Le lycanthrope à retirer.
     */
    public void enleverLycanthrope(Lycanthrope lycan) {
        if (lycanthropes.remove(lycan)) {
            lycan.setMeute("solitaire");
            System.out.println("Le lycanthrope " + lycan.getNom() + " a été retiré de la meute.");
        } else {
            System.out.println("Le lycanthrope " + lycan.getNom() + " n'appartient pas à la meute.");
        }
    }

    /**
     * Définit un couple alpha (un mâle et une femelle) pour la meute.
     *
     * @param male    Le lycanthrope mâle.
     * @param femelle Le lycanthrope femelle.
     */
    public void definirCoupleAlpha(Lycanthrope male, Lycanthrope femelle) {
        if (male.getSexe() == Lycanthrope.Sexe.MALE && femelle.getSexe() == Lycanthrope.Sexe.FEMELLE) {
            this.coupleAlpha = new CoupleAlpha(male, femelle);
            System.out.println("Couple α défini :");
            coupleAlpha.afficherCaracteristiques();
        } else {
            System.out.println("Erreur : Le couple α doit être constitué d'un mâle et d'une femelle.");
        }
    }

    /**
     * Affiche les caractéristiques de la meute.
     */
    public void afficherCaracteristiques() {
        System.out.println("Caractéristiques de la meute :");
        if (coupleAlpha != null) {
            coupleAlpha.afficherCaracteristiques();
        } else {
            System.out.println("Pas de couple α.");
        }
        System.out.println("Nombre de lycanthropes dans la meute : " + lycanthropes.size());
    }

    /**
     * Affiche les caractéristiques de tous les lycanthropes dans la meute.
     */
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

    /**
     * Lance une reproduction entre le couple alpha.
     */
    public void lancerReproduction() {
        if (coupleAlpha != null) {
            List<Lycanthrope> portee = coupleAlpha.reproduire();
            lycanthropes.addAll(portee);
        } else {
            System.out.println("Impossible de lancer la reproduction : pas de couple α.");
        }
    }

    /**
     * Crée une nouvelle hiérarchie en réorganisant les lycanthropes.
     *
     * @param nouveauxLycanthropes Liste des nouveaux lycanthropes.
     */
    public void creerNouvelleHierarchie(List<Lycanthrope> nouveauxLycanthropes) {
        this.lycanthropes.clear();
        this.lycanthropes.addAll(nouveauxLycanthropes);
        definirCoupleAlphaAutomatiquement();
        System.out.println("Nouvelle hiérarchie créée avec " + nouveauxLycanthropes.size() + " lycanthropes.");
    }

    /**
     * Définit automatiquement un couple alpha basé sur les rangs hiérarchiques.
     */
    private void definirCoupleAlphaAutomatiquement() {
        Lycanthrope maleAlpha = lycanthropes.stream()
                .filter(l -> l.getSexe() == Lycanthrope.Sexe.MALE)
                .max((l1, l2) -> Integer.compare(l1.getRang(), l2.getRang()))
                .orElse(null);

        Lycanthrope femelleAlpha = lycanthropes.stream()
                .filter(l -> l.getSexe() == Lycanthrope.Sexe.FEMELLE)
                .max((l1, l2) -> Integer.compare(l1.getRang(), l2.getRang()))
                .orElse(null);

        if (maleAlpha != null && femelleAlpha != null) {
            definirCoupleAlpha(maleAlpha, femelleAlpha);
        } else {
            System.out.println("Impossible de définir automatiquement un couple α : critères non remplis.");
        }
    }

    /**
     * Diminue les rangs de domination de tous les lycanthropes de la meute.
     */
    public void diminuerRangsDomination() {
        for (Lycanthrope lycan : lycanthropes) {
            int nouveauRang = Math.max(0, lycan.getRang() - 1); // Le rang ne peut pas être négatif
            lycan.setRang(nouveauRang);
        }
        definirCoupleAlphaAutomatiquement(); // Mise à jour automatique du couple α après modification des rangs
        System.out.println("Rangs de domination diminués pour tous les lycanthropes.");
    }

    /**
     * Propage un hurlement dans la meute.
     *
     * @param hurlement Le hurlement à propager.
     */
    public void propagerHurlement(Hurlement hurlement) {
        System.out.println("\n=== Propagation du hurlement dans la meute ===");
        for (Lycanthrope lycan : lycanthropes) {
            if (!lycan.equals(hurlement.getEmetteur())) {
                lycan.repondreHurlement(hurlement);
            }
        }
    }

    /**
     * Gère la transformation d'un lycanthrope en humain.
     *
     * @param lycan Le lycanthrope à transformer.
     */
    public void gererTransformation(Lycanthrope lycan) {
        if (lycan.seTransformerEnHumain()) {
            enleverLycanthrope(lycan);
            System.out.println("Réorganisation de la meute suite au départ de " + lycan.getNom() + ".");
            definirCoupleAlphaAutomatiquement(); // Mise à jour du couple α si nécessaire
        }
    }

    /**
     * Transforme toute la meute en humains.
     */
    public void transformerMeute() {
        System.out.println("\n=== Transformation de la meute en humains ===");
        Iterator<Lycanthrope> iterator = lycanthropes.iterator();
        while (iterator.hasNext()) {
            Lycanthrope lycan = iterator.next();
            if (lycan.seTransformerEnHumain()) {
                iterator.remove();
                System.out.println(lycan.getNom() + " a quitté la meute.");
            }
        }
        definirCoupleAlphaAutomatiquement(); // Mise à jour du couple α après transformations
    }

    /**
     * Getter pour les lycanthropes de la meute.
     *
     * @return Liste des lycanthropes.
     */
    public List<Lycanthrope> getLycanthropes() {
        return lycanthropes;
    }
}