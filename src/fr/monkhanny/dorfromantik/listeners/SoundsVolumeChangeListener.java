package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.utils.MusicPlayer;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

/**
 * Listener qui écoute les changements dans un slider de volume pour ajuster les paramètres sonores
 * 
 * @version 1.0
 * @author Moncef STITI
 * @see ChangeListener
 * @see JSlider
 */
public class SoundsVolumeChangeListener implements ChangeListener {
    /**
     * Référence au slider de volume
     */
    private JSlider slider;

    /**
     * Constructeur pour initialiser les variables du listener.
     *
     * @param slider Référence au slider de volume
     */
    public SoundsVolumeChangeListener(JSlider slider) {
        this.slider = slider;
    }

    /**
     * Méthode appelée lorsqu'un changement est détecté dans le slider de volume.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        // Récupérer la valeur du slider spécifique
        Options.SOUNDS_VOLUME = slider.getValue();
        MusicPlayer.setVolume(MusicPlayer.getSoundClip(), Options.SOUNDS_VOLUME);
    }
}
