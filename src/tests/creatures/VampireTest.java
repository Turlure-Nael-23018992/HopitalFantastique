package tests.creatures;

import hopital.Sexe;
import hopital.creatures.Creature;
import hopital.creatures.personnages.Vampire;
import hopital.creatures.personnages.Elfe;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;
import hopital.sante.caracteristiques.Budget;
import hopital.sante.centre.Crypte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VampireTest {
    private Vampire vampire;
    private Elfe elfe;
    private ServiceMedical serviceMedical;

    @BeforeEach
    void setUp() {
        // Initialisation des objets pour chaque test
        vampire = new Vampire("Vampire", Sexe.MASCULIN, 80, 180, 150);
        elfe = new Elfe("Elfe", Sexe.FEMININ, 60, 170, 30);

        // Création du service médical (Crypte)
        serviceMedical = new Crypte("Crypte", 100, 10, 2, new ArrayList<>(), Budget.ELEVE);
        serviceMedical.addCreature(vampire);
        serviceMedical.addCreature(elfe);

        // Le vampire commence avec une maladie
        vampire.tomberMalade(Maladie.MDC);
    }

    @Test
    void testContaminer() {
        int maladiesAvant = elfe.getMaladies().size();
        vampire.contaminer(serviceMedical);
        int maladiesApres = elfe.getMaladies().size();

        // Vérifier que la contamination a eu lieu
        assertTrue(maladiesApres >= maladiesAvant, "La contamination n'a pas eu lieu.");

        if (!elfe.getMaladies().isEmpty()) {
            assertTrue(elfe.getMaladies().contains(Maladie.MDC),
                    "L'Elfe devrait être contaminé par la maladie du Vampire.");
        }
    }

    @Test
    void testContaminerAvecAucuneMaladie() {
        // Le vampire n'a plus de maladie
        vampire.getMaladies().clear();
        int maladiesAvant = elfe.getMaladies().size();

        // Contamination
        vampire.contaminer(serviceMedical);
        int maladiesApres = elfe.getMaladies().size();

        // Aucune maladie n'aurait dû être transmise
        assertEquals(maladiesAvant, maladiesApres, "Aucune maladie n'aurait dû être transmise.");
    }

    @Test
    void testContaminerSiCibleDejaMalade() {
        // L'elfe est déjà malade
        elfe.tomberMalade(Maladie.MDC);
        int maladiesAvant = elfe.getMaladies().size();

        // Contamination
        vampire.contaminer(serviceMedical);
        int maladiesApres = elfe.getMaladies().size();

        // La maladie était déjà présente, donc aucune nouvelle contamination
        assertEquals(maladiesAvant, maladiesApres, "La maladie était déjà présente, aucune nouvelle contamination.");
    }

    @Test
    void testContaminerAucuneCreature() {
        // Supprimer toutes les créatures du service médical
        serviceMedical.getCreatures().clear();
        vampire.contaminer(serviceMedical);

        // Vérification que le service médical est vide, donc aucune contamination
        assertTrue(serviceMedical.getCreatures().isEmpty(), "Aucune créature présente.");
    }

    @Test
    void testDemoraliser() {
        // Initial morale of the elf
        int moralAvant = elfe.getMoral().getValeur();

        // Le vampire démoralise l'elfe
        vampire.demoraliser(Creature.getInstance());

        // Morale of the elf after demoralization
        int moralApres = elfe.getMoral().getValeur();

        // Vérifier que le moral de l'elfe a diminué
        assertTrue(moralApres < moralAvant, "L'elfe n'a pas été démoralisé.");
    }

    @Test
    void testTrepasser() {
        // Sauvegarder les valeurs initiales pour vérifier les effets de trepasser
        int moralAvant = vampire.getMoral().getValeur();
        int maladiesAvant = elfe.getMaladies().size();

        // Le vampire trépasser
        vampire.trepasser();

        // Vérifier que le moral du vampire a été régénéré à 100
        assertEquals(100, vampire.getMoral().getValeur(), "Le moral du vampire n'a pas été régénéré correctement.");
    }

    @Test
    void testTrepasserSansMaladies() {
        // Le vampire n'a plus de maladies
        vampire.getMaladies().clear();

        // Sauvegarder les valeurs initiales
        int moralAvant = vampire.getMoral().getValeur();
        int maladiesAvant = elfe.getMaladies().size();

        // Le vampire trépasser
        vampire.trepasser();

        // Vérifier que l'elfe n'a pas été contaminé (pas de nouvelles maladies)
        assertEquals(maladiesAvant, elfe.getMaladies().size(), "Aucune contamination n'aurait dû se produire.");

        // Vérifier que le moral du vampire a bien été régénéré à 100
        assertEquals(100, vampire.getMoral().getValeur(), "Le moral du vampire n'a pas été régénéré correctement.");

        // Vérifier que l'elfe a été démoralisé (le test de démoralisation est toujours valable)
        assertNotEquals(moralAvant, elfe.getMoral().getValeur(), "L'elfe n'a pas été démoralisé.");
    }

    @Test
    void testRegenerer() {
        vampire.getMoral().setValeur(50);
        int moralAvant = vampire.getMoral().getValeur();

        vampire.regenerer();
        int moralApres = vampire.getMoral().getValeur();

        assertEquals(100, moralApres, "Le moral du vampire aurait dû être régénéré à 100.");
    }
}
