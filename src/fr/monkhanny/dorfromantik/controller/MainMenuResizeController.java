package fr.monkhanny.dorfromantik.controller;

import fr.monkhanny.dorfromantik.gui.MainMenu;

/**
 * Contrôleur pour gérer les redimensionnements de la fenêtre du menu principal.
 * Ce contrôleur attache un gestionnaire de redimensionnement pour ajuster dynamiquement 
 * les éléments du menu principal lorsque la fenêtre est redimensionnée.
 *
 * @version 1.0
 * @author 
 * Moncef STITI, Khalid CHENOUNA
 */
public class MainMenuResizeController {

    /**
     * Instance du menu principal.
     */
    private final MainMenu mainMenu;

    /**
     * Gestionnaire de redimensionnement pour le menu principal.
     */
    private final MainMenuResizeHandler resizeHandler;

    /**
     * Constructeur qui initialise le contrôleur et attache le gestionnaire de redimensionnement.
     *
     * @param mainMenu Instance du menu principal
     */
    public MainMenuResizeController(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.resizeHandler = new MainMenuResizeHandler(mainMenu);
        addComponentListener();
    }

    /**
     * Attache le gestionnaire de redimensionnement au menu principal.
     */
    private void addComponentListener() {
        mainMenu.addComponentListener(resizeHandler);
    }
}
