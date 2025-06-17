package fr.monkhanny.dorfromantik.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;

/**
 * Classe pour gérer la connexion à la base de données et effectuer des opérations de lecture et d'écriture
 * 
 * Cette classe permet de se connecter à une base de données MariaDB et d'effectuer des opérations de lecture et d'écriture.
 * 
 * @version 1.0
 * @author Moncef STITI
 * @see PlayerScore
 * @see SQLException
 * @see Connection
 * @see DriverManager
 * @see Statement
 * @see ResultSet
 * @see PreparedStatement
 */
public class Database {
    /**
     * URL de connexion à la base de données
     */
    private static final String URL = "jdbc:mariadb://dwarves.iut-fbleau.fr/stiti";

    /**
     * Identifiants de connexion à la base de données
     */
    private static final String LOGIN = "stiti";

    /**
     * Mot de passe de connexion à la base de données
     */
    private static final String PASSWORD = "";

    /**
     * Connexion à la base de données
     */
    private Connection database;

    /**
     * Constructeur pour initialiser la connexion à la base de données
     * @throws SQLException Si une erreur se produit lors de la connexion à la base de données
     */
    public Database() throws SQLException {
        try {
            // Chargement du driver MariaDB
            Class.forName("org.mariadb.jdbc.Driver");

            try {
                // Connexion à la base de données
                this.database = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            }catch (SQLException e) {
            // Gestion de l'erreur de connexion
            throw new SQLException("Échec de la connexion à la base de données: " + e.getMessage(), e);
            }
        } catch (ClassNotFoundException e) {
            // Si le driver n'est pas trouvé
            throw new SQLException("Driver MariaDB introuvable dans le classpath", e);
        } 
    }

    /**
     * Récupère la connexion à la base de données
     * @return La connexion à la base de données
     */
    public Connection getDatabase() {
        return this.database;
    }


