package hopital.creatures.caracteristiques;

/**
 * Enumération représentant les différents états de moral d'une créature.
 * Chaque état a une plage de valeurs, avec une valeur actuelle qui peut être ajustée.
 */
public enum Moral {

    /** Moral très élevé (80 à 100) */
    AUTOP(80, 100),

    /** Moral assez élevé (60 à 79) */
    COOL(60, 79),

    /** Moral moyen (40 à 59) */
    MOYEN(40, 59),

    /** Moral bas (0 à 39) */
    PASCOOL(0, 39);

    private final int minValeur;  // Valeur minimale du moral
    private final int maxValeur;  // Valeur maximale du moral
    private int valeurActuelle;   // Valeur actuelle du moral

    /**
     * Constructeur de l'énumération.
     *
     * @param minValeur La valeur minimale du moral.
     * @param maxValeur La valeur maximale du moral.
     */
    Moral(int minValeur, int maxValeur) {
        this.minValeur = minValeur;
        this.maxValeur = maxValeur;
        this.valeurActuelle = maxValeur;
    }

    /**
     * Récupère la valeur actuelle du moral.
     *
     * @return La valeur actuelle du moral.
     */
    public int getValeur() {
        return this.valeurActuelle;
    }

    /**
     * Modifie la valeur actuelle du moral si elle est dans la plage définie.
     *
     * @param valeur La nouvelle valeur du moral à définir.
     */
    public void setValeur(int valeur) {
        if (valeur >= minValeur && valeur <= maxValeur) {
            this.valeurActuelle = valeur;
        }
    }

    /**
     * Modifie la valeur actuelle du moral en fonction d'un booléen et d'un nombre.
     * Si le booléen est vrai, le moral augmente. Si faux, il diminue.
     *
     * @param bool Si vrai, le moral augmente. Si faux, il diminue.
     * @param nb Le nombre à ajouter ou à soustraire à la valeur actuelle du moral.
     */
    public void state(boolean bool, int nb) {
        if (bool && this.getValeur() < 96) {
            setValeur(this.getValeur() + nb);
        } else if (!bool && this.getValeur() > 4) {
            setValeur(this.getValeur() - nb);
        }
    }

    /**
     * Vérifie si la valeur donnée est dans la plage de valeurs de l'état moral actuel.
     *
     * @param valeur La valeur à vérifier.
     * @return True si la valeur est dans la plage, sinon false.
     */
    public boolean isInRange(int valeur) {
        return valeur >= minValeur && valeur <= maxValeur;
    }
}
