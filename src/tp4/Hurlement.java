package tp4;

public class Hurlement {
    public enum TypeHurlement {
        MEUTE, // Hurlement exprimant l'appartenance à une meute
        DOMINATION, // Hurlement exprimant la domination
        SOUMISSION, // Hurlement exprimant la soumission
        AGRESSIVITE // Hurlement exprimant l'agressivité
    }

    private final Lycanthrope emetteur; // Le lycanthrope qui émet le hurlement
    private final TypeHurlement type; // Le type de hurlement
    private final String message; // Le contenu du hurlement

    // Constructeur
    public Hurlement(Lycanthrope emetteur, TypeHurlement type, String message) {
        this.emetteur = emetteur;
        this.type = type;
        this.message = message;
    }

    // Getter pour l'émetteur
    public Lycanthrope getEmetteur() {
        return emetteur;
    }

    // Getter pour le type de hurlement
    public TypeHurlement getType() {
        return type;
    }

    // Getter pour le message
    public String getMessage() {
        return message;
    }

    // Méthode pour afficher les caractéristiques du hurlement
    @Override
    public String toString() {
        return "Hurlement{" +
                "émetteur=" + emetteur.getNom() +
                ", type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}