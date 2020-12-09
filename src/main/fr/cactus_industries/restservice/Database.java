package fr.cactus_industries.restservice;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.sql.*;
import java.util.Map;

public class Database {

    private static Connection con = null;
    
    // Renvoie la connection actuelle si elle est valide/existance, sinon établie une nouvelle connexion
    public static Connection getDBConnection(){
        try {
            if(con != null && con.isValid(3)){ // Check if there is already a connection and if it is still valid
                return con;
            } else{ // Make or remake the connection
                EstablishConnection();
                return con;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        // Erreur: On tente la création d'une nouvelle connection
        if(EstablishConnection()){
            return con;
        }
        return null;
    }
    
    // Etablie une connexion à la BDD en fonction des paramètres rentrés dans le fichier de configuration
    private static boolean EstablishConnection(){
        File f = new File("./db_infos.yml");
        if(!f.exists()){
            try{
                InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("db_infos.yml");
                OutputStream out = new BufferedOutputStream(new FileOutputStream(f));
                byte[] buff = new byte[1024];
                int length;
                while((length = in.read(buff))>0){
                    out.write(buff, 0, length);
                }
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        Map<String, Object> db_info = null;
        try{ // On récupère la map des données du Yaml
            db_info = new Yaml().load(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        
        // On créé la connexion à la BDD avec les infos contenue dans le Yaml
        try {
            con = DriverManager.getConnection(
                    "jdbc:"+ getConfigString(db_info, "db.type")+"://"+getConfigString(db_info,"db.url") /*ou "jdbc:postgre://cactus-industries.fr/ProgWeb" */,
                    getConfigString(db_info, "db.user"),
                    getConfigString(db_info, "db.pass"));
        }
        catch (SQLException e) { //En cas d'erreur
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    public static String getConfigString(Map<String, Object> config, String path){
        String[] list = path.split("\\.");
        Object o = null;
        Map<String, Object> map = config;
        for (String  s:list){
            o = map.get(s);
            if(o == null)
                return "";
            else
            if(o instanceof Map)
                map = (Map<String, Object>) o;
        }
        
        return (String) o;
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
}
