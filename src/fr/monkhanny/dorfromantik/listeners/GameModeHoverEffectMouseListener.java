package fr.monkhanny.dorfromantik.listeners;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import fr.monkhanny.dorfromantik.enums.Sounds;
import fr.monkhanny.dorfromantik.utils.MusicPlayer;


/**
 * Listener pour ajouter un effet visuel et sonore lors du survol et du clic sur un bouton.
 * Cette classe change la couleur d'arrière-plan du bouton lorsqu'il est survolé, cliqué ou relâché,
 * et joue un son lors du survol.
 * 
 * @version 1.0
 * @author Lenny FOULOU, Khalid Chenouna
 */
public class GameModeHoverEffectMouseListener extends MouseAdapter {

    /**
     * La couleur d'arrière-plan par défaut du bouton.
     */
    private Color defaultColor;

    /**
     * La couleur d'arrière-plan lorsque le bouton est survolé.
     */
    private Color hoverColor;
    
    /**
     * La couleur d'arrière-plan lorsque le bouton est cliqué.
     */
    private Color clickColor;


    /**
     * Constructeur pour initialiser les couleurs d'effet du bouton.
     * 
     * @param defaultColor la couleur d'arrière-plan par défaut du bouton.
     * @param hoverColor la couleur d'arrière-plan lors du survol du bouton.
     * @param clickColor la couleur d'arrière-plan lors du clic sur le bouton.
     */
    public GameModeHoverEffectMouseListener(Color defaultColor, Color hoverColor, Color clickColor) {
        this.defaultColor = defaultColor;
        this.hoverColor = hoverColor;
        this.clickColor = clickColor;
    }

    /**
     * Méthode appelée lorsque le curseur entre dans la zone du bouton.
     * Elle modifie la couleur d'arrière-plan du bouton et joue un son.
     *
     * @param e l'événement de survol de la souris.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JButton button) {
            button.setBackground(hoverColor);
            MusicPlayer.loadSound(Sounds.SOUNDS1);  // Charge le son
            MusicPlayer.playSound();                // Joue le son
        }
    }

    /**
     * Méthode appelée lorsque le curseur quitte la zone du bouton.
     * Elle rétablit la couleur d'arrière-plan par défaut du bouton.
     *
     * @param e l'événement de sortie de la souris.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JButton button) {
            button.setBackground(defaultColor);
        }
    }

    /**
     * Méthode appelée lorsque le bouton est pressé.
     * Elle change la couleur d'arrière-plan du bouton pour la couleur de clic.
     *
     * @param e l'événement de pression du bouton de la souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JButton button) {
            button.setBackground(clickColor);
        }
    }

    /**
     * Méthode appelée lorsque le bouton est relâché.
     * Elle remet la couleur d'arrière-plan du bouton à celle de survol.
     *
     * @param e l'événement de relâchement du bouton de la souris.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() instanceof JButton button) {
            button.setBackground(hoverColor);
        }
    }
}
