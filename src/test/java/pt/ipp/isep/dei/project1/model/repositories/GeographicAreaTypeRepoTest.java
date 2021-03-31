package pt.ipp.isep.dei.project1.model.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaTypeDto;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class GeographicAreaTypeRepoTest {

    @Mock
    private GeographicAreaTypeRepository geographicAreaTypeRepository;

    @Mock
    private GeographicAreaTypeRepo geographicAreaTypeRepo;

    @BeforeEach
    void initMocks() {
         geographicAreaTypeRepo = new GeographicAreaTypeRepo(geographicAreaTypeRepository);
    }

    @Test
    void getListOfGeographicAreaType() {

        List<GeographicAreaTypeDto> result = geographicAreaTypeRepo.getListOfAreaTypesDTO();

        assertEquals(Collections.emptyList(),result);

    }

    @Test
    void getListOfGeographicAreaTypeTrue() {

        GeographicAreaType geographicAreaType = new GeographicAreaType("rua");

        geographicAreaTypeRepo.add(geographicAreaType);

        List<GeographicAreaTypeDto> result = geographicAreaTypeRepo.getListOfAreaTypesDTO();

        assertEquals("rua",result.get(0).getName());

    }
}