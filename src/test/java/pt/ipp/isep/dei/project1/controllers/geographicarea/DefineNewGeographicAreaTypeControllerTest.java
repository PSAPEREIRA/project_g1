package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepository;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaTypeRepo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class DefineNewGeographicAreaTypeControllerTest {

    @Mock
    private GeographicAreaTypeRepository geographicAreaTypeRepository;
    @Mock
    private GeographicAreaTypeRepo geographicAreaTypeRepo;

    @BeforeEach
    void initUseCase() {
        geographicAreaTypeRepo = new GeographicAreaTypeRepo(geographicAreaTypeRepository);
    }

    @Test
    void newGeographicAreaType() {
        DefineNewGeographicAreaTypeController ctrll1 = new DefineNewGeographicAreaTypeController(geographicAreaTypeRepo);
        boolean result = ctrll1.newGeographicAreaType("rua");
        assertTrue(result);
    }

    @Test
    void newGeographicAreaTypeRepeated() {
        GeographicAreaType geographicAreaType = new GeographicAreaType("rua");
        geographicAreaTypeRepo.add(geographicAreaType);
        DefineNewGeographicAreaTypeController ctrll1 = new DefineNewGeographicAreaTypeController(geographicAreaTypeRepo);
        boolean result = ctrll1.newGeographicAreaType("rua");
        assertFalse(result);
    }
}