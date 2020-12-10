package fr.cactus_industries.restservice;

public class Response {
    
    protected String status;
    
    public Response(String status){
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
    
}
