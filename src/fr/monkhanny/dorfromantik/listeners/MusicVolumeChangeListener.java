package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.utils.MusicPlayer;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
/**
 * Classe MusicVolumeChangeListener, un écouteur d'événements.
 * Gère les changements de volume de la musique via un composant JSlider.
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class MusicVolumeChangeListener implements ChangeListener {
    /**
     * Le slider associé permettant de contrôler le volume de la musique.
     */
    private JSlider slider;

    /**
     * Constructeur de MusicVolumeChangeListener.
     *
     * @param slider le composant JSlider utilisé pour ajuster le volume
     */
    public MusicVolumeChangeListener(JSlider slider) {
        this.slider = slider;
    }

    /**
     * Gère les changements d'état du slider (JSlider).
     * Met à jour le volume de la musique en fonction de la position actuelle du slider.
     *
     * @param e l'événement ChangeEvent déclenché par un changement de valeur sur le slider
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        // Récupérer la valeur du slider spécifique
        Options.MUSIC_VOLUME = slider.getValue();
        // Applique le nouveau volume au lecteur de musique
        MusicPlayer.setVolume(MusicPlayer.getMusicClip(), Options.MUSIC_VOLUME);
    }
}
