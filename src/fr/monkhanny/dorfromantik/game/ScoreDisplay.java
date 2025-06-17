package fr.monkhanny.dorfromantik.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Classe ScoreDisplay, utilisée pour afficher le score du joueur à l'écran.
 * 
 * @version 1.0
 * @author Lenny FOULOU
 */

public class ScoreDisplay {
    private int score;
    private Font font;
    private int x, y; // Position du texte

    /**
     * Constructeur de la classe ScoreDisplay.
     * 
     * @param font La police utilisée pour afficher le score.
     * @param x La position X où le score sera affiché.
     * @param y La position Y où le score sera affiché.
     */

    public ScoreDisplay(Font font, int x, int y) {
        this.font = font;
        this.x = x;
        this.y = y;
    }

    /**
     * Met à jour le score à afficher.
     * 
     * @param score Le nouveau score à afficher.
     */

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Met à jour la position où le score sera affiché.
     * 
     * @param x La nouvelle position X du score.
     * @param y La nouvelle position Y du score.
     */

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Dessine le score à l'écran.
     * 
     * @param g L'objet Graphics utilisé pour dessiner.
     */

    public void draw(Graphics g) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, x, y);
    }
}