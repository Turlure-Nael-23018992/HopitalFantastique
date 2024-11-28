package tp4;

import java.util.Objects;

public class Lycanthrope implements Comparable<Lycanthrope> {

    public enum Sexe {
        MALE, FEMELLE
    }

    public enum CategorieAge {
        JEUNE, ADULTE, VIEUX
    }

    private final String nom; // Nom du lycanthrope
    private final Sexe sexe; // Sexe
    private CategorieAge categorieAge; // Catégorie d'âge
    private final int force; // Force du lycanthrope
    private int facteurDomination; // Facteur de domination
    private int rang; // Rang de domination
    private final double facteurImpetuosite; // Facteur d'impétuosité (entre 0 et 1)
    private String meute; // Nom de la meute
    private double niveau; // Niveau calculé
    private boolean estHumain = false; // Indique si transformé en humain
    private boolean estMalade = false; // Indique si malade
    private boolean solitaire; // Indique si le lycanthrope est solitaire

    // Constructeur
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

    // Calcul du niveau
    private double calculerNiveau() {
        double coefficientAge = switch (categorieAge) {
            case JEUNE -> 1.0;
            case ADULTE -> 1.5;
            case VIEUX -> 2.0;
        };
        return coefficientAge * (force + facteurDomination * 0.5 + rang * 2);
    }

    // Méthode pour hurler
    public void hurler(String hurlement) {
        System.out.println(this.nom + " hurle : " + hurlement);
    }

    // Méthode pour entendre un hurlement
    public void entendreHurlement(String hurlement) {
        if (!estMalade) {
            System.out.println(this.nom + " entend : " + hurlement);
        } else {
            System.out.println(this.nom + " est trop malade pour entendre le hurlement.");
        }
    }

    // Méthode pour quitter la meute
    public void quitterMeute() {
        this.meute = "solitaire";
        this.solitaire = true;
        System.out.println(this.nom + " a quitté sa meute.");
    }

    // Méthode pour tenter une domination
    public void tenterDomination(Lycanthrope cible) {
        if (cible.sexe == Sexe.FEMELLE && cible.rang == 0) {
            System.out.println("Impossible de dominer la femelle α.");
            return;
        }

        double forceEffective = force * (1 + facteurImpetuosite);
        if (forceEffective > cible.force || cible.rang == 0) {
            System.out.println(this.nom + " domine " + cible.nom + " !");
            int ancienRang = this.rang;
            this.rang = cible.rang;
            cible.rang = ancienRang;

            this.facteurDomination++;
            cible.facteurDomination--;
        } else {
            System.out.println(this.nom + " a échoué à dominer " + cible.nom + " et subit des représailles !");
            this.facteurDomination--;
        }
    }

    // Méthode pour afficher les caractéristiques
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

    // Comparaison par rang
    @Override
    public int compareTo(Lycanthrope other) {
        return Integer.compare(this.rang, other.rang);
    }

    // Getters et setters nécessaires
    public String getNom() {
        return nom;
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

    public boolean isSolitaire() {
        return solitaire;
    }

    public boolean isMalade() {
        return estMalade;
    }

    public void setMalade(boolean malade) {
        this.estMalade = malade;
    }

    public String getMeute() {
        return meute;
    }

    public void setMeute(String meute) {
        this.meute = meute;
        this.solitaire = meute.equals("solitaire");
    }

    public Hurlement emettreHurlement(Hurlement.TypeHurlement type, String message) {
        System.out.println(this.nom + " émet un hurlement : [" + type + "] " + message);
        return new Hurlement(this, type, message);
    }

    // Méthode pour répondre à un hurlement
    public void repondreHurlement(Hurlement hurlement) {
        switch (hurlement.getType()) {
            case MEUTE -> {
                if (this.meute.equals(hurlement.getEmetteur().getMeute())) {
                    System.out.println(this.nom + " répond à l'appel de meute avec un hurlement similaire !");
                } else {
                    System.out.println(this.nom + " répond en représentant sa propre meute.");
                }
            }
            case DOMINATION -> {
                if (this.rang > hurlement.getEmetteur().getRang()) {
                    System.out.println(this.nom + " répond au hurlement de domination par un hurlement agressif !");
                } else {
                    System.out.println(this.nom + " se soumet au hurlement de domination.");
                }
            }
            case SOUMISSION -> {
                System.out.println(this.nom + " ignore le hurlement de soumission.");
            }
            case AGRESSIVITE -> {
                System.out.println(this.nom + " se prépare à un conflit en réponse à l'agressivité !");
            }
        }
    }

    public boolean seTransformerEnHumain() {
        System.out.println(this.nom + " tente de se transformer en humain.");
        double probQuitterMeute = this.niveau / 100.0; // Probabilité basée sur le niveau
        if (Math.random() < probQuitterMeute) {
            System.out.println(this.nom + " a quitté la meute et est désormais un humain libre.");
            return true; // Indique que le lycanthrope quitte la meute
        } else {
            System.out.println(this.nom + " reste dans la meute malgré sa transformation.");
            this.estHumain = true; // Transformé en humain mais reste dans la meute
            return false; // Le lycanthrope reste dans la meute
        }
    }

    public void vieillir() {
        System.out.println(this.nom + " vieillit.");
        switch (this.categorieAge) {
            case JEUNE:
                this.categorieAge = CategorieAge.ADULTE;
                System.out.println(this.nom + " est maintenant un adulte.");
                break;
            case ADULTE:
                this.categorieAge = CategorieAge.VIEUX;
                System.out.println(this.nom + " est maintenant vieux.");
                break;
            case VIEUX:
                System.out.println(this.nom + " est déjà vieux et ne peut pas vieillir davantage.");
                break;
        }
        recalculerNiveau(); // Recalculer le niveau après le vieillissement
    }

    // Méthode pour recalculer le niveau après un changement
    private void recalculerNiveau() {
        double coefficientAge = switch (categorieAge) {
            case JEUNE -> 1.0;
            case ADULTE -> 1.5;
            case VIEUX -> 2.0;
        };
        this.niveau = coefficientAge * (force + facteurDomination * 0.5 + rang * 2);
        System.out.println("Niveau recalculé pour " + this.nom + ": " + this.niveau);
    }
}