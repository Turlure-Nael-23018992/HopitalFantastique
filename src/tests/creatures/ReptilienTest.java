package tests.creatures;

import hopital.Sexe;
import hopital.creatures.Creature;
import hopital.creatures.personnages.Reptilien;
import hopital.creatures.personnages.Elfe;
import hopital.creatures.personnages.Orque;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.personnages.Lycanthrope;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReptilienTest {
    private Reptilien reptilien;
    private ArrayList<Creature> creatures;

    @BeforeEach
    void setUp() {
        reptilien = new Reptilien("Reptilien", Sexe.MASCULIN, 80, 190, 25);
        creatures = new ArrayList<>();
        reptilien.getMoral().setValeur(100);  // Moral initial à 100
    }

    @Test
    void testAttendreSeul() {
        int moralInitial = reptilien.getMoral().getValeur();

        reptilien.attendre(creatures, 2);

        int moralApres = reptilien.getMoral().getValeur();
        assertEquals(moralInitial - 10, moralApres, "Le Reptilien aurait dû perdre 10 points de moral en attendant seul.");
    }

    @Test
    void testAttendreAvecUnPoteNonTriage() {
        creatures.add(new Elfe("Elfe", Sexe.FEMININ, 60, 170, 30));  // Non-Triage
        int moralInitial = reptilien.getMoral().getValeur();

        reptilien.attendre(creatures, 2);

        int moralApres = reptilien.getMoral().getValeur();
        assertEquals(moralInitial - 10, moralApres, "Le Reptilien aurait dû perdre 10 points de moral avec un non-Triage.");
    }

    @Test
    void testAttendreAvecUnPoteTriage() {
        creatures.add(new Lycanthrope("Lycanthrope", Sexe.MASCULIN, 95, 190, 40));  // Triage
        int moralInitial = reptilien.getMoral().getValeur();

        reptilien.attendre(creatures, 2);

        int moralApres = reptilien.getMoral().getValeur();
        assertEquals(moralInitial - 5, moralApres, "Le Reptilien aurait dû perdre seulement 5 points de moral avec un Triage.");
    }

    @Test
    void testAttendreAvecPlusieursPotesTriage() {
        creatures.add(new Lycanthrope("Lycanthrope", Sexe.MASCULIN, 95, 190, 40));  // Triage
        creatures.add(new Orque("Orque", Sexe.MASCULIN, 120, 200, 35));              // Triage

        int moralInitial = reptilien.getMoral().getValeur();

        reptilien.attendre(creatures, 2);

        int moralApres = reptilien.getMoral().getValeur();
        assertEquals(moralInitial - 3, moralApres, "Avec plusieurs Triage, la perte de moral aurait dû être réduite à 3.");
    }

    @Test
    void testAttendreSansCreatures() {
        int moralInitial = reptilien.getMoral().getValeur();

        reptilien.attendre(new ArrayList<>(), 2);

        int moralApres = reptilien.getMoral().getValeur();
        assertEquals(moralInitial - 10, moralApres, "Sans collègues, le Reptilien devrait perdre 10 points de moral.");
    }
}
