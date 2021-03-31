package pt.ipp.isep.dei.project1.model.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.controllers.geographicarea.CreateSaveNewGeographicAreaController;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaTypeDto;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListOfGeographicAreaTest {

    @Mock
    private GeographicAreaRepository repo;
    @Mock
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
    public void testAddNewGeoAreaFalse() {
        //ARRANGE
        GeographicArea g1 = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        geographicAreaDomainService.add(g1);
        //ACT
        boolean expectedResult = false;
        boolean result = geographicAreaDomainService.add(g1);
        //System.out.println(geographicAreaService.getListOfGeographicAreasAsString());
        //ASSERT
        assertEquals(expectedResult, result);
    }


    @Test
    public void testCreateNewGeographicArea() {
        //ARRANGE
        //ACT
        GeographicArea expectedResult = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        GeographicArea result = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateNewGeographicAreaNonValid() {
        //ARRANGE
        try {
            //ACT
            GeographicArea result = new GeographicArea("", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
            fail("Not an exception");
        } catch (RuntimeException e) {
        }
    }


    @Test
    public void testGetGAByName() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        //ACT
        geographicArea.setName("porto");
        geographicAreaDomainService.add(geographicArea);
        GeographicArea result = geographicAreaDomainService.getGeographicAreaByName("porto");
        GeographicArea expectedResult = geographicArea;
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetGAByIDNonValid() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        //ACT
        geographicArea.setName("Lisboa");
        geographicAreaDomainService.add(geographicArea);
        GeographicArea result = geographicAreaDomainService.getGeographicAreaByName("braga");
        GeographicArea expectedResult = null;
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void checkIfAddCheckName() {
        //ARRANGE

        GeographicArea geographicArea =  new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        GeographicArea geographicArea2 = new GeographicArea("porto", "urban area", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("urban"));
        GeographicArea geographicArea3 = new GeographicArea("porto", "district", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("area"));

        //ACT
        geographicArea.setName("porto");
        geographicAreaDomainService.add(geographicArea);

        geographicAreaDomainService.add( geographicArea);
        geographicAreaDomainService.add(geographicArea2);
        geographicAreaDomainService.add(geographicArea3);


        int result = geographicAreaDomainService.getListOfGeographicArea().size();
        int expectedResult = 1;

        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    public void testToString() {
        //ARRANGE
        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        //ACT
        geographicArea.setName("porto");
        geographicAreaDomainService.add(geographicArea);
        String result = geographicArea.toString();
        //ASSERT
        assertEquals(  geographicArea.toString() , result);
    }

    @Test
    public void checkIfAddCheckNameTrue() {
        //ARRANGE

        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        GeographicArea geographicArea2 = new GeographicArea("porto", "urban area", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("urban"));
        GeographicArea geographicArea3 = new GeographicArea("porto", "district", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("area"));

        //ACT
        geographicArea.setName("porto");
        boolean result = geographicAreaDomainService.add( geographicArea);

        geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add( geographicArea2);
        geographicAreaDomainService.add( geographicArea3);


        //ASSERT
        assertTrue(result);
    }

    @Test
    public void checkIfAddCheckNameFalse() {
        //ARRANGE

        GeographicArea geographicArea = new GeographicArea("porto", "city", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("city"));
        GeographicArea geographicArea2 = new GeographicArea("porto", "urban area", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("urban"));
        GeographicArea geographicArea3 = new GeographicArea("porto", "district", new OccupationArea(new Location(10, 20, 0), 10, 10), new GeographicAreaType("area"));

        //ACT
        geographicArea.setName("porto");
        geographicAreaDomainService.add(geographicArea);

        boolean result = geographicAreaDomainService.add(geographicArea);
        geographicAreaDomainService.add(geographicArea2);
        geographicAreaDomainService.add(geographicArea3);

        //ASSERT
        assertFalse(result);
    }



    @Test
    public void createNewGeographicAreaRepeated() {

        //ARRANGE
        new GeographicArea("Porto", "city", new OccupationArea
                (new Location(10, 20, 30), 0.5, 0.5), new GeographicAreaType("city"));
        //ACT
        CreateSaveNewGeographicAreaController ctr31 = new CreateSaveNewGeographicAreaController(geographicAreaDomainService, geographicAreaTypeRepo);
        CreateSaveNewGeographicAreaController ctr32 = new CreateSaveNewGeographicAreaController(geographicAreaDomainService, geographicAreaTypeRepo);
        double [] coordinates = new double[3];
        coordinates[0]=10;
        coordinates[1]=20;
        coordinates[2]=30;
        ctr32.createNewGeographicArea("Porto", "city", coordinates, 0.5, 0.5, new GeographicAreaTypeDto());
        boolean result = ctr31.createNewGeographicArea("Porto", "city",coordinates, 0.5, 0.5, new GeographicAreaTypeDto());
        //ASSERT
        assertFalse (result);
    }


    @Test
    public void getListOfGeographicAreasTypes() {

        GeographicAreaType gAT1 = new GeographicAreaType("Street");
        GeographicAreaType gAT2 = new GeographicAreaType("City");
        geographicAreaTypeRepo.add(gAT1);
        geographicAreaTypeRepo.add(gAT2);
        geographicAreaTypeRepo.add(gAT1);
        geographicAreaTypeRepo.add(gAT2);
        assertEquals(gAT1.getType(), geographicAreaTypeRepo.getListOfGeographicAreaType().get(0).getType());
    }

    @Test
    public void createNewGeographicArea() {
        GeographicAreaTypeDto gAT1 = new GeographicAreaTypeDto();
        double [] coordinates = new double[3];
        coordinates[0]=80;
        coordinates[1]=90;
        coordinates[2]=0;
        CreateSaveNewGeographicAreaController ctr31 = new CreateSaveNewGeographicAreaController(geographicAreaDomainService, geographicAreaTypeRepo);
        boolean result = ctr31.createNewGeographicArea("Porto", "City", coordinates,40,40,gAT1);
        assertTrue(result);
    }


    @Test
    public void GetListOfGeographicAreaTypes() {
        GeographicAreaType ga1 = new GeographicAreaType("city");
        geographicAreaTypeRepo.add(ga1);
        assertEquals("city", geographicAreaTypeRepo.getListOfGeographicAreaType().get(0).getType());
    }



}