package fr.monkhanny.dorfromantik.game;

import fr.monkhanny.dorfromantik.listeners.GameZoomListener;
import fr.monkhanny.dorfromantik.listeners.GameArrowKeyListener;
import fr.monkhanny.dorfromantik.listeners.GameSpaceKeyListener;
import fr.monkhanny.dorfromantik.listeners.GameMouseClickListener;
import fr.monkhanny.dorfromantik.listeners.GameMouseWheelListener;
import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.enums.Fonts;
import fr.monkhanny.dorfromantik.gui.GameControlsMenu;
import fr.monkhanny.dorfromantik.gui.GameOver;
import fr.monkhanny.dorfromantik.utils.Database;

import java.util.List;
import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Représente le plateau de jeu.
 * Gère les tuiles, les positions disponibles, le zoom, les déplacements, et l'interaction avec l'utilisateur.
 * 
 * @version 1.0
 * @author Moncef STITI, Lenny FOULOU, Khalid CHENOUNA
 */
public class Board extends JPanel{
    
    /**
     * Liste des tuiles présentes sur le plateau de jeu.
     */
    private List<Tile> tiles;

    /**
     * Liste des positions disponibles où une tuile peut être placée sur le plateau.
     */
    private List<Point> availablePositions;

    /**
     * Objet de génération aléatoire utilisé pour déterminer certaines actions dans le jeu.
     */
    private Random random;

    /**
     * Référence au jeu en cours, permettant d'interagir avec la logique du jeu.
     */
    private Game game;

    /**
     * Fenêtre principale du jeu dans laquelle le plateau de jeu est affiché.
     */
    private JFrame gameFrame;

    /**
     * La tuile centrale du jeu, placée au démarrage du jeu.
     */
    private Tile centralTile;

    /**
     * Facteur de zoom initial, utilisé pour contrôler le niveau de zoom du plateau.
     */
    private double zoomFactor = 1.0;

    /**
     * Décalage horizontal du plateau, utilisé pour déplacer l'affichage du plateau.
     */
    private int offsetX = 0;

    /**
     * Décalage vertical du plateau, utilisé pour déplacer l'affichage du plateau.
     */
    private int offsetY = 0;

    /**
     * La prochaine tuile à placer sur le plateau, visible avant le placement effectif.
     */
    private Tile nextTile;

    /**
     * Position actuelle de la souris sur le plateau, utilisée pour placer la prochaine tuile.
     */
    private Point mousePosition;

    /**
     * Score actuel du joueur dans le jeu.
     */
    private int currentScore;

    /**
     * Objet représentant la base de données pour enregistrer les scores et autres informations.
     */
    private Database database;

    /**
     * Indicateur visuel du nombre de tuiles restantes à placer sur le plateau.
     */
    private RemainingTilesIndicator remainingTilesIndicator;

    /**
     * Menu contenant les contrôles du jeu, utilisé pour afficher ou masquer les options.
     */
    private GameControlsMenu controlsMenu;

    /**
     * Gestionnaire de score utilisé pour mettre à jour et calculer les scores du joueur.
     */
    private ScoreManager scoreManager;

    /**
     * Affichage du score actuel dans le jeu.
     */
    private ScoreDisplay scoreDisplay;


