package tests.creatures;

import hopital.creatures.personnages.Zombie;
import hopital.creatures.Creature;
import hopital.Sexe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZombieTest {

    private Zombie zombie;

    @BeforeEach
    void setUp() {
        // Initialisation d'un Zombie pour chaque test
        zombie = new Zombie("Zombie", Sexe.MASCULIN, 70, 180, 30);
    }

    @Test
    void testCreationZombie() {
        // Vérifier que le Zombie est bien créé avec les bons attributs
        assertEquals("Zombie", zombie.getName(), "Le nom du Zombie ne correspond pas.");
        assertEquals(Sexe.MASCULIN, zombie.getSexe(), "Le sexe du Zombie ne correspond pas.");
        assertEquals(70, zombie.getPoids(), "Le poids du Zombie ne correspond pas.");
        assertEquals(180, zombie.getTaille(), "La taille du Zombie ne correspond pas.");
        assertEquals(30, zombie.getAge(), "L'âge du Zombie ne correspond pas.");
    }

    @Test
    void testRegenererApresTrepasser() {
        // Sauvegarder l'état avant de trépasser
        int moralAvant = zombie.getMoral().getValeur();

        // Le zombie trépasser
        zombie.trepasser();

        // Vérifier que le moral a été régénéré à 100
        assertEquals(100, zombie.getMoral().getValeur(), "Le moral du Zombie n'a pas été régénéré à 100 après trépassage.");
    }

    @Test
    void testRegenerationDejaMaximale() {
        // Réinitialiser le moral du zombie à 100
        zombie.getMoral().setValeur(100);
        int moralAvant = zombie.getMoral().getValeur();

        // Le zombie trépasser à nouveau
        zombie.trepasser();

        // Vérifier que le moral reste à 100 même après un trépassage
        assertEquals(100, zombie.getMoral().getValeur(), "Le moral du Zombie n'a pas été correctement régénéré.");
        assertEquals(moralAvant, zombie.getMoral().getValeur(), "Le moral ne doit pas changer s'il est déjà à 100.");
    }

    @Test
    void testRegenererApresTrepasserSurZombieSansMaladie() {
        // On suppose que le zombie n'a aucune maladie et que le moral est à un niveau bas
        zombie.getMoral().setValeur(50);
        int moralAvant = zombie.getMoral().getValeur();

        // Le zombie trépasser
        zombie.trepasser();

        // Vérifier que le moral a bien été régénéré à 100
        assertEquals(100, zombie.getMoral().getValeur(), "Le moral du Zombie n'a pas été correctement régénéré après trépassage.");
    }
}