    /**
     * Récupère le seed correspondant au mode de jeu (series_id)
     * @param seriesId L'ID de la série (mode de jeu)
     * @return Le seed associé à ce mode de jeu
     * @throws SQLException Si une erreur se produit lors de la récupération du seed
     */
    public long getSeedBySeriesId(long seriesId) throws SQLException {
        String query = "SELECT series_id FROM Series WHERE series_id = " + seriesId;
        long seed = -1; // Valeur par défaut si le seed n'est pas trouvé

        try (Statement stmt = this.database.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                seed = rs.getLong("series_id");
            }
        }
        return seed;
    }

    /**
     * Récupère le nom de la série correspondant au mode de jeu (series_id)
     * @param seriesId L'ID de la série (mode de jeu)
     * @return Le nom de la série associée à ce mode de jeu
     * @throws SQLException Si une erreur se produit lors de la récupération du nom de la série
     */
    public List<PlayerScore> getAllScores(long seriesId) throws SQLException {
        List<PlayerScore> allScores = new ArrayList<>();
        
        // Requête pour récupérer les scores et le nom de la série
        String query = "SELECT s.score, se.name " +
                       "FROM Scores s " +
                       "JOIN Series se ON s.series_id = se.series_id " +
                       "WHERE s.series_id = ? " +
                       "ORDER BY s.score DESC";
        
        try (PreparedStatement stmt = this.database.prepareStatement(query)) {
            stmt.setLong(1, seriesId);  // Paramètre pour filtrer par series_id
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int score = rs.getInt("score");
                    String seriesName = rs.getString("name");  // Nom de la série
                    // Ajouter l'objet PlayerScore à la liste avec un nom générique et le score
                    allScores.add(new PlayerScore(seriesName, score));  // Ajout du nom de la série
                }
            }
        }
        
        return allScores;
    }
        
    /**
     * Récupère toutes les séries de la base de données
     * @return Les séries stockées dans la base de données
     */
    public List<String> getAllSeries() {
        List<String> series = new ArrayList<>();
        try {
            String query = "SELECT name FROM series ORDER BY date_created DESC"; // Trier par date
            Statement statement = this.database.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
    
            while (resultSet.next()) {
                series.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return series;
    }
    
    /**
     * Récupère les séries dans une plage de dates
     * @param startDate Date de début
     * @param endDate Date de fin
     * @return La liste des séries créées dans la plage de dates spécifiée
     */
    public List<String> getSeriesByDateRange(Date startDate, Date endDate) {
        List<String> series = new ArrayList<>();
        try {
            String query = "SELECT name FROM Series WHERE creation_date BETWEEN ? AND ? ORDER BY creation_date DESC";
            PreparedStatement statement = this.database.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                series.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return series;
    }

    /**
     * Compte le nombre de séries créées dans une plage de dates
     * @param startDate Date de début
     * @param endDate Date de fin
     * @param onlyDeveloperCreated Indique si seules les séries créées par les développeurs doivent être comptées
     * @return Le nombre de séries créées dans la plage de dates spécifiée
     */
    public int countSeriesByDateRange(Date startDate, Date endDate, boolean onlyDeveloperCreated) {
        int count = 0;
        try {
            // Construire la requête SQL en fonction du filtre "created_by_developer"
            String query = "SELECT COUNT(*) as series_count FROM Series WHERE creation_date BETWEEN ? AND ? " +
                           (onlyDeveloperCreated ? "AND created_by_developer = TRUE " : "");
            
            // Préparer l'instruction
            PreparedStatement statement = this.database.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
    
            // Exécuter la requête et récupérer le résultat
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("series_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    

    /**
     * Récupère les séries dans une plage de dates, paginées
     * @param startDate Date de début
     * @param endDate Date de fin
     * @param page Numéro de page
     * @param itemsPerPage Nombre d'éléments par page
     * @param onlyDeveloperCreated Indique si seules les séries créées par les développeurs doivent être récupérées
     * @return La liste des séries créées dans la plage de dates spécifiée, paginée
     */
    public List<String> getSeriesByDateRangePaginated(Date startDate, Date endDate, 
                                                  int page, int itemsPerPage, 
                                                  boolean onlyDeveloperCreated) {
        List<String> series = new ArrayList<>();
        try {
            String query = "SELECT name FROM Series " +
                        "WHERE creation_date BETWEEN ? AND ? " +
                        (onlyDeveloperCreated ? "AND created_by_developer = TRUE " : "") +
                        "ORDER BY " +
                        "  CASE " +
                        "    WHEN name LIKE 'Série custom%' THEN CAST(REGEXP_SUBSTR(name, '[0-9]+') AS UNSIGNED) " + 
                        "    ELSE NULL " +
                        "  END ASC, " + 
                        "  created_by_developer DESC, " +
                        "  name ASC " +
                        "LIMIT ? OFFSET ?";
            
            PreparedStatement statement = this.database.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            statement.setInt(3, itemsPerPage);
            statement.setInt(4, (page - 1) * itemsPerPage);
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                series.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return series;
    }

    /**
     * Récupère la seed correspondant au nom de la série
     * @param name Le nom de la série
     * @return La seed associée au nom de la série
     * @throws SQLException Si une erreur se produit lors de la récupération de la seed
     */
    public long getSeedByName(String name) throws SQLException {
        String query = "SELECT series_id FROM Series WHERE name = " + "\'" + name + "\'" +";";
        long seed = -1; // Valeur par défaut si le seed n'est pas trouvé

        try (Statement stmt = this.database.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                seed = rs.getLong("series_id");
            }
        }
        return seed;
    }

    /**
     * Ajoute un score à une série
     * @param seriesId L'ID de la série
     * @param score Le score à ajouter
     * @throws SQLException Si une erreur se produit lors de l'ajout du score
     */
    public void addScore(long seriesId, int score) throws SQLException {
        String insertQuery = "INSERT INTO Scores (series_id, score) VALUES (?, ?)";
        try (PreparedStatement stmt = this.database.prepareStatement(insertQuery)) {
            stmt.setLong(1, seriesId);
            stmt.setInt(2, score);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du score: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Ajoute une seed personnalisée à la base de données
     * @param customSeed La seed personnalisée à ajouter
     * @throws SQLException Si une erreur se produit lors de l'ajout de la seed
     */
    public void addCustomSeed(long customSeed) throws SQLException {
        // Vérifier si la seed existe déjà
        String checkQuery = "SELECT COUNT(*) FROM Series WHERE series_id = ?";
        try (PreparedStatement checkStmt = this.database.prepareStatement(checkQuery)) {
            checkStmt.setLong(1, customSeed);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // La seed existe déjà
                return; // Ne pas insérer si la seed existe déjà
            }
        }
    
        // Trouver le plus grand numéro existant dans les noms de série
        String lastSeriesQuery = "SELECT name FROM Series";
        int nextId = 1;  // Valeur par défaut si aucune série n'existe
        try (PreparedStatement lastSeriesStmt = this.database.prepareStatement(lastSeriesQuery)) {
            ResultSet rs = lastSeriesStmt.executeQuery();
            while (rs.next()) {
                String seriesName = rs.getString("name");
                // Extraire le numéro à la fin du nom de la série
                String[] parts = seriesName.split(" ");
                try {
                    int number = Integer.parseInt(parts[parts.length - 1]);
                    if (number >= nextId) {
                        nextId = number + 1;  // Incrémenter pour la nouvelle série
                    }
                } catch (NumberFormatException e) {
                    // Ignorer les séries dont le nom ne suit pas le format attendu
                    continue;
                }
            }
        }
    
        // Générer le nom dynamique "Série Custom X"
        String seriesName = "Série custom " + nextId;
    
        // Insérer la nouvelle série dans la base de données
        String insertQuery = "INSERT INTO Series (name, series_id, creation_date) VALUES (?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement stmt = this.database.prepareStatement(insertQuery)) {
            stmt.setString(1, seriesName);
            stmt.setLong(2, customSeed);  // Utiliser customSeed comme series_id
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la seed custom: " + e.getMessage());
            throw e;
        }
    }
    
    

    /**
     * Récupère les meilleurs scores des joueurs (limite de 10 scores)
     * @return une liste de résultats sous forme de tableau contenant le score et la date formatée
     * @throws SQLException Si une erreur se produit lors de la récupération des scores
     */
    public List<PlayerScore> getTopPlayers() throws SQLException {
        List<PlayerScore> topPlayers = new ArrayList<>();
        
        // Requête pour récupérer les scores et le nom de la série, triée par score décroissant
        String query = "SELECT s.score, se.name FROM Scores s " +
                       "JOIN Series se ON s.series_id = se.series_id " +
                       "ORDER BY s.score DESC LIMIT 10";
        
        try (Statement stmt = this.database.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                int score = rs.getInt("score");
                String seriesName = rs.getString("name");  // Nom de la série
                
                // Ajouter l'objet PlayerScore à la liste
                topPlayers.add(new PlayerScore(seriesName, score));  // Assurez-vous que PlayerScore accepte un nom de série
            }
        }
        
        return topPlayers;
    }
    
    /**
     * Récupère les scores d'une série spécifique, triés en ordre décroissant (du plus élevé au plus bas)
     * @param seriesId L'ID de la série
     * @return Liste des scores pour la série donnée
     * @throws SQLException En cas d'erreur lors de la récupération des scores
     */
    public List<Integer> getScoresBySeriesId(long seriesId) throws SQLException {
        List<Integer> scores = new ArrayList<>();

        String query = "SELECT score FROM Scores WHERE series_id = ? ORDER BY score DESC";
        try (PreparedStatement stmt = this.database.prepareStatement(query)) {
            stmt.setLong(1, seriesId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    scores.add(rs.getInt("score"));
                }
            }
        }

        // If you want the scores to be in descending order (from highest to lowest)
        Collections.sort(scores, Collections.reverseOrder());
        
        return scores;
    }

    /**
     * Ferme la connexion à la base de données
     */
    public void close() {
        try {
            if (this.database != null && !this.database.isClosed()) {
                this.database.close();
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la base de données : " + e.getMessage());
        }
    }
}