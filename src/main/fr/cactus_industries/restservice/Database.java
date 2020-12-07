package fr.cactus_industries.restservice;

import fr.cactus_industries.query.Sondage;

import java.sql.*;

public class Database {

    private Connection con;

    public Database() {
        //On donne les variables pour la connexion à la base de données
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection(
                    "jdbc:mysql://cerenity.net/basededonnees" /*ou "jdbc:mysql://cactus-industries.fr/Users" */,
                    "progweb",
                    "Esiea2020");
        }
        //En cas d'erreur
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    public void test() throws SQLException {
        //REQUETE
        String query = "select id, mail, login, pass from USERS";

        //CONNEXION
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //RECUPERATION DES DONNEES
                int id = rs.getInt("id");
                String mail = rs.getString("mail");
                String login = rs.getString("login");
                String pass = rs.getString("pass");

                //AFFICHAGE
                System.out.println("ID : "+id+", mail : "+mail+", login : "+login+", pass : "+pass);
            }
        }
        //En cas d'erreur
        catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public int getNumberOfSurvey() throws SQLException {
        int result = -1;
        //REQUETE
        String query = "SELECT COUNT(*) FROM SONDAGES";

        //CONNEXION
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            //RECUPERATION DES DONNEES (ici : première ligne, contenant le nombre de sondages créés)
            if (rs.next()) {
                result= rs.getInt(1);
            }
        }
        //EN CAS D'ERREUR
        catch (SQLException e) {
            result=-2;
            e.printStackTrace();
        }
        //ON RETOURNE LE NOMBRE DE SONDAGES DANS LA BDD
        return result;
    }

    public Sondage getSondageById(int id) throws SQLException {
        String query = "select nom, description, authorId, sondagePrive from Users where id="+id+";";
        //CONNEXION
        try (Statement stmt = this.con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                //RECUPERATION DES DONNEES
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                int authorId = rs.getInt("authorId");
                int sondagePrive = rs.getInt("sondagePrive");

                //CREATION LOCAL DU SONDAGE
                Sondage sondageSuccess = new Sondage(id, nom, description, authorId, sondagePrive);
                return sondageSuccess;
            }
        }
        //EN CAS D'ERREUR
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        //ON RETOURNE LE NOMBRE DE SONDAGES DANS LA BDD
    }

    public int createSondage(String nom, String description, int authorId, int sondagePrive) throws SQLException {
        int id = this.getNumberOfSurvey();
        //String query = "INSERT INTO SONDAGES (id, nom, description, authorId, sondagePrive) VALUES ('1', 'SondageTest', 'CeciEstUnTest', '2', '0');";

        String query = "INSERT INTO SONDAGES (id, nom, description, authorId, sondagePrive) VALUES ('"+id+"', '"+nom+"', '"+description+"', '"+authorId+"', '"+sondagePrive+"');";

        //CONNEXION
        try (Statement stmt = this.con.createStatement()) {
            stmt.executeUpdate(query);
        }
        //EN CAS D'ERREUR
        catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void deleteSondage(int id) throws SQLException {
        //String query = "INSERT INTO SONDAGES (id, nom, description, authorId, sondagePrive) VALUES ('1', 'SondageTest', 'CeciEstUnTest', '2', '0');";

        String query = "DELETE FROM SONDAGES WHERE '"+id+"';";

        //CONNEXION
        try (Statement stmt = this.con.createStatement()) {
            stmt.executeUpdate(query);
        }
        //EN CAS D'ERREUR
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
