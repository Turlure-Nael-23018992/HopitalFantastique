package hopital.sante.caracteristiques;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitaire de la classe {@link Budget}.
 */
public class BudgetTest {

    /**
     * Initialisation avant chaque test.
     * On remet le budget actuel à zéro.
     */
    @BeforeEach
    public void setUp() {
        Budget.setValeurActuelle(0);
    }

    /**
     * Test de la valeur actuelle du budget.
     */
    @Test
    public void testGetValeurActuelle() {
        assertEquals(0, Budget.getValeurActuelle(), "Le budget initial devrait être 0.");

        Budget.setValeurActuelle(5000);
        assertEquals(5000, Budget.getValeurActuelle(), "Le budget devrait être 5000.");
    }

    /**
     * Test du niveau de budget avec différentes valeurs.
     */
    @Test
    public void testGetNiveauActuel() {
        Budget.setValeurActuelle(500);
        assertEquals(Budget.INEXISTANT, Budget.getNiveauActuel(), "Le niveau devrait être INEXISTANT.");

        Budget.setValeurActuelle(1500);
        assertEquals(Budget.MEDIOCRE, Budget.getNiveauActuel(), "Le niveau devrait être MEDIOCRE.");

        Budget.setValeurActuelle(6000);
        assertEquals(Budget.INSUFFISANT, Budget.getNiveauActuel(), "Le niveau devrait être INSUFFISANT.");

        Budget.setValeurActuelle(20000);
        assertEquals(Budget.FAIBLE, Budget.getNiveauActuel(), "Le niveau devrait être FAIBLE.");

        Budget.setValeurActuelle(70000);
        assertEquals(Budget.MOYEN, Budget.getNiveauActuel(), "Le niveau devrait être MOYEN.");

        Budget.setValeurActuelle(150000);
        assertEquals(Budget.ELEVE, Budget.getNiveauActuel(), "Le niveau devrait être ELEVE.");
    }

    /**
     * Test de l'ajustement du budget (augmentation et diminution).
     */
    @Test
    public void testAjusterBudget() {
        Budget.setValeurActuelle(1000);

        // Test augmentation du budget
        Budget.ajusterBudget(true, 5000);
        assertEquals(6000, Budget.getValeurActuelle(), "Le budget devrait être augmenté à 6000.");

        // Test diminution du budget
        Budget.ajusterBudget(false, 2000);
        assertEquals(4000, Budget.getValeurActuelle(), "Le budget devrait être diminué à 4000.");

        // Test de la limite inférieure (budget ne peut pas être négatif)
        Budget.ajusterBudget(false, 5000);
        assertEquals(0, Budget.getValeurActuelle(), "Le budget ne peut pas être inférieur à 0.");
    }

    /**
     * Test de la méthode toString() pour vérifier la représentation textuelle du budget.
     */
    @Test
    public void testToString() {
        Budget.setValeurActuelle(100);
        assertEquals("INEXISTANT (0 - 999)", Budget.getNiveauActuel().toString(), "La représentation du budget est incorrecte.");

        Budget.setValeurActuelle(2000);
        assertEquals("MEDIOCRE (1000 - 4999)", Budget.getNiveauActuel().toString(), "La représentation du budget est incorrecte.");

        Budget.setValeurActuelle(8000);
        assertEquals("INSUFFISANT (5000 - 9999)", Budget.getNiveauActuel().toString(), "La représentation du budget est incorrecte.");

        Budget.setValeurActuelle(15000);
        assertEquals("FAIBLE (10000 - 49999)", Budget.getNiveauActuel().toString(), "La représentation du budget est incorrecte.");

        Budget.setValeurActuelle(60000);
        assertEquals("MOYEN (50000 - 99999)", Budget.getNiveauActuel().toString(), "La représentation du budget est incorrecte.");

        Budget.setValeurActuelle(200000);
        assertEquals("ELEVE (100000 - 2147483647)", Budget.getNiveauActuel().toString(), "La représentation du budget est incorrecte.");
    }

    /**
     * Test de la méthode setValeurActuelle pour les cas négatifs.
     */
    @Test
    public void testSetValeurActuelle() {
        // Test valeur négative
        Budget.setValeurActuelle(-1000);
        assertEquals(0, Budget.getValeurActuelle(), "Le budget ne peut pas être négatif.");

        // Test valeur valide
        Budget.setValeurActuelle(5000);
        assertEquals(5000, Budget.getValeurActuelle(), "Le budget devrait être 5000.");
    }
}
