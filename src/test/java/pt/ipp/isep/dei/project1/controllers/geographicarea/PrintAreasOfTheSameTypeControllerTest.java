package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaTypeDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListGeographicAreaDto;
import pt.ipp.isep.dei.project1.dto.geographicareadto.ListOfGeographicAreaTypesDto;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.mapper.MapperGeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.*;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class PrintAreasOfTheSameTypeControllerTest {
    @Mock
    private GeographicAreaRepository repo;

    private GeographicAreaDomainService geographicAreaDomainService;
    @Mock
    private GeographicAreaTypeRepository geographicAreaTypeRepository;
    private GeographicAreaTypeRepo geographicAreaTypeRepo;


    @BeforeEach
    void initUseCase() {
        geographicAreaDomainService = new GeographicAreaDomainService(repo);
        geographicAreaTypeRepo = new GeographicAreaTypeRepo(geographicAreaTypeRepository);
    }

    @Test
    void testTrueInControlVerificationGeographicArea() {
        //ARRANGE
        GeographicAreaType gaType1= new GeographicAreaType("Cidade");
        GeographicAreaType gaType2= new GeographicAreaType("Alameda");
        GeographicAreaType gaType3= new GeographicAreaType("Rua");
        GeographicArea GA1=new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),gaType1);
        GeographicArea GA2=new GeographicArea("Braga","city",new OccupationArea(new Location(10,20,30),0.5,0.5),gaType1);
        geographicAreaDomainService.getListOfGeographicArea().add(GA1);
        geographicAreaDomainService.getListOfGeographicArea().add(GA2);
        geographicAreaTypeRepo.add(gaType1);
        geographicAreaTypeRepo.add(gaType2);
        geographicAreaTypeRepo.add(gaType3);
        GeographicAreaTypeDto geographicAreaTypeDto = MapperGeographicAreaType.toDto(gaType3);
        //ACT
        PrintAreasOfTheSameTypeController ctr31 = new PrintAreasOfTheSameTypeController(geographicAreaDomainService, geographicAreaTypeRepo);
        ListGeographicAreaDto result = ctr31.controlVerificationGeographicArea(geographicAreaTypeDto);
        ListGeographicAreaDto expect = new ListGeographicAreaDto();
        //ASSERT
        assertEquals(expect.getListOfGADto().isEmpty(),result.getListOfGADto().isEmpty());
    }

    @Test
    void testInControlVerificationGeographicAreaWithMultiple1() {
        //ARRANGE
        GeographicAreaType   gaType1= new GeographicAreaType("Cidade");
        GeographicAreaType gaType2= new GeographicAreaType("Alameda");
        GeographicArea GA1=new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),gaType1);
        GeographicArea GA2=new GeographicArea("Lisboa","city",new OccupationArea(new Location(10,20,30),0.5,0.5),gaType2);
        geographicAreaDomainService.getListOfGeographicArea().add(GA1);
        geographicAreaDomainService.getListOfGeographicArea().add(GA2);
        geographicAreaTypeRepo.add(gaType1);
        geographicAreaTypeRepo.add(gaType2);
        GeographicAreaTypeDto geographicAreaTypeDto = MapperGeographicAreaType.toDto(gaType1);
        //ACT
        PrintAreasOfTheSameTypeController ctr31 = new PrintAreasOfTheSameTypeController(geographicAreaDomainService, geographicAreaTypeRepo);
        ListGeographicAreaDto result = ctr31.controlVerificationGeographicArea(geographicAreaTypeDto);
        //ASSERT
        assertEquals(GA1.getName(),result.getListOfGADto().get(0).getName());
    }

    @Test
    void testInControlVerificationGeographicAreaWithMultiple2() {
        //ARRANGE
        GeographicAreaType   gaType1= new GeographicAreaType("Cidade");
        GeographicAreaType   gaType2= new GeographicAreaType("Alameda");
        GeographicArea GA1=new GeographicArea("Porto","city",new OccupationArea(new Location(10,20,30),0.5,0.5),gaType1);
        GeographicArea GA2=new GeographicArea("Lisboa","city",new OccupationArea(new Location(10,20,30),0.5,0.5),gaType2);
        geographicAreaDomainService.add(GA1);
        geographicAreaDomainService.add(GA2);
        geographicAreaTypeRepo.add(gaType1);
        geographicAreaTypeRepo.add(gaType2);
        GeographicAreaTypeDto geographicAreaTypeDto = MapperGeographicAreaType.toDto(gaType2);
        //ACT
        PrintAreasOfTheSameTypeController ctr31 = new PrintAreasOfTheSameTypeController(geographicAreaDomainService, geographicAreaTypeRepo);
        ListGeographicAreaDto result = ctr31.controlVerificationGeographicArea(geographicAreaTypeDto);
        //ASSERT
        assertEquals(GA2.getName(),result.getListOfGADto().get(0).getName());
    }

    @Test
    void getListOfGeographicAreaTypes() {
        GeographicAreaType   gaType1= new GeographicAreaType("Cidade");
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 10, 10), 20, 30), gaType1);
        GeographicArea geographicArea2 = new GeographicArea("lisboa", "city", new OccupationArea(new Location(20, 20, 20), 20, 30), gaType1);
        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add(geographicArea2);
        geographicAreaTypeRepo.add(gaType1);
        PrintAreasOfTheSameTypeController ctr31 = new PrintAreasOfTheSameTypeController(geographicAreaDomainService, geographicAreaTypeRepo);
        ListOfGeographicAreaTypesDto result = ctr31.getListOfGeographicAreaTypes();
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(gaType1.getType());
        assertEquals(expectedResult.get(0), result.getListOfGATypesDto().get(0).getName());
    }

}
