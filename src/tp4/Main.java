package tp4;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Lycanthrope l1 = new Lycanthrope(Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.ADULTE, 100, 50, 1, 0.8, null);  // Facteur d'impétuosité corrigé à 0.8
        Lycanthrope l2 = new Lycanthrope(Lycanthrope.Sexe.FEMELLE, Lycanthrope.CategorieAge.ADULTE, 90, 40, 1, 0.7, null);  // Facteur d'impétuosité corrigé à 0.7
        Lycanthrope l3 = new Lycanthrope(Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.JEUNE, 60, 30, 5, 0.6, null);    // Facteur d'impétuosité corrigé à 0.6
        Lycanthrope l4 = new Lycanthrope(Lycanthrope.Sexe.FEMELLE, Lycanthrope.CategorieAge.JEUNE, 70, 35, 6, 0.75, null); // Facteur d'impétuosité corrigé à 0.75
        Lycanthrope l5 = new Lycanthrope(Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.ADULTE, 80, 45, Integer.MAX_VALUE, 0.5, null);  // Facteur d'impétuosité corrigé à 0.5

        Meute meute = new Meute();
        meute.ajouterLycanthrope(l1);
        meute.ajouterLycanthrope(l2);
        meute.ajouterLycanthrope(l3);
        meute.ajouterLycanthrope(l4);
        meute.ajouterLycanthrope(l5);

        System.out.println("Affichage de la hiérarchie de la meute :");
        meute.afficherHierarchie();

        System.out.println("\nAlpha Male : " + meute.getAlphaMale());
        System.out.println("Alpha Femelle : " + meute.getAlphaFemelle());

        System.out.println("\nSouffre Douleur : " + meute.getSouffreDouleur());

        System.out.println("\nSubordonnés de l'Alpha Male : ");
        List<Lycanthrope> subordonnés = meute.getSubordinates(l1);
        subordonnés.forEach(System.out::println);

        List<Lycanthrope> lycanthropesSolitaire = List.of(l3, l4);
        Meute nouvelleMeute = Meute.fonderMeuteSiPossible(lycanthropesSolitaire);
        if (nouvelleMeute != null) {
            System.out.println("\nNouvelle meute fondée avec les lycanthropes solitaires.");
        } else {
            System.out.println("\nLa création de la meute n'a pas pu avoir lieu.");
        }

        System.out.println("\nDébut de la reproduction...");
        List<Lycanthrope> jeunes = meute.reproduire();
        System.out.println("\nJeunes lycanthropes nés : " + jeunes);
    }
}