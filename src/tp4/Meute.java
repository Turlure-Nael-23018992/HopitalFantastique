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
                .sorted((l1, l2) -> l1.getRang().compareTo(l2.getRang())) // compareTo garanti valide
                .forEach(System.out::println);
    }

    public Lycanthrope getAlphaMale() {
        return lycanthropes.stream()
                .filter(l -> l.getRang().equals("α") && l.getSexe() == Lycanthrope.Sexe.MALE)
                .findFirst().orElse(null);
    }

    public Lycanthrope getAlphaFemelle() {
        return lycanthropes.stream()
                .filter(l -> l.getRang().equals("α") && l.getSexe() == Lycanthrope.Sexe.FEMELLE)
                .findFirst().orElse(null);
    }

    public Lycanthrope getSouffreDouleur() {
        return lycanthropes.stream()
                .filter(l -> l.getRang().equals("ω"))
                .findFirst().orElse(null);
    }

    public List<Lycanthrope> getSubordinates(Lycanthrope lycanthrope) {
        return lycanthropes.stream()
                .filter(l -> l.getRang().compareTo(lycanthrope.getRang()) > 0)  // Utilise lexicographique
                .toList();
    }
}