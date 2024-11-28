package hopital.sante;

public enum Maladie {
    MDC("Hopital.Creature.Caractéristique.Maladie débilitante chronique","MDC",1, 10),
    FOMO("Syndrome fear of missing out","FOMO",1,10),
    DRS("Dépendance aux réseaux sociaux", "DRS",1,10),
    PEC("Porphyrie érythropoïétique congénitale", "PEC",1,10),
    ZPL("Zoopathie paraphrénique lycanthropique", "ZPL",1,10),
    BG("Bien gras", "BG",1,10);

    private String nomComplet;
    private String nomCourt;
    private int niveauActuel;
    private int niveauMax;

    Maladie(String nomComplet, String nomCourt, int niveauActuel, int niveauMax) {
        this.nomComplet = nomComplet;
        this.nomCourt = nomCourt;
        this.niveauActuel = niveauActuel;
        this.niveauMax = niveauMax;
    }

    public String getNomComplet() {
        return this.nomComplet;
    }

    public String getNomCourt() {
        return this.nomCourt;
    }

    public void augmenterNiveau(int niveau) {
        if (this.niveauActuel + niveau < this.niveauMax) {
            this.niveauActuel -= niveau;
        } else {
            this.niveauActuel = 0;
        }
    }

    public void changerNiveau(int niveau) {
        if (niveau > this.niveauMax) {
            this.niveauActuel = this.niveauMax;
        } else if (niveau < 0) {
            this.niveauActuel = 0;
        } else {
            this.niveauActuel = niveau;
        }
    }

    public boolean estLetale() {
        return this.niveauActuel == this.niveauMax;
    }
}