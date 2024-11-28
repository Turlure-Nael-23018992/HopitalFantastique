package hopital.sante.caracteristiques;

public enum Budget implements Comparable<Budget> {
    INEXISTANT(0),
    MEDIOCRE(1000),
    INSUFFISANT(5000),
    FAIBLE(10000),
    MOYEN(50000),
    ELEVE(100000);

    private int budget;

    Budget(int budget) {
        this.budget = budget;
    }

    public int getBudget() {
        return this.budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Budget reviserBudgetALaHausse()
    {
        Budget[] budgets = Budget.values();
        return this.ordinal() < budgets.length - 1 ?
                budgets[this.ordinal() + 1] :
                this;
    }

    public Budget reviserBudgetABaisse()
    {
        Budget[] budgets = Budget.values();
        return this.ordinal() > 0 ?
                budgets[this.ordinal() - 1] :
                this;
    }
}