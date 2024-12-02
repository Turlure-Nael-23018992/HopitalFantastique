package tp4.main;

/**
 * Interface générique Comparable.
 * Permet de comparer des objets pour définir un ordre.
 *
 * @param <T> Le type des objets à comparer.
 */
public interface Comparable<T> {
    /**
     * Compare l'objet actuel avec l'objet spécifié pour définir leur ordre relatif.
     *
     * @param o L'objet à comparer.
     * @return Un entier négatif, zéro ou un entier positif si l'objet actuel
     *         est respectivement inférieur, égal ou supérieur à l'objet spécifié.
     */
    int compareTo(T o);
}