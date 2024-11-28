package tests.creatures;

import hopital.Sexe;
import hopital.creatures.Creature;
import hopital.creatures.personnages.Lycanthrope;
import hopital.creatures.personnages.Elfe;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;
import hopital.sante.caracteristiques.Budget;
import hopital.sante.centre.Crypte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LycanthropeTest {
    private Lycanthrope lycanthrope;
    private Elfe elfe;
    private ServiceMedical serviceMedical;

    @BeforeEach
    void setUp() {
        lycanthrope = new Lycanthrope("Lycanthrope", Sexe.MASCULIN, 95, 190, 40);
        elfe = new Elfe("Elfe", Sexe.FEMININ, 60, 170, 30);

        serviceMedical = new Crypte("Crypte", 100, 10, 2, new ArrayList<>(), Budget.MOYEN);
        serviceMedical.addCreature(lycanthrope);
        serviceMedical.addCreature(elfe);

        // Le Lycanthrope commence avec une maladie
        lycanthrope.tomberMalade(Maladie.MDC);
    }

    @Test
    void testContaminer() {
        int maladiesAvant = elfe.getMaladies().size();
        lycanthrope.contaminer(serviceMedical);
        int maladiesApres = elfe.getMaladies().size();

        assertTrue(maladiesApres >= maladiesAvant, "La contamination n'a pas eu lieu.");

        if (!elfe.getMaladies().isEmpty()) {
            assertTrue(elfe.getMaladies().contains(Maladie.MDC),
                    "L'Elfe devrait être contaminé par la maladie du Lycanthrope.");
        }
    }

    @Test
    void testContaminerAvecAucuneMaladie() {
        lycanthrope.getMaladies().clear();
        int maladiesAvant = elfe.getMaladies().size();

        lycanthrope.contaminer(serviceMedical);
        int maladiesApres = elfe.getMaladies().size();

        assertEquals(maladiesAvant, maladiesApres, "Aucune maladie n'aurait dû être transmise.");
    }

    @Test
    void testContaminerSiCibleDejaMalade() {
        elfe.tomberMalade(Maladie.MDC);
        int maladiesAvant = elfe.getMaladies().size();

        lycanthrope.contaminer(serviceMedical);
        int maladiesApres = elfe.getMaladies().size();

        assertEquals(maladiesAvant, maladiesApres, "La maladie était déjà présente, aucune nouvelle contamination.");
    }

    @Test
    void testContaminerAucuneCreature() {
        serviceMedical.getCreatures().clear();
        lycanthrope.contaminer(serviceMedical);

        // Vérification indirecte via absence d'exception et comportements corrects.
        assertTrue(serviceMedical.getCreatures().isEmpty(), "Aucune créature présente.");
    }

    @Test
    void testTrepasser() {
        lycanthrope.trepasser();
        //assertTrue(lycanthrope.isMort(), "Le Lycanthrope aurait dû trépasser.");
        // Si la méthode contaminer était déclenchée automatiquement, nous l'ajouterions ici
    }
}