    /**
     * Constructeur pour initialiser le plateau de jeu avec la fenêtre principale et la graine aléatoire.
     * 
     * @param gameFrame la fenêtre du jeu
     * @param seed la graine aléatoire pour initialiser le générateur de nombres aléatoires
     */
    public Board(JFrame gameFrame, long seed) {
        this.gameFrame = gameFrame;
        this.tiles = new ArrayList<>();
        this.availablePositions = new ArrayList<>();
        this.random = new Random(seed);
        this.game = new Game(seed);
        scoreManager = new ScoreManager();

        Font scoreFont = Fonts.SCORE.getFont(30f);  // Remplacez par votre logique de chargement de police
        scoreDisplay = new ScoreDisplay(scoreFont, 0, 40); // Position fixe

        // Placer une tuile centrale au démarrage
        initializeCentralTile();

        // Ajouter un écouteur de molette de souris pour gérer le zoom
        gameFrame.addMouseWheelListener(new GameZoomListener(this));

        // Ajouter un écouteur de clavier pour déplacer le plateau
        gameFrame.addKeyListener(new GameArrowKeyListener(this));
        gameFrame.setFocusable(true);

        this.addMouseWheelListener(new GameMouseWheelListener(this));
        gameFrame.addKeyListener(new GameSpaceKeyListener(this));

        this.addMouseListener(new GameMouseClickListener(this));
        gameFrame.addKeyListener(new PauseGame(gameFrame, game));
        gameFrame.setFocusable(true);

        this.remainingTilesIndicator = new RemainingTilesIndicator(Options.MAX_TILE_NUMBER - 1);

        // Remplacer l'adaptateur anonyme par notre classe CustomMouseMotionAdapter
        this.addMouseMotionListener(new CustomMouseMotionAdapter(this));

        // Ajouter le menu des contrôles
        controlsMenu = new GameControlsMenu();
        setLayout(null); // Utiliser un layout absolu pour placer le menu

        // Ajouter le menu au panneau principal
        controlsMenu.setBounds(10, getHeight() + 350, 400, 400);
        add(controlsMenu);

        // Remplacer l'adaptateur anonyme par notre classe CustomKeyAdapter
        gameFrame.addKeyListener(new CustomKeyAdapter(this));
    }


    /**
     * Récupère le menu des contrôles du jeu.
     * @return le menu des contrôles du jeu
     */
    public GameControlsMenu getControlsMenu() {
        return controlsMenu;
    }

    /**
     * Gère les événements de déplacement de la souris.
     * @param e l'événement MouseEvent déclenché par le déplacement de la souris
     */
    public void handleMouseMove(java.awt.event.MouseEvent e) {
        if (Options.isPaused) {
            return;
        }

        // Récupérer les coordonnées du curseur
        Point cursorPoint = e.getPoint();

        // Ajuster la position de la souris en fonction du zoom et des offsets
        int adjustedX = (int)((cursorPoint.x - offsetX) / zoomFactor);
        int adjustedY = (int)((cursorPoint.y - offsetY) / zoomFactor);

        // Vérifier si la souris est proche d'une des positions disponibles
        for (Point position : availablePositions) {
            if (new Point(adjustedX, adjustedY).distance(position) < 20) {
                mousePosition = position;
                controlsMenu.setControlsMenuVisible(false);
                repaint();  // Redessiner le plateau avec la tuile transparente
                return;
            }
        }

        // Si la souris n'est pas proche d'une position valide, ne rien faire
        mousePosition = null;
        controlsMenu.setControlsMenuVisible(false);
        repaint();  // Redessiner sans la tuile transparente
    }

    /**
     * Initialise la prochaine tuile à placer sur le plateau.
     */
    private void initializeNextTile() {
        int offsetX = 50;  // Décalage pour la position en haut à gauche
        int offsetY = 50;  // Décalage pour la position en haut à gauche
        this.nextTile = new Tile(this, offsetX, offsetY, 50);  // Création de la nouvelle tuile

    }

    /**
     * Récupère la prochaine tuile à placer sur le plateau.
     * @return la prochaine tuile à placer
     */
    public Tile getNextTile() { return nextTile; }

    /**
     * Gère les événements de touche de clavier
     */
    public void handleSpaceKeyPress() {
        if (Options.isPaused) {
            return;
        }
    
        // Calculer les limites du plateau (minX, minY, maxX, maxY)
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
    
        for (Tile tile : tiles) {
            minX = Math.min(minX, tile.getXCoord() - tile.getRadius());
            minY = Math.min(minY, tile.getYCoord() - tile.getRadius());
            maxX = Math.max(maxX, tile.getXCoord() + tile.getRadius());
            maxY = Math.max(maxY, tile.getYCoord() + tile.getRadius());
        }
    
        // Ajouter une marge aux dimensions calculées
        int margin = 50;
        minX -= margin;
        minY -= margin;
        maxX += margin;
        maxY += margin;
    
        // Calculer les dimensions totales avec la marge
        int totalWidth = maxX - minX;
        int totalHeight = maxY - minY;
    
        // Calculer le facteur de zoom pour que tout tienne dans la fenêtre
        double horizontalZoom = (double) getWidth() / totalWidth;
        double verticalZoom = (double) getHeight() / totalHeight;
    
        // Choisir le plus petit facteur de zoom pour s'assurer que tout est visible
        zoomFactor = Math.min(horizontalZoom, verticalZoom);
    
        // Ajuster les offsets pour centrer les tuiles
        int centerX = minX + totalWidth / 2;
        int centerY = minY + totalHeight / 2;
    
        offsetX = getWidth() / 2 - (int) (centerX * zoomFactor);
        offsetY = getHeight() / 2 - (int) (centerY * zoomFactor);
    
        // Redessiner le plateau avec les nouveaux paramètres
        repaint();
    }


