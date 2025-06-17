package fr.monkhanny.dorfromantik.game;

import java.awt.*;
import javax.swing.*;

/**
 * Représente une cellule de base sur le plateau de jeu.
 * C'est la classe parente pour la classe Tile.
 * @version 1.0
 * @author Lenny FOULOU, Khalid CHENOUNA
 */
public class Cell extends JComponent {
    
/**
 * Le plateau de jeu auquel cette cellule appartient.
 */
private Board board;  

/**
 * La coordonnée x du centre de la cellule.
 */
public int x;        

/**
 * La coordonnée y du centre de la cellule.
 */
public int y;        

/**
 * Le rayon de la cellule (si on parle d'un hexagone, c'est le rayon de l'hexagone).
 */
public int radius;


    /**
     * Constructeur de la classe Cell.
     *
     * @param board  Le plateau de jeu auquel cette cellule appartient
     * @param x      La coordonnée x du centre de la cellule
     * @param y      La coordonnée y du centre de la cellule
     * @param radius Le rayon de la cellule
     */
    public Cell(Board board, int x, int y, int radius) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.radius = radius;

        // Définir la taille du composant pour l'affichage
        this.setSize(2 * radius, 2 * radius);
        this.setLocation(x - radius, y - radius);
    }

    /**
     * Récupère le plateau de jeu auquel cette cellule appartient.
     *
     * @return Le plateau de jeu
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Récupère la coordonnée x du centre de la cellule.
     *
     * @return La coordonnée x
     */
    public int getXCoord() {
        return x;
    }

    /**
     * Récupère la coordonnée y du centre de la cellule.
     *
     * @return La coordonnée y
     */
    public int getYCoord() {
        return y;
    }

    /**
     * Récupère le rayon de la cellule.
     *
     * @return Le rayon de la cellule
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Convertit une coordonnée en degrés en une valeur de 0 à 360.
     * 
     * @param angle L'angle en degrés
     * @return La valeur normalisée entre 0 et 360 degrés
     */
    public static double to360Degrees(double angle) {
        angle = angle % 360;
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    /**
     * Permet de changer la position de la cellule.
     * Cette méthode met à jour les coordonnées x et y et déplace la cellule dans le composant graphique.
     *
     * @param x La nouvelle coordonnée x du centre de la cellule
     * @param y La nouvelle coordonnée y du centre de la cellule
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        
        // Mettre à jour la position de la cellule sur le plateau
        this.setLocation(x - radius, y - radius);
    }

    /**
     * Méthode pour redessiner la cellule si nécessaire.
     * Elle sera surchargée par les classes dérivées comme Tile.
     *
     * @param g Le contexte graphique
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
