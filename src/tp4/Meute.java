package tp4;

import java.util.ArrayList;
import java.util.List;

public class Meute {

    private final List<Lycanthrope> lycanthropes;
    private static List<Meute> meutes = new ArrayList<>();

    public Meute() {
        this.lycanthropes = new ArrayList<>();
    }

    public void ajouterLycanthrope(Lycanthrope l) {
        if (!lycanthropes.contains(l)) {
            lycanthropes.add(l);
        }
    }

    public void afficherHierarchie() {
        lycanthropes.stream()
                .sorted((l1, l2) -> Integer.compare(l1.getRang(), l2.getRang())) // Tri par rang
                .forEach(System.out::println);
    }

    public Lycanthrope getAlphaMale() {
        return lycanthropes.stream()
                .filter(l -> l.getRang() == 1 && l.getSexe() == Lycanthrope.Sexe.MALE)  // Le rang "α" correspond à 1 pour les mâles
                .findFirst().orElse(null);
    }

    public Lycanthrope getAlphaFemelle() {
        return lycanthropes.stream()
                .filter(l -> l.getRang() == 1 && l.getSexe() == Lycanthrope.Sexe.FEMELLE) // Le rang "α" correspond à 1 pour les femelles
                .findFirst().orElse(null);
    }

    public Lycanthrope getSouffreDouleur() {
        return lycanthropes.stream()
                .filter(l -> l.getRang() == Integer.MAX_VALUE)  // Le rang "ω" correspond à Integer.MAX_VALUE
                .findFirst().orElse(null);
    }

    public List<Lycanthrope> getSubordinates(Lycanthrope lycanthrope) {
        return lycanthropes.stream()
                .filter(l -> l.getRang() > lycanthrope.getRang())  // Les subordonnés ont un rang supérieur
                .toList();
    }

    // Méthode pour fonder une nouvelle meute si possible
    public static Meute fonderMeuteSiPossible(List<Lycanthrope> lycanthropes) {
        long nombreMalesSolitaire = lycanthropes.stream().filter(l -> l.isSolitaire() && l.getSexe() == Lycanthrope.Sexe.MALE).count();
        long nombreFemellesSolitaire = lycanthropes.stream().filter(l -> l.isSolitaire() && l.getSexe() == Lycanthrope.Sexe.FEMELLE).count();

        if (nombreMalesSolitaire > 0 && nombreFemellesSolitaire > 0 && !meutesExistantes()) {
            // Créer une nouvelle meute et marquer les lycanthropes comme non solitaires
            Meute nouvelleMeute = new Meute();
            lycanthropes.stream()
                    .filter(Lycanthrope::isSolitaire)
                    .forEach(lycanthrope -> {
                        lycanthrope.devenirNonSolitaire();
                        nouvelleMeute.ajouterLycanthrope(lycanthrope);
                    });
            meutes.add(nouvelleMeute);
            return nouvelleMeute;
        }

        return null;  // Retourner null si la création de la meute n'est pas possible
    }

    // Méthode pour vérifier si une meute existe déjà
    public static boolean meutesExistantes() {
        return !meutes.isEmpty();  // On vérifie simplement si une meute existe déjà
    }
}