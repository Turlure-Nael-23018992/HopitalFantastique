package tests.sante.caracteristiques;

import hopital.sante.caracteristiques.Budget;

import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    @org.junit.jupiter.api.Test
    void reviserBudgetALaHausse() {
        Budget budget = Budget.INEXISTANT;
        assertEquals(Budget.MEDIOCRE, budget.reviserBudgetALaHausse());
        budget = Budget.MEDIOCRE;
        assertEquals(Budget.INSUFFISANT, budget.reviserBudgetALaHausse());
        budget = Budget.INSUFFISANT;
        assertEquals(Budget.FAIBLE, budget.reviserBudgetALaHausse());
        budget = Budget.FAIBLE;
        assertEquals(Budget.MOYEN, budget.reviserBudgetALaHausse());
        budget = Budget.MOYEN;
        assertEquals(Budget.ELEVE, budget.reviserBudgetALaHausse());
        budget = Budget.ELEVE;
        assertEquals(Budget.ELEVE, budget.reviserBudgetALaHausse());
    }
}