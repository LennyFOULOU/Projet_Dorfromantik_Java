package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.utils.MusicPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe MuteCheckBoxListener, un écouteur d'événements pour gérer les cases à cocher
 * permettant d'activer ou de désactiver la musique et les effets sonores (SFX).
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class MuteCheckBoxListener implements ActionListener {
    /**
     * Libellé indiquant le type d'option contrôlée par cet écouteur.
     * Par exemple, "Musique" pour la musique de fond ou "SFX" pour les effets sonores.
     */
    private String label;
    
    /**
     * Constructeur de MuteCheckBoxListener.
     *
     * @param label un libellé pour indiquer le type d'option (par exemple "Musique" ou "SFX")
     */
    public MuteCheckBoxListener(String label) {
        this.label = label;
    }
    
    /**
     * Gère les événements de clic sur une case à cocher.
     * Permet d'activer ou de désactiver la musique ou les effets sonores en fonction
     * du libellé fourni.
     *
     * @param e l'événement ActionEvent déclenché par un clic sur une case à cocher
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox checkBox = (JCheckBox) e.getSource();
        
        if ("Musique".equals(label)) {
            Options.MUSIC_MUTED = !checkBox.isSelected();
            if (Options.MUSIC_MUTED) {
                MusicPlayer.pauseMusic();
            } else {
                MusicPlayer.playMusic();
            }
        } else if ("SFX".equals(label)) {
            // Active ou désactive les effets sonores (SFX)
            Options.SOUNDS_MUTED = !checkBox.isSelected();
        }
    }
}
