package fr.monkhanny.dorfromantik.controller;

import fr.monkhanny.dorfromantik.gui.MainMenu;
import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.gui.ButtonHoverAnimator;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Gestionnaire de redimensionnement pour le menu principal.
 * Ce gestionnaire ajuste dynamiquement les éléments du menu principal 
 * (titre, boutons, etc.) lorsque la fenêtre est redimensionnée.
 *
 * @version 1.0
 * @author 
 * Moncef STITI, Khalid CHENOUNA
 */
public class MainMenuResizeHandler extends ComponentAdapter {

    /**
     * Instance du menu principal à ajuster.
     */
    private final MainMenu mainMenu;

    /**
     * Constructeur qui initialise le gestionnaire avec une référence au menu principal.
     *
     * @param mainMenu Instance du menu principal
     */
    public MainMenuResizeHandler(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * Méthode appelée lorsque la fenêtre du menu principal est redimensionnée.
     * Ajuste la taille de la police du titre et des boutons en fonction de la nouvelle largeur de la fenêtre.
     *
     * @param e Événement de redimensionnement
     * @see ComponentEvent
     */
    @Override
    public void componentResized(ComponentEvent e) {
        int mainMenuWidth = mainMenu.getWidth();

        // Ajuster la taille de la police du titre en fonction de la largeur de la fenêtre
        float newFontSize = Options.BASE_TITLE_FONT_SIZE * (mainMenuWidth / 900f);
        mainMenu.getTitleLabel().updateTitleFont(newFontSize);

        // Mettre à jour les polices des boutons
        mainMenu.getButtonPanel().updateButtonFonts(mainMenuWidth);

        // Mettre à jour la taille de la police originale pour les animations de survol des boutons
        ButtonHoverAnimator.updateOriginalFont(mainMenuWidth / 30f);
    }
}
