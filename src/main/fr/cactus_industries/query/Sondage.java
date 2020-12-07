package fr.cactus_industries.query;

import fr.cactus_industries.restservice.Database;

import java.sql.SQLException;

public class Sondage {

    private int id;
    private String nom;
    private String description;
    private int authorId;
    private int sondagePrive;
    //private int[] idOfUserCanVote;

    //Constructeur qui créer un sondage localement
    public Sondage(int id, String nom, String description, int authorId, int sondagePrive) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.authorId = authorId;
    }

    //Constructeur qui créer un sondage localement et sur la bdd
    /*public Sondage(String nom, String description, int authorId, boolean sondagePrive) {
        Database db = new Database();
        try {
            int numberOfSondage=db.getNumberOfSurvey();
            if (numberOfSondage>=0) {
                //On recupère le nombre de sondage présent dans la base de données, ça nous permet de connaitre l'id a donné au nouveau sondage.
                this.id = numberOfSondage;
            }
            else {
                System.out.println("Une erreur est survenue dans la récupération du nombre de sondage");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        this.nom = nom;
        this.description = description;
        this.authorId = authorId;
        this.sondagePrive = sondagePrive;
        //db.createSondage(id, nom, description, authorId, sondagePrive);
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int isSondagePrive() {
        return sondagePrive;
    }

    public void setSondagePrive(int sondagePrive) {
        this.sondagePrive = sondagePrive;
    }

}
