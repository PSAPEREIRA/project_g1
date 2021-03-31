package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaTypeDto;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CreateSaveNewGeographicAreaControllerTest {

    @Mock
    private GeographicAreaRepository repo;
    @Mock
    private GeographicAreaTypeRepository repo3;
    private GeographicAreaDomainService areaService;
    private GeographicAreaTypeRepo typeService;
    private CreateSaveNewGeographicAreaController controller;

    @BeforeEach
    void initUseCase() {
        areaService = new GeographicAreaDomainService(repo);
        typeService = new GeographicAreaTypeRepo(repo3);
        controller = new CreateSaveNewGeographicAreaController(areaService,typeService);
    }

    @Test
    void testGetListDto()  {
        //Arrange
        GeographicAreaType geographicAreaType = new GeographicAreaType("city");
        //Act
        typeService.add(geographicAreaType);
        List<GeographicAreaTypeDto> list = controller.getListOfGeographicAreasTypes().getListOfGATypesDto();
        //Assert
        assertEquals(list.get(0).getName(),typeService.getListOfGeographicAreaType().get(0).getType());
    }



}
