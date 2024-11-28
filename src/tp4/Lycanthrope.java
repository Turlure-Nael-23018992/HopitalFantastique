package tp4;

import java.util.Objects;

public class Lycanthrope {

    public enum Sexe {
        MALE, FEMELLE
    }

    public enum CategorieAge {
        JEUNE, ADULTE, VIEUX
    }

    private final Sexe sexe;
    private final CategorieAge categorieAge;
    private final int force;
    private final int facteurDomination;
    private final int rang;
    private final double facteurImpetuosite;
    private String meute;
    private final double niveau;
    private boolean estHumain = false;
    private boolean estMalade = false;

    public Lycanthrope(Sexe sexe, CategorieAge categorieAge, int force, int facteurDomination, int rang, double facteurImpetuosite, String meute) {
        if (force <= 0) throw new IllegalArgumentException("La force doit être positive.");
        if (rang < 0) throw new IllegalArgumentException("Le rang ne peut pas être négatif.");
        if (facteurImpetuosite < 0 || facteurImpetuosite > 1) throw new IllegalArgumentException("Le facteur d'impétuosité doit être entre 0 et 1.");
        this.sexe = Objects.requireNonNull(sexe);
        this.categorieAge = Objects.requireNonNull(categorieAge);
        this.force = force;
        this.facteurDomination = facteurDomination;
        this.rang = rang;
        this.facteurImpetuosite = facteurImpetuosite;
        this.meute = Objects.requireNonNull(meute);
        this.niveau = calculerNiveau();
    }

    private double calculerNiveau() {
        double coefficientAge = switch (categorieAge) {
            case JEUNE -> 1.0;
            case ADULTE -> 1.5;
            case VIEUX -> 2.0;
        };
        return coefficientAge * (force + facteurDomination * 0.5 + rang * 2);
    }

    @Override
    public int compareTo(Lycanthrope other) {
        return Integer.compare(this.rang, other.rang);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lycanthrope that = (Lycanthrope) o;
        return rang == that.rang &&
                sexe == that.sexe &&
                categorieAge == that.categorieAge &&
                meute.equals(that.meute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sexe, categorieAge, rang, meute);
    }

    public void afficherCaracteristiques() {
        System.out.println(this);
    }

    public void hurler(String hurlement) {
        System.out.println("Le lycanthrope hurle : " + hurlement);
    }

    public void entendreHurlement(String hurlement) {
        if (!estMalade) {
            System.out.println("Le lycanthrope entend : " + hurlement);
        } else {
            System.out.println("Le lycanthrope est trop malade pour entendre le hurlement.");
        }
    }

    public void quitterMeute() {
        this.meute = "solitaire";
        System.out.println("Le lycanthrope a quitté sa meute.");
    }

    public void seTransformerEnHumain() {
        estHumain = true;
        System.out.println("Le lycanthrope s'est transformé en humain.");
    }

    public Sexe getSexe() {
        return sexe;
    }

    public CategorieAge getCategorieAge() {
        return categorieAge;
    }

    public int getForce() {
        return force;
    }

    public int getFacteurDomination() {
        return facteurDomination;
    }

    public int getRang() {
        return rang;
    }

    public double getFacteurImpetuosite() {
        return facteurImpetuosite;
    }

    public String getMeute() {
        return meute;
    }

    public double getNiveau() {
        return niveau;
    }

    public boolean isEstHumain() {
        return estHumain;
    }

    public void setMalade(boolean malade) {
        this.estMalade = malade;
    }

    @Override
    public String toString() {
        return "Lycanthrope{" +
                "sexe=" + sexe +
                ", categorieAge=" + categorieAge +
                ", force=" + force +
                ", facteurDomination=" + facteurDomination +
                ", rang=" + rang +
                ", facteurImpetuosite=" + facteurImpetuosite +
                ", meute='" + meute + '\'' +
                ", niveau=" + niveau +
                ", estHumain=" + estHumain +
                '}';
    }
}