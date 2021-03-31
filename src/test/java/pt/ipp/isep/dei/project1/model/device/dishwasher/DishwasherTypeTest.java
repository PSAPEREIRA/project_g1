package pt.ipp.isep.dei.project1.model.device.dishwasher;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;
import pt.ipp.isep.dei.project1.model.device.stove.Stove;
import pt.ipp.isep.dei.project1.model.device.stove.StoveType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DishwasherTypeTest {

    @Test
    void getTypeCode() {
        DishwasherType dwType = new DishwasherType();
        String result = dwType.getTypeCode();
        String expectedResult = "DISW";

        assertEquals(expectedResult,result);
    }

    @Test
    void getType() {
        DishwasherType dwType = new DishwasherType();
        String result = dwType.getType();
        String expectedResult = "Dishwasher";

        assertEquals(expectedResult,result);
    }


    @Test
    void createDishwasher() {
        DishwasherType dwType = new DishwasherType();
        Dishwasher dishwasher = dwType.createNewDevice("Dishwasher");
        assertEquals("Dishwasher",dishwasher.getName());
    }

}