package hopital.creatures.caracteristiques;

public enum Moral {
    AUTOP(80, 100),
    COOL(60, 79),
    MOYEN(40, 59),
    PASCOOL(0, 39);

    private final int minValeur;
    private final int maxValeur;
    private int valeurActuelle;

    Moral(int minValeur, int maxValeur) {
        this.minValeur = minValeur;
        this.maxValeur = maxValeur;
        this.valeurActuelle = maxValeur;
    }

    public int getValeur() {
        return this.valeurActuelle;
    }

    public void setValeur(int valeur) {
        if (valeur >= minValeur && valeur <= maxValeur) {
            this.valeurActuelle = valeur;
        }
    }

    public void state(boolean bool, int nb) {
        if (bool && this.getValeur() < 96) {
            setValeur(this.getValeur() + nb);
        } else if (!bool && this.getValeur() > 4) {
            setValeur(this.getValeur() - nb);
        }
    }

    public boolean isInRange(int valeur) {
        return valeur >= minValeur && valeur <= maxValeur;
    }
}