package pt.ipp.isep.dei.project1.model.device.eletricwaterheater;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.eletricwaterheater.ElectricWaterHeaterType;

import static org.junit.jupiter.api.Assertions.*;

class ElectricWaterHeaterTypeTest {

    @Test
    void getTypeCode() {
        ElectricWaterHeaterType ehwType = new ElectricWaterHeaterType();
        String result = ehwType.getTypeCode();
        String expectedResult = "EWH";

        assertEquals(expectedResult,result);
    }
}