package fr.monkhanny.dorfromantik.gui;

/**
 * Représente une étape dans le tutoriel du jeu.
 * Chaque étape comprend un titre, un texte descriptif et une image associée.
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class Step {

    /**
     * Titre de l'étape.
     */
    private String title;

    /**
     * Texte descriptif de l'étape.
     */
    private String text;

    /**
     * Chemin vers l'image associée à l'étape.
     */
    private String imagePath;

    /**
     * Constructeur pour créer une nouvelle étape de tutoriel.
     *
     * @param title     Titre de l'étape.
     * @param text      Texte descriptif de l'étape.
     * @param imagePath Chemin vers l'image associée.
     */
    public Step(String title, String text, String imagePath) {
        this.title = title;
        this.text = text;
        this.imagePath = imagePath;
    }

    /**
     * Obtient le texte descriptif de l'étape.
     *
     * @return Le texte descriptif de l'étape.
     */

    public String getText() {
        return text;
    }

    /**
     * Obtient le chemin vers l'image associée à l'étape.
     *
     * @return Le chemin de l'image.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Obtient le titre de l'étape.
     *
     * @return Le titre de l'étape.
     */
    public String getTitle() {
        return title;
    }
}
