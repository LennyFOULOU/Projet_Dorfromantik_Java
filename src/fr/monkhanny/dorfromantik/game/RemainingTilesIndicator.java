package fr.monkhanny.dorfromantik.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.RadialGradientPaint;

/**
 * Classe RemainingTilesIndicator, utilisée pour représenter graphiquement l'indicateur
 * des tuiles restantes dans le jeu. Affiche une pile d'hexagones pour visualiser les tuiles
 * disponibles et leur décompte.
 * 
 * @version 1.0
 * @author Moncef STITI, Khalid CHENOUNA
 */

public class RemainingTilesIndicator {
    private int remainingTiles;
    private int maxTiles;

    /**
     * Constructeur de la classe RemainingTilesIndicator.
     * 
     * @param maxTiles Le nombre maximum de tuiles disponibles au départ.
     */

    public RemainingTilesIndicator(int maxTiles) {
        this.maxTiles = maxTiles;
        this.remainingTiles = maxTiles;
    }

    /**
     * Met à jour le nombre de tuiles restantes, en s'assurant qu'il reste
     * dans les limites valides (entre 0 et maxTiles).
     * 
     * @param remainingTiles Le nouveau nombre de tuiles restantes.
     */

    public void setRemainingTiles(int remainingTiles) {
        this.remainingTiles = Math.max(0, Math.min(remainingTiles, maxTiles));
    }

    /**
     * Dessine l'indicateur des tuiles restantes à une position donnée.
     * 
     * @param g L'objet Graphics utilisé pour dessiner.
     * @param x La position X de l'indicateur.
     * @param y La position Y de l'indicateur.
     */

    public void draw(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        int tileWidth = 50; // Largeur de l'assiette
        int tileHeight = 12; // Hauteur de l'assiette
        int gap = 1; // Écart entre les tuiles
    
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(2));  // Appliquer un contour légèrement plus épais
    
        // Dessiner chaque hexagone, à partir du bas de la fenêtre
        for (int i = 0; i < remainingTiles; i++) {
            int currentY = y + (maxTiles - remainingTiles + i) * (tileHeight + gap); // Positionnement fixe
    
            Polygon polygon = createHexagon(x, currentY, tileWidth, tileHeight); // Créer un hexagone
    
            // Appliquer un dégradé radial avec des couleurs bleu moyen et plus foncé
            RadialGradientPaint gradient = new RadialGradientPaint(
                    x + tileWidth / 2, currentY + tileHeight / 2, tileWidth / 2, 
                    new float[]{0.0f, 1.0f}, new Color[]{new Color(70, 130, 180), new Color(30, 60, 100)} // Bleu moyen au bleu foncé
            );
            g2d.setPaint(gradient);
            g2d.fillPolygon(polygon);  // Remplir avec le dégradé
    
            // Contour solide autour de l'hexagone, en bleu foncé
            g2d.setColor(new Color(10, 30, 60)); // Contour bleu foncé
            g2d.drawPolygon(polygon);  // Dessiner le contour
        }
    
        // Affichage du nombre de tuiles restantes en haut de la pile
        g2d.setColor(Color.BLACK); // Couleur du texte
        g2d.setFont(g2d.getFont().deriveFont(22f)); // Taille de la police
        String text = String.valueOf(remainingTiles); // Nombre de tuiles restantes
        int textWidth = g2d.getFontMetrics().stringWidth(text); // Largeur du texte
        int textX = x + (tileWidth - textWidth) / 2; // Centrer le texte horizontalement
        int textY = y + (maxTiles - remainingTiles) * (tileHeight + gap) - 10; // Position juste au-dessus du premier hexagone
    
        g2d.drawString(text, textX, textY); // Dessiner le texte
    
        g2d.dispose();
    }

    /**
     * Retourne la hauteur totale occupée par les tuiles restantes dans l'indicateur.
     * 
     * @return La hauteur totale en pixels.
     */

    public int getTotalHeight() {
        int tileHeight = 12; // Hauteur de l'assiette
        int gap = 1; // Espace entre les tuiles
        return remainingTiles * (tileHeight + gap);
    }

    /**
     * Crée un polygone hexagonal représentant une tuile.
     * 
     * @param x La position X de l'hexagone.
     * @param y La position Y de l'hexagone.
     * @param width La largeur de l'hexagone.
     * @param height La hauteur de l'hexagone.
     * @return Un objet Polygon représentant l'hexagone.
     */
    
    private Polygon createHexagon(int x, int y, int width, int height) {
        Polygon hexagon = new Polygon();

        // Calcul des 6 points de l'hexagone
        hexagon.addPoint(x, y); // Point gauche supérieur
        hexagon.addPoint(x + width / 2, y - height / 2); // Point supérieur central
        hexagon.addPoint(x + width, y); // Point droit supérieur
        hexagon.addPoint(x + width, y + height); // Point droit inférieur
        hexagon.addPoint(x + width / 2, y + height + height / 2); // Point inférieur central
        hexagon.addPoint(x, y + height); // Point gauche inférieur

        return hexagon;
    }
}
