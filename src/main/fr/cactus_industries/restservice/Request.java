package fr.cactus_industries.restservice;

public class Request {

    private int status; //0 success | 1 error | 2 warning
    private String content; //Message de succès ou d'erreur

    public Request(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public long getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