    /**
     * Gère les événements de clic de souris pour placer une tuile à la position disponible 
     * la plus proche, si le jeu n'est pas en pause.
     *
     * @param e l'événement MouseEvent déclenché par un clic de souris
     */
    public void handleMouseClick(java.awt.event.MouseEvent e) {
        if (Options.isPaused) {
            return;
        }
    
        Point clickedPoint = e.getPoint();
        int adjustedX = (int)((clickedPoint.x - offsetX) / zoomFactor);
        int adjustedY = (int)((clickedPoint.y - offsetY) / zoomFactor);
    
        Point nearestPosition = null;
        double minDistance = Double.MAX_VALUE;
    
        for (Point position : availablePositions) {
            double distance = new Point(adjustedX, adjustedY).distance(position);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPosition = position;
            }
        }
    
        if (nearestPosition != null && minDistance < 20) {
            placeTileAtPosition(nearestPosition);
        }
    }
    


    /**
     * Initialise la tuile centrale au centre de la fenêtre de jeu et calcule 
     * les positions disponibles autour pour les placements futurs.
     */
    private void initializeCentralTile() {
        int centerX = gameFrame.getWidth() / 2;
        int centerY = gameFrame.getHeight() / 2;

        this.centralTile = new Tile(this, centerX, centerY, 50);
        addTile(centralTile);


        // Calculer les positions disponibles autour de la tuile centrale
        calculateAvailablePositions(centralTile);
        initializeNextTile();
    }

    /**
     * Ajoute une tuile au jeu, met à jour le gestionnaire de score 
     * et rafraîchit l'affichage du score.
     *
     * @param tile l'objet Tile à ajouter au jeu
     */
    public void addTile(Tile tile) { 
        tiles.add(tile);
        scoreManager.addTile(tile);
        currentScore = scoreManager.getCurrentScore();
        scoreDisplay.setScore(currentScore);
    }

    /**
     * Récupère la tuile située aux coordonnées spécifiées, si elle existe.
     *
     * @param x la coordonnée x à vérifier
     * @param y la coordonnée y à vérifier
     * @return l'objet Tile situé aux coordonnées spécifiées, ou {@code null} si aucune tuile n'est trouvée
     */
    public Tile getTileAt(int x, int y) {
        for (Tile tile : tiles) {
            if (Math.abs(tile.getXCoord() - x) < 5 && Math.abs(tile.getYCoord() - y) < 5) {
                return tile;
            }
        }
        return null;
    }

    /**
     * Récupère le random
     * @return random
     */
    public Random getRandom() { return random; }

    /**
     * Récupère la game
     * @return game
     */
    public Game getGame() { return game; }

    /**
     * Calcule les positions disponibles autour de la tuile donnée.
     * Les points rouges seront générés autour de cette tuile.
     *
     * @param tile La tuile pour laquelle on calcule les positions disponibles
     */
    private void calculateAvailablePositions(Tile tile) {
        int tileX = tile.getXCoord();
        int tileY = tile.getYCoord();
        int radius = (int) (tile.getRadius() * 1.72);  // Utiliser un rayon uniforme pour toutes les directions

        // Définir les directions possibles autour de la tuile (6 directions pour une tuile hexagonale)
        Point[] directions = {
            new Point(0, -radius),  // Nord 
            new Point((int)(radius * Math.sqrt(3) / 2), -radius / 2),  // Nord-Est (ajuster horizontalement)
            new Point((int)(radius * Math.sqrt(3) / 2), radius / 2),  // Sud-Est (ajuster horizontalement)
            new Point(0, radius),  // Sud
            new Point(-(int)(radius * Math.sqrt(3) / 2), radius / 2),  // Sud-Ouest (ajuster horizontalement)
            new Point(-(int)(radius * Math.sqrt(3) / 2), -radius / 2)  // Nord-Ouest (ajuster horizontalement)
        };

        // Calculer les positions disponibles autour de la tuile
        for (Point direction : directions) {
            Point newPoint = new Point(tileX + direction.x, tileY + direction.y);
            if (!isTileAtPosition(newPoint)) {
                availablePositions.add(newPoint);  // Ajouter la position si une tuile n'est pas déjà là
            }
        }
    }


    /**
     * Vérifie si une tuile existe déjà à la position donnée.
     *
     * @param position La position à vérifier
     * @return true si une tuile est présente à cette position, false sinon
     */
    private boolean isTileAtPosition(Point position) {
        for (Tile t : tiles) {
            if (t.getXCoord() == position.x && t.getYCoord() == position.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lorsqu'un utilisateur clique, ajoute une nouvelle tuile à la position sélectionnée.
     *
     * @param position La position où ajouter la tuile
     */
    public void placeTileAtPosition(Point position) {
        if (availablePositions.contains(position) && !isTileAtPosition(position)) {
            if (tiles.size() < Options.MAX_TILE_NUMBER - 1) {
                // Placer une nouvelle tuile normalement
                if (nextTile != null) {
                    remainingTilesIndicator.setRemainingTiles(Options.MAX_TILE_NUMBER - 1 - tiles.size());
    
                    // Place la nextTile à la position choisie
                    nextTile.setPosition(position.x, position.y);
                    addTile(nextTile);  // Ajoute la nextTile au tableau des tuiles
                    calculateAvailablePositions(nextTile);  // Calcule de nouvelles positions disponibles
                    repaint();  // Redessine le plateau
                    autoReFocus(nextTile);
    
                    // Initialiser une nouvelle nextTile pour le prochain tour
                    initializeNextTile();
                }
            } else {
                // Pour la dernière tuile
                if (nextTile != null) {
                    // Place la dernière tuile et mettre à jour le score
                    nextTile.setPosition(position.x, position.y);
                    addTile(nextTile);
                    calculateAvailablePositions(nextTile);
                    repaint();
    
                    // Mise à jour de l'indicateur de tuiles restantes
                    remainingTilesIndicator.setRemainingTiles(0);
                }
    
                // Fin de la partie
                try {
                    this.database = new Database();
                } catch (Exception e) {
                    System.err.println("Erreur lors de la connexion à la base de données: " + e.getMessage());
                }
                GameOver gameOverPanel = new GameOver(gameFrame, currentScore, database, Options.mainMenu);
                gameFrame.getContentPane().removeAll();  // Supprime l'ancien contenu
                gameFrame.getContentPane().add(gameOverPanel);  // Ajoute le GameOver
                gameFrame.revalidate();  // Revalidate pour mettre à jour la fenêtre
                gameFrame.repaint();  // Repaint pour afficher les modifications
            }
        }
    }
        
    /**
     * Recentre automatiquement la vue sur une tuile nouvellement placée si l'option
     * de mise au point automatique (AUTO_FOCUS) est activée.
     *
     * @param newlyPlacedTile la tuile récemment placée, utilisée comme référence pour recentrer la vue
     */
    public void autoReFocus(Tile newlyPlacedTile) {
        if (Options.AUTO_FOCUS) {
            // Récupérer les coordonnées de la nouvelle tuile
            int newlyPlacedTileX = newlyPlacedTile.getXCoord();
            int newlyPlacedTileY = newlyPlacedTile.getYCoord();

            // Calculer les décalages nécessaires pour centrer la tuile
            // Nous utilisons la largeur et la hauteur du panneau de jeu (getWidth et getHeight)
            // Divisé par 2 pour centrer la nouvelle tuile dans la fenêtre.
            int targetOffsetX = (int) ((getWidth() - newlyPlacedTile.getRadius() * 2 * zoomFactor) / 2 - newlyPlacedTileX * zoomFactor);
            int targetOffsetY = (int) ((getHeight() - newlyPlacedTile.getRadius() * 2 * zoomFactor) / 2 - newlyPlacedTileY * zoomFactor);


            TilePanningTransition panningTransition = new TilePanningTransition(this, targetOffsetX, targetOffsetY, 15);
            panningTransition.start();
        }
    }

    /**
     * Récupère le facteur de zoom actuel.
     * @return facteur de zoom actuel.
     */
    public double getZoomFactor() { return zoomFactor;}

    /**
     * Définit le facteur de zoom actuel.
     * @param zoomFactor facteur de zoom actuel.
     */
    public void setZoomFactor(double zoomFactor) { this.zoomFactor = zoomFactor; }

    /**
     * Récupère l'offset horizontal actuel.
     * @return offsetX
     */
    public int getOffsetX() { return offsetX; }

    /**
     * Définit l'offset horizontal actuel.
     * @param offsetX La valeur de l'offset horizontal à définir.
     */
    public void setOffsetX(int offsetX) { this.offsetX = offsetX; }

    /**
     * Récupère l'offset vertical actuel.
     * @return offsetY
     */
    public int getOffsetY() { return offsetY; }

    /**
     * Définit l'offset vertical actuel.
     * @param offsetY La valeur de l'offset vertical à définir.
     */
    public void setOffsetY(int offsetY) { this.offsetY = offsetY; }

    /**
     * Permet de zoomer sur le plateau de jeu.
     */
    public void zoomIn() {
        zoomFactor *= 1.1; // Augmenter le facteur de zoom
        repaint();
    }

    /**
     * Permet de dézoomer sur le plateau de jeu.
     */
    public void zoomOut() {
        zoomFactor /= 1.1; // Diminuer le facteur de zoom
        repaint();
    }

    /**
     * Permet de déplacer le plateau de jeu.
     * @param dx Position x
     * @param dy Position y
     */
    public void moveBoard(int dx, int dy) {
        offsetX += dx;
        offsetY += dy;
        repaint();
    }


    /**
     * Afficher les points rouges pour indiquer les positions disponibles.
     *
     * @param g Le contexte graphique
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int scoreX = (getWidth() - 110) / 2;
        scoreDisplay.setPosition(scoreX, 40);

        scoreDisplay.draw(g);


        // Appliquer l'échelle de zoom et le déplacement
        g2d.scale(zoomFactor, zoomFactor);  // Appliquer le zoom
        g2d.translate(offsetX / zoomFactor, offsetY / zoomFactor);  // Appliquer le déplacement (en tenant compte du zoom)

        // Dessiner les points rouges pour les positions disponibles
        for (Point position : availablePositions) {
            g.setColor(Color.RED);
            g.fillOval(position.x - 5, position.y - 5, 10, 10);  // Dessiner un point rouge
        }

        // Dessiner les tuiles existantes
        for (Tile tile : tiles) {
            int tileX = tile.getXCoord();
            int tileY = tile.getYCoord();

            tile.drawTileAt(g,tileX-50,tileY-50,1f);
        }

        // Vérifier si la position de la souris est valide et ne pas dessiner si elle est occupée
        if (mousePosition != null && nextTile != null && !isTileAtPosition(mousePosition)) {
            int nextTileX = mousePosition.x;
            int nextTileY = mousePosition.y;

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));  // Rendre la tuile transparente
            nextTile.drawTileAt(g, nextTileX - 50, nextTileY - 50, 1f);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));  // Rétablir l'opacité
        }

        if (nextTile != null) {
            // Calculer la position correcte de la nextTile (en tenant compte du zoom et des décalages)
            int nextTileX = 0;  // Position x dans l'espace global
            int nextTileY = 0;  // Position y dans l'espace global
            
            // Appliquer la transformation inverse (ne pas zoomer ni déplacer la nextTile)
            g2d.scale(1 / zoomFactor, 1 / zoomFactor);  // Inverser le zoom
            g2d.translate(-offsetX, -offsetY);  // Inverser le décalage (revenir à l'espace global)

            // Dessiner la nextTile à sa position d'origine (0,0)
            nextTile.drawTileAt(g, nextTileX, nextTileY, 1f);

            int indicatorX = getWidth() - 70; // Position x de l'indicateur
            int indicatorY = getHeight() - 670; // Positionner la pile près du bas

            remainingTilesIndicator.draw(g2d, indicatorX, indicatorY);

            // Rétablir les transformations pour les autres éléments (tuiles existantes, etc.)
            g2d.translate(offsetX / zoomFactor, offsetY / zoomFactor);  // Re-appliquer le décalage
            g2d.scale(zoomFactor, zoomFactor);  // Re-appliquer le zoom

        }
    }
}
