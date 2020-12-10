package fr.cactus_industries.query;

import java.util.List;

public class Sondage {

    private int id;
    private String nom;
    private String description;
    private int authorId;
    private int sondagePrive;
    private List<Proposition> propositionList;

    public List<Proposition> getPropositionList() {
        return propositionList;
    }

    public void addProposition(Proposition proposition) {
        this.propositionList.add(proposition);
    }

    public void removeProposition(Proposition proposition) {
        this.propositionList.remove(proposition);
    }

    //Constructeur qui créer un sondage localement
    public Sondage(int id, String nom, String description, int authorId, int sondagePrive) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.authorId = authorId;
        this.sondagePrive = sondagePrive;
    }

    public Sondage(int id, String nom, String description, int authorId) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.authorId = authorId;
    }

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
