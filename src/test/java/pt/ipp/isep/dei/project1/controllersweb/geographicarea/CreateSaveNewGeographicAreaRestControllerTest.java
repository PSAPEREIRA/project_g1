package pt.ipp.isep.dei.project1.controllersweb.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CreateSaveNewGeographicAreaRestControllerTest {


    @Mock
    private GeographicAreaRepository repo;
    @Mock
    private GeographicAreaTypeRepository repo3;
    private GeographicAreaDomainService geographicAreaDomainService;
    private GeographicAreaTypeRepo geographicAreaTypeRepo;
    private CreateSaveNewGeographicAreaRestController controller;

    @BeforeEach
    void initUseCase() {
        geographicAreaDomainService = new GeographicAreaDomainService(repo);
        geographicAreaTypeRepo = new GeographicAreaTypeRepo(repo3);
        controller = new CreateSaveNewGeographicAreaRestController(geographicAreaDomainService, geographicAreaTypeRepo);
    }

    @Test
    void testGetListDto() {
        //Arrange
        GeographicAreaType geographicAreaType = new GeographicAreaType("city");
        //Act
        geographicAreaTypeRepo.add(geographicAreaType);
        ResponseEntity<Object> result = controller.getListOfGeographicAreasTypes();
        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    void createNewGeographicArea() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea
                (new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        //Act
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        ResponseEntity<Object> result = controller.createNewGeographicArea(geographicAreaDto);
        //Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void createNewGeographicAreaRepeated() {
        //Arrange
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea
                (new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        //Act
        GeographicAreaDto geographicAreaDto = MapperGeographicArea.toDto(geographicArea);
        controller.createNewGeographicArea(geographicAreaDto);
        ResponseEntity<Object> result = controller.createNewGeographicArea(geographicAreaDto);
        //Assert
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    void testGetListOfGeoTypes() {
        //Arrange
        GeographicAreaType geographicAreaType = new GeographicAreaType("city");
        GeographicAreaType geographicAreaType2 = new GeographicAreaType("street");
        //Act
        geographicAreaTypeRepo.add(geographicAreaType);
        geographicAreaTypeRepo.add(geographicAreaType2);
        ResponseEntity<Object> result = controller.getListOfGeographicAreasTypes();
        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    void testGetListOfGeoTypesEmpty() {
        ResponseEntity<Object> result = controller.getListOfGeographicAreasTypes();
        //Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
    }

}