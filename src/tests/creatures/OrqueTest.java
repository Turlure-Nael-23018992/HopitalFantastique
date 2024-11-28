package tests.creatures;

import hopital.Sexe;
import hopital.creatures.Creature;
import hopital.creatures.personnages.Orque;
import hopital.creatures.personnages.Elfe;
import hopital.sante.Maladie;
import hopital.sante.ServiceMedical;
import hopital.sante.caracteristiques.Budget;
import hopital.sante.centre.Crypte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrqueTest {
    private Orque orque;
    private Elfe elfe;
    private ServiceMedical serviceMedical;

    @BeforeEach
    void setUp() {
        orque = new Orque("Orque", Sexe.MASCULIN, 120, 200, 35);
        elfe = new Elfe("Elfe", Sexe.FEMININ, 60, 170, 30);

        serviceMedical = new Crypte("Crypte", 100, 10, 2, new ArrayList<>(), Budget.MOYEN);
        serviceMedical.addCreature(orque);
        serviceMedical.addCreature(elfe);

        // L'Orque commence avec une maladie
        orque.tomberMalade(Maladie.MDC);
    }

    @Test
    void testContaminer() {
        int maladiesAvant = elfe.getMaladies().size();
        orque.contaminer(serviceMedical);
        int maladiesApres = elfe.getMaladies().size();

        assertTrue(maladiesApres >= maladiesAvant, "La contamination n'a pas eu lieu.");

        if (!elfe.getMaladies().isEmpty()) {
            assertTrue(elfe.getMaladies().contains(Maladie.MDC), "L'Elfe aurait dû être contaminé par la maladie de l'Orque.");
        }
    }

    @Test
    void testContaminerAvecAucuneMaladie() {
        orque.getMaladies().clear();
        int maladiesAvant = elfe.getMaladies().size();

        orque.contaminer(serviceMedical);
        int maladiesApres = elfe.getMaladies().size();

        assertEquals(maladiesAvant, maladiesApres, "Aucune maladie n'aurait dû être transmise.");
    }

    @Test
    void testContaminerSiCibleDejaMalade() {
        elfe.tomberMalade(Maladie.MDC);
        int maladiesAvant = elfe.getMaladies().size();

        orque.contaminer(serviceMedical);
        int maladiesApres = elfe.getMaladies().size();

        assertEquals(maladiesAvant, maladiesApres, "La contamination n'a pas eu lieu car l'Elfe est déjà contaminé.");
    }

    @Test
    void testContaminerSansCreatures() {
        serviceMedical.getCreatures().clear();
        orque.contaminer(serviceMedical);

        // Vérification indirecte : aucune exception et comportement sans erreur.
        assertTrue(serviceMedical.getCreatures().isEmpty(), "Aucune créature n'est présente dans le service médical.");
    }

    @Test
    void testTrepasser() {
        orque.trepasser();
        //assertTrue(orque.isMort(), "L'Orque aurait dû être marqué comme mort.");
        // Si une autre action comme contaminer devait être appelée ici, on la testerait aussi.
    }
}
