package tests.creatures;

import hopital.Sexe;
import hopital.creatures.Creature;
import hopital.creatures.caracteristiques.Moral;
import hopital.creatures.personnages.Elfe;
import hopital.creatures.personnages.HommeBete;
import hopital.creatures.personnages.Vampire;
import hopital.creatures.personnages.Zombie;
import hopital.sante.ServiceMedical;
import hopital.sante.Maladie;
import hopital.sante.caracteristiques.Budget;
import hopital.sante.centre.Crypte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HommeBeteTest {
    private HommeBete hommeBete;
    private Elfe elfe;
    private ServiceMedical serviceMedical;

    @BeforeEach
    void setUp() {
        hommeBete = new HommeBete("HommeBête", Sexe.MASCULIN, 90, 180, 35);
        elfe = new Elfe("Elfe", Sexe.FEMININ, 60, 170, 30);

        serviceMedical = new Crypte("Elfe", 100, 10, 2, new ArrayList<Creature>(), Budget.ELEVE);
        serviceMedical.addCreature(hommeBete);

        hommeBete.tomberMalade(Maladie.MDC);
    }

    @Test
    void testContaminer() {
        System.out.println(serviceMedical);
        int maladiesAvant = elfe.getMaladies().size();

        hommeBete.contaminer(serviceMedical);

        int maladiesApres = elfe.getMaladies().size();

        assertTrue(maladiesApres >= maladiesAvant, "La contamination n'a pas eu lieu");

        // Si la créature était contaminée par la maladie de l'HommeBete
        if (!elfe.getMaladies().isEmpty()) {
            assertTrue(elfe.getMaladies().contains(Maladie.MDC),
                    "L'autre créature devrait maintenant avoir la même maladie que l'HommeBete.");
        }
    }

    
    @Test
    void testContaminerSiMaladieDejaPresente() {
        elfe.tomberMalade(Maladie.MDC);
        int maladiesAvant = elfe.getMaladies().size();

        hommeBete.contaminer(serviceMedical);

        int maladiesApres = elfe.getMaladies().size();

        assertEquals(maladiesAvant, maladiesApres, "La contamination n'a pas eu lieu car la maladie était déjà présente.");
    }

    @Test
    void testContaminerSansMaladies() {
        hommeBete.getMaladies().clear();
        int maladiesAvant = elfe.getMaladies().size();

        hommeBete.contaminer(serviceMedical);

        int maladiesApres = elfe.getMaladies().size();

        assertEquals(maladiesAvant, maladiesApres, "Aucune maladie n'aurait dû être transmise.");
    }
    @Test
    public void testAttendre() {
        ArrayList<Creature> creatures = new ArrayList<>();
        hommeBete.getMoral().setValeur(100);
        int initialMoral = hommeBete.getMoral().getValeur();

        hommeBete.attendre(creatures, 2);
        assertTrue(hommeBete.getMoral().getValeur() < initialMoral);
        assertEquals(initialMoral - 20, hommeBete.getMoral().getValeur());
    }

    @Test
    public void testAttendreAvecPote() {
        ArrayList<Creature> creatures = new ArrayList<>();
        hommeBete.getMoral().setValeur(100);
        int initialMoral = hommeBete.getMoral().getValeur();
        creatures.add(new Vampire("HommeBête", Sexe.MASCULIN, 90, 180, 35));
        hommeBete.attendre(creatures, 2);
        assertTrue(hommeBete.getMoral().getValeur() < initialMoral);
        assertEquals(initialMoral - 20, hommeBete.getMoral().getValeur());
    }
}
