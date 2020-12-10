package fr.cactus_industries.query;

public class Vote {
    private int id;
    private int associatedProposition;
    private int associatedUser;

    public Vote(int id, int associatedProposition, int associatedUser) {
        this.id = id;
        this.associatedProposition = associatedProposition;
        this.associatedUser = associatedUser;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", associatedProposition=" + associatedProposition +
                ", associatedUser=" + associatedUser +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssociatedProposition() {
        return associatedProposition;
    }

    public void setAssociatedProposition(int associatedProposition) {
        this.associatedProposition = associatedProposition;
    }

    public int getAssociatedUser() {
        return associatedUser;
    }

    public void setAssociatedUser(int associatedUser) {
        this.associatedUser = associatedUser;
    }
}
