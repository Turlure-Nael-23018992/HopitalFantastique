package tests.creatures;

import hopital.creatures.personnages.Elfe;
import hopital.creatures.Creature;
import hopital.Sexe;
import hopital.creatures.personnages.Zombie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ElfeTest {
    private Elfe elfe;
    private ArrayList<Creature> creatures;
    private Creature targetCreature;

    @Before
    public void setUp() {
        System.out.println("Setting up test environment...");
        elfe = new Elfe("Legolas", Sexe.MASCULIN, 70, 180, 2931);
        creatures = new ArrayList<>();
        targetCreature = new Zombie("Target", Sexe.FEMININ, 60, 170, 25);
        elfe.getMoral().setValeur(100);
        System.out.println("Setup complete.");
    }

    @Test
    public void testAttendre() {
        System.out.println("Running testAttendre...");
        int initialMoral = elfe.getMoral().getValeur();
        elfe.attendre(creatures, 2);
        assertFalse(initialMoral == elfe.getMoral().getValeur());
        System.out.println("testAttendre completed.");
    }

    @Test
    public void testAttendreMoralMinimum() {
        System.out.println("Running testAttendreMoralMinimum...");
        elfe.getMoral().setValeur(5);
        elfe.attendre(creatures, 5);
        assertTrue(elfe.getMoral().getValeur() >= 0);
        System.out.println("testAttendreMoralMinimum completed.");
    }

    @Test
    public void testAttendreMoralMaximum() {
        System.out.println("Running testAttendreMoralMaximum...");
        elfe.getMoral().setValeur(100);
        int initialMoral = elfe.getMoral().getValeur();
        elfe.attendre(creatures, 1);
        assertEquals(initialMoral - 10, elfe.getMoral().getValeur());
        System.out.println("testAttendreMoralMaximum completed.");
    }

    @Test
    public void testDemoraliser() {
        System.out.println("Running testDemoraliser...");
        int initialTargetMoral = targetCreature.getMoral().getValeur();
        elfe.demoraliser(targetCreature);
        assertEquals(initialTargetMoral - 5, targetCreature.getMoral().getValeur());
        System.out.println("testDemoraliser completed.");
    }

    @Test
    public void testDemoralisationLimit() {
        System.out.println("Running testDemoralisationLimit...");
        targetCreature.getMoral().setValeur(3);
        elfe.demoraliser(targetCreature);
        assertTrue(targetCreature.getMoral().getValeur() >= 0);
        System.out.println("testDemoralisationLimit completed.");
    }

    @Test
    public void testTrepasser() {
        System.out.println("Running testTrepasser...");
        int initialSante = elfe.getMoral().getValeur();
        elfe.trepasser();
        assertFalse(initialSante == elfe.getMoral().getValeur());
        System.out.println("testTrepasser completed.");
    }

    @Test
    public void testConstructeur() {
        System.out.println("Running testConstructeur...");
        assertEquals("Legolas", elfe.getName());
        assertEquals(Sexe.MASCULIN, elfe.getSexe());
        assertEquals(70, elfe.getPoids());
        assertEquals(180, elfe.getTaille());
        assertEquals(2931, elfe.getAge());
        System.out.println("testConstructeur completed.");
    }
}