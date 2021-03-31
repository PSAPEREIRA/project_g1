package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.geographicarea.*;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class PrintInfoIfOneGeographicAreaIsInOtherControllerTest {


    @Mock
    private GeographicAreaRepository repo;

    private GeographicAreaDomainService areaService;

    @BeforeEach
    void initUseCase() {
        areaService = new GeographicAreaDomainService(repo);
    }


    @Test
    void getListOfGeographicAreas() {
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 10, 10), 20, 30), new GeographicAreaType("city"));
        GeographicArea geographicArea2 = new GeographicArea("lisboa", "city", new OccupationArea(new Location(20, 20, 20), 20, 30), new GeographicAreaType("city"));
        areaService.add(geographicArea);
        areaService.add(geographicArea2);
        PrintInfoIfOneGeographicAreaIsInOtherController printInfoIfOneGeographicAreaIsInOtherController = new PrintInfoIfOneGeographicAreaIsInOtherController(areaService);
        ListGeographicAreaDto listGeographicAreaDto= printInfoIfOneGeographicAreaIsInOtherController.getListOfGeographicAreas();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(geographicArea.getName());
        expectedResult.add(geographicArea2.getName());
        assertEquals(expectedResult.get(1), listGeographicAreaDto.getListOfGADto().get(1).getName());
    }


}