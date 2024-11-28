package tp4;

public class Main {

    public static void main(String[] args) {
        Meute meute = new Meute();

        Lycanthrope l1 = new Lycanthrope(Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.ADULTE, 50, 10, 0, 0.7, "Meute Alpha");
        Lycanthrope l2 = new Lycanthrope(Lycanthrope.Sexe.FEMELLE, Lycanthrope.CategorieAge.VIEUX, 45, 8, 0, 0.6, "Meute Alpha");
        Lycanthrope l3 = new Lycanthrope(Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.JEUNE, 30, 5, 2, 0.3, "Meute Alpha");
        Lycanthrope l4 = new Lycanthrope(Lycanthrope.Sexe.FEMELLE, Lycanthrope.CategorieAge.ADULTE, 20, 2, 5, 0.4, "Meute Alpha");
        Lycanthrope l5 = new Lycanthrope(Lycanthrope.Sexe.MALE, Lycanthrope.CategorieAge.JEUNE, 10, 0, 8, 0.2, "Meute Alpha");

        meute.ajouterLycanthrope(l1);
        meute.ajouterLycanthrope(l2);
        meute.ajouterLycanthrope(l3);
        meute.ajouterLycanthrope(l4);
        meute.ajouterLycanthrope(l5);

        System.out.println("=== Hiérarchie de la meute ===");
        meute.afficherHierarchie();

        System.out.println("Alpha mâle : " + meute.getAlphaMale());
        System.out.println("Alpha femelle : " + meute.getAlphaFemelle());
        System.out.println("Souffre-douleur (ω) : " + meute.getSouffreDouleur());

        System.out.println("Subordonnés de " + l3 + " : ");
        meute.getSubordinates(l3).forEach(System.out::println);

        l3.quitterMeute();
        System.out.println("=== Hiérarchie après le départ de " + l3 + " ===");
        meute.afficherHierarchie();
    }
}