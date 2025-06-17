package fr.monkhanny.dorfromantik.utils;

/**
 * Représente un score d'un joueur dans une série.
 * 
 * Cette classe est utilisée pour stocker les informations sur un score
 * dans une série donnée. Elle permet de garder un enregistrement de la série jouée et du score.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class PlayerScore {
    /**
     * Nom de la série
     */
    private String serie;

    /**
     * Score
     */
    private int score;

    /**
     * Constructeur pour initialiser les variables du score.
     *
     * @param serie Nom de la série
     * @param score Score
     */
    public PlayerScore(String serie, int score) {
        this.serie = serie;
        this.score = score;
    }

    /**
     * Récupérer le nom de la série.
     *
     * @return Nom de la série
     */
    public String getSerieName() {
        return serie;
    }

    /**
     * Récupérer le score.
     *
     * @return Score
     */
    public int getScore() {
        return score;
    }
}
