package hopital.sante.caracteristiques;

import java.util.Arrays;

/**
 * Enumération représentant les différents niveaux de budget disponibles pour un hôpital.
 * Chaque niveau est défini par une plage de valeurs minimale et maximale.
 * Permet de gérer dynamiquement un budget actuel et de déterminer son niveau.
 */
public enum Budget {

    /**
     * Budget inexistant : de 0 à 999.
     */
    INEXISTANT(0, 999),

    /**
     * Budget médiocre : de 1000 à 4999.
     */
    MEDIOCRE(1000, 4999),

    /**
     * Budget insuffisant : de 5000 à 9999.
     */
    INSUFFISANT(5000, 9999),

    /**
     * Budget faible : de 10000 à 49999.
     */
    FAIBLE(10000, 49999),

    /**
     * Budget moyen : de 50000 à 99999.
     */
    MOYEN(50000, 99999),

    /**
     * Budget élevé : de 100000 à Integer.MAX_VALUE.
     */
    ELEVE(100000, Integer.MAX_VALUE);

    private final int minValeur;
    private final int maxValeur;
    private static int valeurActuelle;

    /**
     * Constructeur privé de l'enumération.
     *
     * @param minValeur La valeur minimale pour ce niveau de budget.
     * @param maxValeur La valeur maximale pour ce niveau de budget.
     */
    Budget(int minValeur, int maxValeur) {
        this.minValeur = minValeur;
        this.maxValeur = maxValeur;
    }

    /**
     * Obtient la valeur actuelle du budget.
     *
     * @return La valeur actuelle du budget.
     */
    public static int getValeurActuelle() {
        return valeurActuelle;
    }

    /**
     * Définit une nouvelle valeur pour le budget actuel.
     * Si la valeur fournie est négative, elle est ramenée à zéro.
     *
     * @param nouvelleValeur La nouvelle valeur à définir pour le budget.
     */
    public static void setValeurActuelle(int nouvelleValeur) {
        valeurActuelle = Math.max(0, nouvelleValeur);
    }

    /**
     * Détermine le niveau de budget correspondant à la valeur actuelle.
     *
     * @return Le niveau de budget actuel.
     */
    public static Budget getNiveauActuel() {
        return Arrays.stream(values())
                .filter(budget -> valeurActuelle >= budget.minValeur && valeurActuelle < budget.maxValeur)
                .findFirst()
                .orElseGet(() -> {
                    if (valeurActuelle < MEDIOCRE.minValeur) return INEXISTANT;
                    if (valeurActuelle < INSUFFISANT.minValeur) return MEDIOCRE;
                    if (valeurActuelle < FAIBLE.minValeur) return INSUFFISANT;
                    if (valeurActuelle < MOYEN.minValeur) return FAIBLE;
                    if (valeurActuelle < ELEVE.minValeur) return MOYEN;
                    return ELEVE;
                });
    }

    /**
     * Retourne une représentation textuelle du niveau de budget.
     *
     * @return Une chaîne de caractères représentant le niveau de budget,
     *         incluant son nom et sa plage de valeurs.
     */
    @Override
    public String toString() {
        return this.name() + " (" + this.minValeur + " - " + this.maxValeur + ")";
    }

    /**
     * Ajuste la valeur actuelle du budget en l'augmentant ou la diminuant.
     * Si l'ajustement est négatif, la valeur est ramenée à zéro si elle descend en dessous.
     *
     * @param augmenter Indique si le budget doit être augmenté (true) ou diminué (false).
     * @param montant   Le montant par lequel ajuster le budget.
     */
    public static void ajusterBudget(boolean augmenter, int montant) {
        valeurActuelle = augmenter
                ? valeurActuelle + montant
                : Math.max(0, valeurActuelle - montant);
    }
}
