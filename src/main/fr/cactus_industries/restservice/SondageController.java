package fr.cactus_industries.restservice;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import fr.cactus_industries.query.Sondage;
import fr.cactus_industries.restservice.Survey.Survey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fr.cactus_industries.query.Sondage;

@RestController
public class SondageController {

    @GetMapping("/sondage/create")
    public void create(@RequestParam(value="nom", defaultValue = "") String nom,
                          @RequestParam(value="description", defaultValue = "") String description,
                          @RequestParam(value="authorId", defaultValue = "") int authorId,
                          @RequestParam(value = "prive", defaultValue = "") int sondagePrive) {
        Survey.createSurvey(nom, description, authorId, sondagePrive);
    }

    /*
    @GetMapping("/sondage/delete")
    public void delete(@RequestParam(value="id", defaultValue = "") int id) {
        try {
            db.deleteSondage(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }*/
}
