package tp4;

import java.util.ArrayList;
import java.util.List;

public class Meute {

    private final List<Lycanthrope> lycanthropes;

    public Meute() {
        this.lycanthropes = new ArrayList<>();
    }

    public void ajouterLycanthrope(Lycanthrope l) {
        if (!lycanthropes.contains(l)) lycanthropes.add(l);
    }

    public void afficherHierarchie() {
        lycanthropes.stream()
                .sorted((l1, l2) -> Integer.compare(l1.getRang(), l2.getRang()))  // On compare directement les entiers
                .forEach(System.out::println);
    }

    public Lycanthrope getAlphaMale() {
        return lycanthropes.stream()
                .filter(l -> l.getRang() == 1 && l.getSexe() == Lycanthrope.Sexe.MALE)  // Si le rang "α" correspond à 1
                .findFirst().orElse(null);
    }

    public Lycanthrope getAlphaFemelle() {
        return lycanthropes.stream()
                .filter(l -> l.getRang() == 1 && l.getSexe() == Lycanthrope.Sexe.FEMELLE)  // Si le rang "α" correspond à 1
                .findFirst().orElse(null);
    }

    public Lycanthrope getSouffreDouleur() {
        return lycanthropes.stream()
                .filter(l -> l.getRang() == Integer.MAX_VALUE)  // Si le rang "ω" correspond à un rang très élevé
                .findFirst().orElse(null);
    }

    public List<Lycanthrope> getSubordinates(Lycanthrope lycanthrope) {
        return lycanthropes.stream()
                .filter(l -> l.getRang() > lycanthrope.getRang())  // Les subordonnés ont un rang supérieur
                .toList();
    }
}