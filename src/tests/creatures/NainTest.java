package tests.creatures;

import hopital.Sexe;
import hopital.creatures.Creature;
import hopital.creatures.personnages.Nain;
import hopital.creatures.personnages.Elfe;
import hopital.creatures.personnages.Lycanthrope;
import hopital.creatures.caracteristiques.Triage;
import hopital.creatures.personnages.Vampire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NainTest {
    private Nain nain;
    private ArrayList<Creature> creatures;

    @BeforeEach
    void setUp() {
        nain = new Nain("Nain", Sexe.MASCULIN, 70, 140, 50);
        creatures = new ArrayList<>();
        nain.getMoral().setValeur(100);
    }

    @Test
    void testAttendreSeul() {
        int moralInitial = nain.getMoral().getValeur();

        nain.attendre(creatures, 2);

        int moralApres = nain.getMoral().getValeur();
        assertEquals(moralInitial - 20, moralApres, "Le Nain aurait dû perdre 20 points de moral.");
    }

    @Test
    void testAttendreAvecUnPoteNonTriage() {
        creatures.add(new Elfe("Elfe", Sexe.FEMININ, 60, 170, 30));
        int moralInitial = nain.getMoral().getValeur();

        nain.attendre(creatures, 2);

        int moralApres = nain.getMoral().getValeur();
        assertEquals(moralInitial - 20, moralApres, "La présence d'un non-Triage ne devrait pas changer la perte de moral.");
    }

    @Test
    void testAttendreAvecUnPoteTriage() {
        creatures.add(new Lycanthrope("Lycanthrope", Sexe.MASCULIN, 95, 190, 40));
        int moralInitial = nain.getMoral().getValeur();

        nain.attendre(creatures, 2);

        int moralApres = nain.getMoral().getValeur();
        assertEquals(moralInitial - 20, moralApres, "Le Nain aurait dû perdre seulement 20 points de moral avec un Triage.");
    }

    @Test
    void testAttendreAvecPlusieursPotesTriage() {
        creatures.add(new Lycanthrope("Lycanthrope", Sexe.MASCULIN, 95, 190, 40));
        creatures.add(new Vampire("Vampire", Sexe.FEMININ, 80, 160, 300));

        int moralInitial = nain.getMoral().getValeur();

        nain.attendre(creatures, 2);

        int moralApres = nain.getMoral().getValeur();
        assertEquals(moralInitial - 20, moralApres, "Avec plusieurs Triage, le Nain aurait dû perdre moins de moral.");
    }

    @Test
    void testAttendreSansCreatures() {
        int moralInitial = nain.getMoral().getValeur();

        nain.attendre(new ArrayList<>(), 2);

        int moralApres = nain.getMoral().getValeur();
        assertEquals(moralInitial - 20, moralApres, "Sans créatures, le Nain perd toujours 20 points de moral.");
    }
}
