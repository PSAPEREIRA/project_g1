package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.*;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DefineIfGeographicAreaIsInsideOtherAreaControllerTest {

    @Mock
    private GeographicAreaRepository repo;
    @Mock
    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaTypeRepository geographicAreaTypeRepository;
    @Mock
    private GeographicAreaTypeRepo geographicAreaTypeRepo;

    @BeforeEach
    void initUseCase() {
        geographicAreaTypeRepo = new GeographicAreaTypeRepo(geographicAreaTypeRepository);
        geographicAreaDomainService = new GeographicAreaDomainService(repo);

    }

    @Test
    void testIncludeSunGeoInDadGeoTrue() {
        //ARRANGE
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
        GeographicAreaDto geographicAreaDto= MapperGeographicArea.toDto(ga1);
        GeographicAreaDto geographicAreaDto2= MapperGeographicArea.toDto(ga2);
        DefineIfGeographicAreaIsInsideOtherAreaController ctrl07 = new DefineIfGeographicAreaIsInsideOtherAreaController(geographicAreaDomainService);

        //ACT
        boolean result = ctrl07.geographicAreaIsInsideOfOtherGeographicArea(geographicAreaDto2,geographicAreaDto);

        //ASSERT
        assertTrue(result);
    }

    @Test
    void testIncludeSunGeoInDadGeoFalse() {

        //ARRANGE
        GeographicAreaType geographicAreaType1 = new GeographicAreaType("cidade");
        GeographicAreaType geographicAreaType2 = new GeographicAreaType("freguesia");
        geographicAreaTypeRepo.add(geographicAreaType1);
        geographicAreaTypeRepo.add(geographicAreaType2);
        GeographicArea ga1 = new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),geographicAreaType1);
        GeographicArea ga2 = new GeographicArea("Cedofeita","city",new OccupationArea(new Location(10,20,30),0.5,0.5),geographicAreaType2);
        ga1.setGeographicAreaType(geographicAreaType1);
        ga2.setGeographicAreaType(geographicAreaType2);
        geographicAreaDomainService.getListOfGeographicArea().add(ga1);
        geographicAreaDomainService.getListOfGeographicArea().add(ga2);
        GeographicAreaDto geographicAreaDto= MapperGeographicArea.toDto(ga1);
        GeographicAreaDto geographicAreaDto2= MapperGeographicArea.toDto(ga2);
        DefineIfGeographicAreaIsInsideOtherAreaController ctrl07 = new DefineIfGeographicAreaIsInsideOtherAreaController(geographicAreaDomainService);
        ga2.addGeographicAreaInsideAnother(ga1);

        //ACT
        boolean result = ctrl07.geographicAreaIsInsideOfOtherGeographicArea(geographicAreaDto2,geographicAreaDto);

        //ASSERT
        assertFalse(result);
    }

    @Test
    void getListOfGeographicAreas() {
        //ARRANGE
        GeographicAreaType geographicAreaType1 = new GeographicAreaType("cidade");
        GeographicAreaType geographicAreaType2 = new GeographicAreaType("freguesia");
        geographicAreaTypeRepo.add(geographicAreaType1);
        geographicAreaTypeRepo.add(geographicAreaType2);
        GeographicArea ga1 = new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),geographicAreaType1);
        GeographicArea ga2 = new GeographicArea("Cedofeita","city",new OccupationArea(new Location(10,20,30),0.5,0.5),geographicAreaType2);
        ga1.setGeographicAreaType(geographicAreaType1);
        ga2.setGeographicAreaType(geographicAreaType2);
        geographicAreaDomainService.getListOfGeographicArea().add(ga1);
        geographicAreaDomainService.getListOfGeographicArea().add(ga2);
        DefineIfGeographicAreaIsInsideOtherAreaController ctrl07 = new DefineIfGeographicAreaIsInsideOtherAreaController(geographicAreaDomainService);
        ListGeographicAreaDto result = ctrl07.getListOfGeographicAreas();
        assertEquals(ga2.getName(),result.getListOfGADto().get(1).getName());
    }


}