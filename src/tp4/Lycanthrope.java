package tp4;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Lycanthrope implements Comparable<Lycanthrope> {

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
    private int rang;  // Enlevez le final ici
    private final double facteurImpetuosite;
    private String meute;
    private final double niveau;
    private boolean estHumain = false;
    private boolean estMalade = false;
    private boolean solitaire;  // Attribut pour savoir si le lycanthrope est solitaire


    private Lycanthrope maleAlpha;  // Le mâle α
    private Lycanthrope femelleAlpha;  // La femelle α

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
        this.solitaire = true;
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
        return force == that.force &&
                facteurDomination == that.facteurDomination &&
                rang == that.rang &&
                Double.compare(that.facteurImpetuosite, facteurImpetuosite) == 0 &&
                Objects.equals(sexe, that.sexe) &&
                Objects.equals(categorieAge, that.categorieAge) &&
                Objects.equals(meute, that.meute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sexe, categorieAge, force, facteurDomination, rang, facteurImpetuosite, meute);
    }

    // Getter et Setter pour `rang`
    public int getRang() {
        return rang;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public boolean isSolitaire() {
        return solitaire;
    }

    public void devenirNonSolitaire() {
        this.solitaire = false;  // Passer un lycanthrope de solitaire à non solitaire
    }

    // Nouvelle méthode pour définir le couple alpha
    public void definirCoupleAlpha(List<Lycanthrope> meute) {
        Optional<Lycanthrope> maleAlphaOpt = meute.stream()
                .filter(l -> l.sexe == Sexe.MALE && l.categorieAge == CategorieAge.ADULTE)
                .max((l1, l2) -> Integer.compare(l1.force, l2.force));

        Optional<Lycanthrope> femelleAlphaOpt = meute.stream()
                .filter(l -> l.sexe == Sexe.FEMELLE && l.categorieAge == CategorieAge.ADULTE)
                .max((l1, l2) -> Double.compare(l1.niveau, l2.niveau));

        maleAlphaOpt.ifPresent(m -> this.maleAlpha = m);
        femelleAlphaOpt.ifPresent(f -> this.femelleAlpha = f);
    }

    // Méthode pour gérer un conflit de domination
    public void contestationDomination(Lycanthrope challenger) {
        if (challenger.sexe == Sexe.MALE && challenger.categorieAge == CategorieAge.ADULTE) {
            if (challenger.force > maleAlpha.force) {
                maleAlpha = challenger;
                definirCoupleAlpha(List.of(maleAlpha, femelleAlpha));
            }
        }
    }

    // Méthode pour gérer la déchéance de la femelle alpha
    public void femelleDechue() {
        if (femelleAlpha != null) {
            femelleAlpha.setRang(maleAlpha.getRang());  // Elle prend le même rang de domination que son ancien conjoint
        }
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