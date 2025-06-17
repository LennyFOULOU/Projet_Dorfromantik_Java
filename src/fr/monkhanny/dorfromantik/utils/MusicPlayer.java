package fr.monkhanny.dorfromantik.utils;

import fr.monkhanny.dorfromantik.enums.Musics;
import fr.monkhanny.dorfromantik.enums.Sounds;
import fr.monkhanny.dorfromantik.Options;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.FloatControl;

/**
 * Classe utilitaire pour jouer de la musique et des bruitages.
 * 
 * Cette classe fournit des méthodes statiques pour charger et jouer de la musique
 * et des bruitages à partir de fichiers audio.
 * 
 * @author Moncef STITI
 * @version 1.0
 */
public class MusicPlayer {
    
    /**
     * Clip pour la musique.
     */
    private static Clip musicClip;

    /**
     * Clip pour les bruitages.
     */
    private static Clip soundClip;

    /**
     * Indique si la musique est en cours de lecture.
     */
    private static boolean isPlayingMusic = false;

    /**
     * Indique si un bruitage est en cours de lecture.
     */
    private static boolean isPlayingSound = false;

    /**
     * Constructeur par défaut pour la classe MusicPlayer.
     */
    public MusicPlayer() {
        // Constructeur par défaut
    }

    /**
     * Charge un fichier audio à partir du chemin spécifié.
     * @param music Musique à charger
     */
    public static void loadMusic(Musics music) {
        if (music == Musics.MAIN_MENU_MUSIC) {
            musicClip = SoundLoader.loadMusic(Musics.MAIN_MENU_MUSIC.getSoundsPath());
            if (musicClip != null) {
                setVolume(musicClip, Options.MUSIC_VOLUME);
            }
        }
    }

    /**
     * Charge un fichier audio à partir du chemin spécifié.
     * @param sound Bruitage à charger
     */
    public static void loadSound(Sounds sound) {
        if (sound == Sounds.SOUNDS1) {
            soundClip = SoundLoader.loadMusic(Sounds.SOUNDS1.getSoundsPath());  // Utilise soundClip pour les bruitages
            if (soundClip != null) {
                setVolume(soundClip, Options.SOUNDS_VOLUME);
            }
        }
    }

    /**
     * Joue la musique.
     */
    public static void playMusic() {
        if (musicClip != null && !isPlayingMusic && !Options.MUSIC_MUTED) {
            musicClip.start();
            isPlayingMusic = true;
        }
    }

    /**
     * Joue un bruitage.
     */
    public static void playSound() {
        if (soundClip != null && !isPlayingSound && !Options.SOUNDS_MUTED) {
            soundClip.start();
            isPlayingSound = true;
            soundClip.addLineListener(event -> {  // Réinitialiser isPlayingSound à la fin du son
                if (event.getType() == LineEvent.Type.STOP) {
                    soundClip.setFramePosition(0);  // Retour au début du son pour rejouer si nécessaire
                    isPlayingSound = false;
                }
            });
        }
    }

    /**
     * Met en pause la musique.
     */
    public static void pauseMusic() {
        if (musicClip != null && isPlayingMusic) {
            musicClip.stop();
            isPlayingMusic = false;
        }
    }

    /**
     * Arrête la musique.
     */
    public static void stopMusic() {
        if (musicClip != null) {
            musicClip.stop();
            musicClip.setFramePosition(0);
            isPlayingMusic = false;
        }
    }

    /**
     * Régler le volume du clip audio.
     * @param clip Clip audio
     * @param volume Volume à régler
     */
    public static void setVolume(Clip clip, int volume) {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = volumeControl.getMaximum() - volumeControl.getMinimum();
            float gain = (range * volume / 100f) + volumeControl.getMinimum();
            volumeControl.setValue(gain);
        }
    }

    /**
     * Indique si la musique est en cours de lecture.
     * @return true si la musique est en cours de lecture, false sinon
     */
    public static boolean isPlayingMusic() {
        return isPlayingMusic;
    }

    /**
     * Indique si un bruitage est en cours de lecture.
     * @return true si un bruitage est en cours de lecture, false sinon
     */
    public static boolean isPlayingSound() {
        return isPlayingSound;
    }

    /**
     * Récupérer le clip de musique.
     * @return Clip de musique
     */
    public static Clip getMusicClip() {
        return musicClip;
    }

    /**
     * Récupérer le clip de bruitage.
     * @return Clip de bruitage
     */
    public static Clip getSoundClip() {
        return soundClip;
    }
}