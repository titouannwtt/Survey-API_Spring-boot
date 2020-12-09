package fr.cactus_industries.query;

public class Proposition {
    private int id;
    private int associatedId;
    private String lieu;
    private String date;

    //Constructeur qui créer un sondage localement
    public Proposition(int id, int associatedId, String lieu, String date) {
        this.id = id;
        this.associatedId = associatedId;
        this.lieu = lieu;
        this.date = date;
    }
}
