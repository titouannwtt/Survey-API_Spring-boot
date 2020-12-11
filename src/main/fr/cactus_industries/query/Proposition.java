package fr.cactus_industries.query;

import java.util.List;

public class Proposition {
    private int id;
    private int associatedId;
    private String lieu;
    private String date;
    private List<Vote> votelist;

    //Constructeur qui créer un sondage localement
    public Proposition(int id, int associatedId, String lieu, String date, List<Vote> votelist) {
        this.id = id;
        this.associatedId = associatedId;
        this.lieu = lieu;
        this.date = date;
        this.votelist = votelist;
    }

    public Proposition(int id, int associatedId, String lieu, String date) {
        this.id = id;
        this.associatedId = associatedId;
        this.lieu = lieu;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Proposition{" +
                "id=" + id +
                ", associatedId=" + associatedId +
                ", lieu='" + lieu + '\'' +
                ", date='" + date + '\'' +
                ", votelist=" + votelist +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(int associatedId) {
        this.associatedId = associatedId;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Vote> getVotelist() {
        return votelist;
    }

    public void addVote(Vote vote) {
        this.votelist.add(vote);
    }

    public void removeVote(Vote vote) {
        this.votelist.remove(vote);
    }

}
