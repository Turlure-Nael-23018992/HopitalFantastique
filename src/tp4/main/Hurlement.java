package tp4;

/**
 * Représente un hurlement émis par un lycanthrope.
 * Les hurlements peuvent exprimer différents types d'intentions ou de messages.
 */
public class Hurlement {

    /**
     * Enumération des types de hurlements possibles.
     */
    public enum TypeHurlement {
        MEUTE,       // Hurlement exprimant l'appartenance à une meute.
        DOMINATION,  // Hurlement exprimant la domination.
        SOUMISSION,  // Hurlement exprimant la soumission.
        AGRESSIVITE  // Hurlement exprimant l'agressivité.
    }

    private final Lycanthrope emetteur; // Le lycanthrope qui émet le hurlement.
    private final TypeHurlement type;   // Le type de hurlement.
    private final String message;       // Le contenu du hurlement.

    /**
     * Constructeur de la classe Hurlement.
     *
     * @param emetteur Le lycanthrope qui émet le hurlement.
     * @param type Le type de hurlement.
     * @param message Le message associé au hurlement.
     */
    public Hurlement(Lycanthrope emetteur, TypeHurlement type, String message) {
        this.emetteur = emetteur;
        this.type = type;
        this.message = message;
    }

    /**
     * Récupère l'émetteur du hurlement.
     *
     * @return Le lycanthrope qui a émis le hurlement.
     */
    public Lycanthrope getEmetteur() {
        return emetteur;
    }

    /**
     * Récupère le type de hurlement.
     *
     * @return Le type de hurlement (MEUTE, DOMINATION, etc.).
     */
    public TypeHurlement getType() {
        return type;
    }

    /**
     * Récupère le message du hurlement.
     *
     * @return Le message associé au hurlement.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retourne une représentation textuelle du hurlement.
     *
     * @return Une chaîne de caractères décrivant le hurlement.
     */
    @Override
    public String toString() {
        return "Hurlement{" +
                "émetteur=" + emetteur.getNom() +
                ", type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}