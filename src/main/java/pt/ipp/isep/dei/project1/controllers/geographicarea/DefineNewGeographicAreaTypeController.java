package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;

@Controller
public class DefineNewGeographicAreaTypeController {

    private final GeographicAreaTypeRepo geographicAreaTypeRepo;

    @Autowired
    public DefineNewGeographicAreaTypeController(GeographicAreaTypeRepo geographicAreaTypeRepo) {
        this.geographicAreaTypeRepo = geographicAreaTypeRepo;
    }

    public boolean newGeographicAreaType(String type) {
        GeographicAreaType newType = new GeographicAreaType(type);
        return geographicAreaTypeRepo.add(newType);
    }

}