package tests.creatures;

import hopital.creatures.personnages.Elfe;
import hopital.creatures.Creature;
import hopital.Sexe;
import hopital.creatures.personnages.Zombie;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ElfeTest {
    private Elfe elfe;
    private ArrayList<Creature> creatures;
    private Creature targetCreature;

    @Before
    public void setUp() {
        elfe = new Elfe("Legolas", Sexe.MASCULIN, 70, 180, 2931);
        creatures = new ArrayList<>();
        targetCreature = new Zombie("Target", Sexe.FEMININ, 60, 170, 25);
    }

    @BeforeEach
    public void setUpCreatures() {
        elfe.getMoral().setValeur(100);
    }
    @Test
    public void testAttendre() {
        int initialMoral = elfe.getMoral().getValeur();
        elfe.attendre(creatures, 20);
        assertFalse(initialMoral == elfe.getMoral().getValeur());
    }

    @Test
    public void testAttendreMoralMinimum() {
        elfe.getMoral().setValeur(5);
        elfe.attendre(creatures, 5);
        assertTrue(elfe.getMoral().getValeur() >= 0);
    }

    @Test
    public void testAttendreMoralMaximum() {
        elfe.getMoral().setValeur(100);
        int initialMoral = elfe.getMoral().getValeur();
        elfe.attendre(creatures, 1);
        assertEquals(initialMoral - 10, elfe.getMoral().getValeur());
    }

    @Test
    public void testDemoraliser() {
        int initialTargetMoral = targetCreature.getMoral().getValeur();
        elfe.demoraliser(targetCreature);
        assertEquals(initialTargetMoral - 5, targetCreature.getMoral().getValeur());
    }

    @Test
    public void testDemoralisationLimit() {
        targetCreature.getMoral().setValeur(3);
        elfe.demoraliser(targetCreature);
        assertTrue(targetCreature.getMoral().getValeur() >= 0);
    }

    @Test
    public void testTrepasser() {
        int initialSante = elfe.getMoral().getValeur();
        elfe.trepasser();
        assertFalse(initialSante == elfe.getMoral().getValeur());
    }

    @Test
    public void testConstructeur() {
        assertEquals("Legolas", elfe.getName());
        assertEquals(Sexe.MASCULIN, elfe.getSexe());
        assertEquals(70, elfe.getPoids());
        assertEquals(180, elfe.getTaille());
        assertEquals(2931, elfe.getAge());
    }
}