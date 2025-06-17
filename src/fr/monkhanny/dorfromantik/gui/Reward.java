package fr.monkhanny.dorfromantik.gui;

import javax.swing.*;

// Classe pour représenter une récompense
/**
 * Classe représentant une récompense dans le jeu.
 * Une récompense peut avoir un nom, une description, un état (déverrouillée ou non)
 * et une icône associée.
 * @version 1.0
 * @author Moncef STITI
 */
public class Reward {

    /**
     * Nom de la récompense.
     */
    private String name;

    /**
     * Description de la récompense, fournissant des informations ou un contexte sur celle-ci.
     */
    private String description;

    /**
     * État indiquant si la récompense est déverrouillée.
     * {@code true} si elle est déverrouillée, {@code false} sinon.
     */
    private boolean isUnlocked;

    /**
     * Icône représentant visuellement la récompense.
     */
    private ImageIcon icon;

    /**
     * Constructeur permettant de créer une nouvelle récompense.
     *
     * @param name        le nom de la récompense
     * @param description la description de la récompense
     * @param isUnlocked  {@code true} si la récompense est déverrouillée, {@code false} sinon
     * @param icon        l'icône associée à la récompense
     */
    public Reward(String name, String description, boolean isUnlocked, ImageIcon icon) {
        this.name = name;
        this.description = description;
        this.isUnlocked = isUnlocked;
        this.icon = icon;
    }

    /**
     * Obtient le nom de la récompense.
     *
     * @return le nom de la récompense
     */
    public String getName() {
        return name;
    }

    /**
     * Obtient la description de la récompense.
     *
     * @return la description de la récompense
     */
    public String getDescription() {
        return description;
    }

    /**
     * Vérifie si la récompense est déverrouillée.
     *
     * @return {@code true} si la récompense est déverrouillée, {@code false} sinon
     */
    public boolean isUnlocked() {
        return isUnlocked;
    }

    /**
     * Obtient l'icône représentant la récompense.
     *
     * @return l'icône de la récompense
     */
    public ImageIcon getIcon() {
        return icon;
    }
}