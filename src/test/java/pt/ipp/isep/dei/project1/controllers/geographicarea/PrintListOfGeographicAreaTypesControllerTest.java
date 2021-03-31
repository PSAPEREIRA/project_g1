package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListOfGeographicAreaTypesDto;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PrintListOfGeographicAreaTypesControllerTest {

    @Mock
    private GeographicAreaTypeRepository geographicAreaTypeRepository;
    @Mock
    private GeographicAreaTypeRepo geographicAreaTypeRepo;

    @BeforeEach
    void initUseCase() {
        geographicAreaTypeRepo = new GeographicAreaTypeRepo(geographicAreaTypeRepository);
    }


    @Test
    void getListOfGeographicAreasTypes() {
        //Arrange

        GeographicAreaType geo1 = new GeographicAreaType("rua");
        GeographicAreaType geo2 = new GeographicAreaType("rua");
        geographicAreaTypeRepo.add(geo1);
        geographicAreaTypeRepo.add(geo2);
        //Act
        PrintListOfGeographicAreaTypesController pl = new PrintListOfGeographicAreaTypesController(geographicAreaTypeRepo);
        ListOfGeographicAreaTypesDto result = pl.getListOfGeographicAreasTypes();
        //Assert
        assertEquals(result.getListOfGATypesDto().get(0).getName(),geo1.getType());
    }
}