package tp4;

import java.util.Objects;

/**
 * Représente un lycanthrope avec ses caractéristiques biologiques et sociales.
 */
public class Lycanthrope implements Comparable<Lycanthrope> {

    // Enumérations pour le sexe et la catégorie d'âge
    public enum Sexe { MALE, FEMELLE }
    public enum CategorieAge { JEUNE, ADULTE, VIEUX }

    private final String nom;               // Nom du lycanthrope
    private final Sexe sexe;                // Sexe du lycanthrope
    private CategorieAge categorieAge;      // Catégorie d'âge (jeune, adulte, vieux)
    private final int force;                // Force physique
    private int facteurDomination;          // Facteur de domination
    private int rang;                       // Rang dans la hiérarchie
    private final double facteurImpetuosite;// Impétuosité (entre 0 et 1)
    private String meute;                   // Nom de la meute
    private double niveau;                  // Niveau calculé
    private boolean estHumain = false;      // Indique si transformé en humain
    private boolean estMalade = false;      // Indique si malade
    private boolean solitaire;              // Indique si le lycanthrope est solitaire

    /**
     * Constructeur pour initialiser un lycanthrope.
     *
     * @param nom               Nom du lycanthrope
     * @param sexe              Sexe (MALE/FEMELLE)
     * @param categorieAge      Catégorie d'âge
     * @param force             Force physique (>0)
     * @param facteurDomination Facteur de domination
     * @param rang              Rang hiérarchique (>=0)
     * @param facteurImpetuosite Facteur d'impétuosité (0-1)
     * @param meute             Nom de la meute
     */
    public Lycanthrope(String nom, Sexe sexe, CategorieAge categorieAge, int force, int facteurDomination, int rang, double facteurImpetuosite, String meute) {
        if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Le nom ne peut pas être vide.");
        if (force <= 0) throw new IllegalArgumentException("La force doit être positive.");
        if (rang < 0) throw new IllegalArgumentException("Le rang ne peut pas être négatif.");
        if (facteurImpetuosite < 0 || facteurImpetuosite > 1)
            throw new IllegalArgumentException("Le facteur d'impétuosité doit être entre 0 et 1.");

        this.nom = nom.trim();
        this.sexe = Objects.requireNonNull(sexe);
        this.categorieAge = Objects.requireNonNull(categorieAge);
        this.force = force;
        this.facteurDomination = facteurDomination;
        this.rang = rang;
        this.facteurImpetuosite = facteurImpetuosite;
        this.meute = (meute != null) ? meute : "solitaire";
        this.niveau = calculerNiveau();
        this.solitaire = this.meute.equals("solitaire");
    }

    /**
     * Calcule le niveau en fonction de l'âge, la force, et le rang.
     *
     * @return Le niveau du lycanthrope.
     */
    private double calculerNiveau() {
        double coefficientAge = switch (categorieAge) {
            case JEUNE -> 1.0;
            case ADULTE -> 1.5;
            case VIEUX -> 2.0;
        };
        return coefficientAge * (force + facteurDomination * 0.5 + rang * 2);
    }

    /**
     * Tente de transformer le lycanthrope en humain.
     *
     * @return true si le lycanthrope quitte la meute pour devenir humain, false s'il reste dans la meute.
     */
    public boolean seTransformerEnHumain() {
        System.out.println(this.nom + " tente de se transformer en humain.");

        double probabiliteQuitterMeute = this.niveau / 100.0; // Probabilité basée sur le niveau
        if (Math.random() < probabiliteQuitterMeute) {
            System.out.println(this.nom + " a quitté la meute et est désormais un humain libre.");
            this.estHumain = true; // Transformé en humain
            return true; // Le lycanthrope quitte la meute
        } else {
            System.out.println(this.nom + " reste dans la meute malgré sa transformation.");
            this.estHumain = true; // Transformé en humain mais reste dans la meute
            return false; // Le lycanthrope reste dans la meute
        }
    }

    /**
     * Permet au lycanthrope de hurler un message.
     *
     * @param message Message du hurlement.
     */
    public void hurler(String message) {
        System.out.println(this.nom + " hurle : " + message);
    }

