package fr.cactus_industries.restservice.login;

import fr.cactus_industries.restservice.Response;

public class AccountResponse extends Response {
    
    private final String mail;
    private final String login;
    
    public AccountResponse(String mail, String login) {
        super("OK");
        this.mail = mail;
        this.login = login;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getMail() {
        return mail;
    }
}
