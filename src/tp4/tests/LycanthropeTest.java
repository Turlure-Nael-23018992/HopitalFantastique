package tp4.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp4.main.Hurlement;
import tp4.main.Lycanthrope;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe Lycanthrope.
 */
class LycanthropeTest {

    private Lycanthrope fenrir;
    private Lycanthrope luna;

    @BeforeEach
    void setUp() {
        fenrir = new Lycanthrope(
                "Fenrir",
                Lycanthrope.Sexe.MALE,
                Lycanthrope.CategorieAge.ADULTE,
                50,
                10,
                1,
                0.4,
                "Meute1"
        );

        luna = new Lycanthrope(
                "Luna",
                Lycanthrope.Sexe.FEMELLE,
                Lycanthrope.CategorieAge.ADULTE,
                45,
                15,
                0,
                0.5,
                "Meute1"
        );
    }

    @Test
    void testConstructeur() {
        assertNotNull(fenrir);
        assertEquals("Fenrir", fenrir.getNom());
        assertEquals(Lycanthrope.Sexe.MALE, fenrir.getSexe());
        assertEquals(Lycanthrope.CategorieAge.ADULTE, fenrir.categorieAge);
        assertEquals(50, fenrir.force);
        assertEquals(10, fenrir.facteurDomination);
        assertEquals(1, fenrir.getRang());
        assertEquals("Meute1", fenrir.getMeute());
    }

    @Test
    void testCalculerNiveau() {
        double expectedNiveauFenrir = 1.5 * (50 + 10 * 0.5 + 1 * 2); // ADULTE : coefficient 1.5
        assertEquals(expectedNiveauFenrir, fenrir.niveau, 0.01);
    }

    @Test
    void testVieillir() {
        fenrir.vieillir();
        assertEquals(Lycanthrope.CategorieAge.VIEUX, fenrir.categorieAge);
        double expectedNiveauFenrir = 2.0 * (50 + 10 * 0.5 + 1 * 2); // VIEUX : coefficient 2.0
        assertEquals(expectedNiveauFenrir, fenrir.niveau, 0.01);
    }

    @Test
    void testHurler() {
        // Capturer la sortie console pour vérifier le hurlement
        fenrir.hurler("Je suis Fenrir !");
        // Aucune assertion ici, car la sortie console n'est pas directement vérifiable avec assert
    }

    @Test
    void testRepondreHurlement() {
        Hurlement hurlement = new Hurlement(fenrir, Hurlement.TypeHurlement.DOMINATION, "Je domine !");
        luna.repondreHurlement(hurlement);
        // Valider visuellement les sorties console
    }

    @Test
    void testTenterDomination_Echec() {
        luna.tenterDomination(fenrir); // Luna devrait échouer (rang inférieur)
        assertEquals(9, fenrir.facteurDomination); // Fenrir n'est pas affecté
        assertEquals(14, luna.facteurDomination); // Luna perd un point
    }

    @Test
    void testReagirAgression() {
        luna.reagirAgression();
        assertEquals(14, luna.facteurDomination); // Luna perd un point
    }

    @Test
    void testToString() {
        String expectedString = "Lycanthrope{nom='Fenrir', sexe=MALE, categorieAge=ADULTE, force=50, facteurDomination=10, rang=1, facteurImpetuosite=0.4, meute='Meute1', niveau=85.5, estHumain=false, solitaire=false}";
        assertEquals(expectedString, fenrir.toString());
    }

    @Test
    void testCompareTo() {
        assertTrue(fenrir.compareTo(luna) > 0); // Fenrir a un rang inférieur (1 < 0)
        luna.setRang(2);
        assertTrue(fenrir.compareTo(luna) < 0); // Luna a un rang supérieur (2 > 1)
    }
}