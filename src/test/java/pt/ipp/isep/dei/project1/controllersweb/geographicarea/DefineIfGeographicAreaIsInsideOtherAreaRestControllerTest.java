package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepository;
import pt.ipp.isep.dei.project1.services.GeoAreaSensorTypeService;

@ExtendWith(MockitoExtension.class)
class DefineIfGeographicAreaIsInsideOtherAreaRestControllerTest {

    @Mock
    private GeoAreaSensorTypeService appService;

    @Mock
    private GeographicAreaRepository repo;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaTypeRepository geographicAreaTypeRepository;
    @Mock
    private GeographicAreaRestController geographicAreaRestController;
    @Mock
    private GeographicAreaTypeRepo geographicAreaTypeRepo;
    private DefineIfGeographicAreaIsInsideOtherAreaRestController defineIfGeographicAreaIsInsideOtherAreaRestController;

    @BeforeEach
    void initUseCase() {
        geographicAreaTypeRepo = new GeographicAreaTypeRepo(geographicAreaTypeRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(repo);
        defineIfGeographicAreaIsInsideOtherAreaRestController = new DefineIfGeographicAreaIsInsideOtherAreaRestController(geographicAreaDomainService);
        appService= new GeoAreaSensorTypeService(geographicAreaDomainService,null);
        geographicAreaRestController = new GeographicAreaRestController(appService);

    }

    @Test
    void testGeographicAreaIsInsideOfOtherGeographicArea() {
        GeographicAreaType geographicAreaType1 = new GeographicAreaType("cidade");
        GeographicAreaType geographicAreaType2 = new GeographicAreaType("freguesia");
        geographicAreaTypeRepo.add(geographicAreaType1);
        geographicAreaTypeRepo.add(geographicAreaType2);
        GeographicArea ga1 = new GeographicArea("Porto","city",
                new OccupationArea(new Location(10,20,30),0.5,0.5),
                geographicAreaType1);
        GeographicArea ga2 = new GeographicArea("Cedofeita","city",
                new OccupationArea(new Location(10,20,30),0.5,0.5),
                geographicAreaType2);
        ga1.setGeographicAreaType(geographicAreaType1);
        ga2.setGeographicAreaType(geographicAreaType2);
        geographicAreaDomainService.getListOfGeographicArea().add(ga1);
        geographicAreaDomainService.getListOfGeographicArea().add(ga2);

        String agSonName = "Cedofeita";
        String agDadName = "Porto";

        ResponseEntity responseEntity = defineIfGeographicAreaIsInsideOtherAreaRestController.geographicAreaIsInsideOfOtherGeographicArea(agSonName,agDadName);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testGeographicAreaIsInsideOfOtherGeographicAreaAgSonNull() {
        GeographicAreaType geographicAreaType1 = new GeographicAreaType("cidade");
        GeographicAreaType geographicAreaType2 = new GeographicAreaType("freguesia");
        geographicAreaTypeRepo.add(geographicAreaType1);
        geographicAreaTypeRepo.add(geographicAreaType2);
        GeographicArea ga1 = new GeographicArea("Porto","city",
                new OccupationArea(new Location(10,20,30),0.5,0.5),
                geographicAreaType1);
        GeographicArea ga2 = new GeographicArea("Cedofeita","city",
                new OccupationArea(new Location(10,20,30),0.5,0.5),
                geographicAreaType2);
        ga1.setGeographicAreaType(geographicAreaType1);
        ga2.setGeographicAreaType(geographicAreaType2);
        geographicAreaDomainService.getListOfGeographicArea().add(ga1);
        geographicAreaDomainService.getListOfGeographicArea().add(ga2);

        String agSonName = "Chicago";
        String agDadName = "Porto";

        ResponseEntity responseEntity = defineIfGeographicAreaIsInsideOtherAreaRestController.geographicAreaIsInsideOfOtherGeographicArea(agSonName,agDadName);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGeographicAreaIsInsideOfOtherGeographicAreaAgDadNull() {
        GeographicAreaType geographicAreaType1 = new GeographicAreaType("cidade");
        GeographicAreaType geographicAreaType2 = new GeographicAreaType("freguesia");
        geographicAreaTypeRepo.add(geographicAreaType1);
        geographicAreaTypeRepo.add(geographicAreaType2);
        GeographicArea ga1 = new GeographicArea("Porto","city",
                new OccupationArea(new Location(10,20,30),0.5,0.5),
                geographicAreaType1);
        GeographicArea ga2 = new GeographicArea("Cedofeita","city",
                new OccupationArea(new Location(10,20,30),0.5,0.5),
                geographicAreaType2);
        ga1.setGeographicAreaType(geographicAreaType1);
        ga2.setGeographicAreaType(geographicAreaType2);
        geographicAreaDomainService.getListOfGeographicArea().add(ga1);
        geographicAreaDomainService.getListOfGeographicArea().add(ga2);

        String agSonName = "Cedofeita";
        String agDadName = "Chicago";

        ResponseEntity responseEntity = defineIfGeographicAreaIsInsideOtherAreaRestController.geographicAreaIsInsideOfOtherGeographicArea(agSonName,agDadName);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGeographicAreaIsInsideOfOtherGeographicAreaAgDadAndSunNull() {
        GeographicAreaType geographicAreaType1 = new GeographicAreaType("cidade");
        GeographicAreaType geographicAreaType2 = new GeographicAreaType("freguesia");
        geographicAreaTypeRepo.add(geographicAreaType1);
        geographicAreaTypeRepo.add(geographicAreaType2);
        GeographicArea ga1 = new GeographicArea("Porto","city",
                new OccupationArea(new Location(10,20,30),0.5,0.5),
                geographicAreaType1);
        GeographicArea ga2 = new GeographicArea("Cedofeita","city",
                new OccupationArea(new Location(10,20,30),0.5,0.5),
                geographicAreaType2);
        ga1.setGeographicAreaType(geographicAreaType1);
        ga2.setGeographicAreaType(geographicAreaType2);
        geographicAreaDomainService.getListOfGeographicArea().add(ga1);
        geographicAreaDomainService.getListOfGeographicArea().add(ga2);

        String agSonName = "Chicago";
        String agDadName = "Portugal";

        ResponseEntity responseEntity = defineIfGeographicAreaIsInsideOtherAreaRestController.geographicAreaIsInsideOfOtherGeographicArea(agSonName,agDadName);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGeographicAreaIsInsideOfOtherGeographicAreaAgDadAndCONFLICT() {
        GeographicAreaType geographicAreaType1 = new GeographicAreaType("cidade");
        GeographicAreaType geographicAreaType2 = new GeographicAreaType("freguesia");
        geographicAreaTypeRepo.add(geographicAreaType1);
        geographicAreaTypeRepo.add(geographicAreaType2);
        GeographicArea ga1 = new GeographicArea("Porto","city",
                new OccupationArea(new Location(10,20,30),0.5,0.5),
                geographicAreaType1);
        GeographicArea ga2 = new GeographicArea("Cedofeita","city",
                new OccupationArea(new Location(10,20,30),0.5,0.5),
                geographicAreaType2);
        ga1.setGeographicAreaType(geographicAreaType1);
        ga2.setGeographicAreaType(geographicAreaType2);
        ga1.addGeographicAreaInsideAnother(ga2);
        geographicAreaDomainService.getListOfGeographicArea().add(ga1);
        geographicAreaDomainService.getListOfGeographicArea().add(ga2);

        String agSonName = "Cedofeita";
        String agDadName = "Porto";

        ResponseEntity responseEntity = defineIfGeographicAreaIsInsideOtherAreaRestController.geographicAreaIsInsideOfOtherGeographicArea(agSonName,agDadName);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

}