    /**
     * Répond à un hurlement reçu en fonction de son type.
     *
     * @param hurlement Le hurlement reçu.
     */
    public void repondreHurlement(Hurlement hurlement) {
        if (this.equals(hurlement.getEmetteur())) {
            System.out.println(this.nom + " ne répond pas à son propre hurlement.");
            return;
        }

        System.out.println(this.nom + " réagit au hurlement reçu de " + hurlement.getEmetteur().getNom());
        switch (hurlement.getType()) {
            case MEUTE -> {
                if (this.meute.equals(hurlement.getEmetteur().getMeute())) {
                    System.out.println(this.nom + " répond à l'appel de meute avec un hurlement similaire !");
                } else {
                    System.out.println(this.nom + " ignore l'appel de meute.");
                }
            }
            case DOMINATION -> {
                if (this.rang > hurlement.getEmetteur().getRang()) {
                    System.out.println(this.nom + " répond au hurlement de domination par un hurlement agressif !");
                } else {
                    System.out.println(this.nom + " se soumet.");
                }
            }
            case SOUMISSION -> System.out.println(this.nom + " ignore le hurlement de soumission.");
            case AGRESSIVITE -> System.out.println(this.nom + " se prépare à un conflit !");
            default -> System.out.println(this.nom + " est confus face à ce hurlement.");
        }
    }

    /**
     * Tente de dominer un autre lycanthrope.
     *
     * @param cible Le lycanthrope cible.
     */
    public void tenterDomination(Lycanthrope cible) {
        // Vérifier si la cible est une femelle α
        if (cible.getRang() == 0 && cible.getSexe() == Sexe.FEMELLE) {
            System.out.println(this.nom + " ne peut pas dominer la femelle α.");
            return;
        }

        // Calculer la force effective de l'agresseur
        double forceEffective = this.force * (1 + this.facteurImpetuosite);

        // Vérifier les conditions de domination
        if (forceEffective >= cible.force && (this.niveau > cible.niveau || cible.getRang() == 0)) {
            System.out.println(this.nom + " domine " + cible.getNom() + " !");

            // Augmenter le facteur de domination de l'agresseur
            this.facteurDomination++;

            // Diminuer le facteur de domination de la cible
            cible.facteurDomination = Math.max(0, cible.facteurDomination - 1);

            // Échanger les rangs
            int ancienRang = this.rang;
            this.rang = cible.getRang();
            cible.setRang(ancienRang);

            // Recalculer les niveaux
            this.recalculerNiveau();
            cible.recalculerNiveau();
        } else {
            // La domination échoue, la cible devient agressive
            System.out.println(this.nom + " a échoué à dominer " + cible.getNom() + ".");
            cible.reagirAgression();
            this.facteurDomination = Math.max(0, this.facteurDomination - 1);
        }
    }

    /**
     * Réagit à une tentative d'agression par un autre lycanthrope.
     */
    public void reagirAgression() {
        System.out.println(this.nom + " devient agressif envers l'attaquant !");
        // Le lycanthrope agressé peut perdre un peu de son facteur de domination
        this.facteurDomination = Math.max(0, this.facteurDomination - 1);
    }

    /**
     * Vieillit le lycanthrope, modifiant sa catégorie d'âge.
     */
    public void vieillir() {
        System.out.println(this.nom + " vieillit.");
        switch (this.categorieAge) {
            case JEUNE -> {
                this.categorieAge = CategorieAge.ADULTE;
                System.out.println(this.nom + " est maintenant adulte.");
            }
            case ADULTE -> {
                this.categorieAge = CategorieAge.VIEUX;
                System.out.println(this.nom + " est maintenant vieux.");
            }
            case VIEUX -> System.out.println(this.nom + " est déjà vieux.");
        }
        recalculerNiveau();
    }

    /**
     * Recalcule le niveau du lycanthrope après un changement.
     */
    private void recalculerNiveau() {
        this.niveau = calculerNiveau();
        System.out.println("Nouveau niveau pour " + this.nom + ": " + this.niveau);
    }

    // Getters et setters nécessaires pour l'interaction avec les autres classes
    public String getNom() {
        return nom;
    }

    public String getMeute() {
        return meute;
    }

    public void setMeute(String meute) {
        this.meute = meute;
        this.solitaire = meute.equals("solitaire");
    }

    public boolean isSolitaire() {
        return solitaire;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public Sexe getSexe() {
        return sexe;
    }

    @Override
    public String toString() {
        return "Lycanthrope{" +
                "nom='" + nom + '\'' +
                ", sexe=" + sexe +
                ", categorieAge=" + categorieAge +
                ", force=" + force +
                ", facteurDomination=" + facteurDomination +
                ", rang=" + rang +
                ", facteurImpetuosite=" + facteurImpetuosite +
                ", meute='" + meute + '\'' +
                ", niveau=" + niveau +
                ", estHumain=" + estHumain +
                ", solitaire=" + solitaire +
                '}';
    }

    @Override
    public int compareTo(Lycanthrope o) {
        return Integer.compare(this.rang, o.rang);
    }
}