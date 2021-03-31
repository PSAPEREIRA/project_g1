package pt.ipp.isep.dei.project1.model.device.washingmachine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WashingMachineTypeTest {
    @Test
    void getTypeCode() {
        WashingMachineType washType = new WashingMachineType();
        String result = washType.getTypeCode();
        String expectedResult = "WASM";

        assertEquals(expectedResult, result);
    }

    @Test
    void getType() {
        WashingMachineType washType = new WashingMachineType();
        String result = washType.getType();
        String expectedResult = "Washing Machine";

        assertEquals(expectedResult,result);
    }

    @Test
    void createWashingMachine() {
        WashingMachineType washType = new WashingMachineType();
        WashingMachine washingMachine = washType.createNewDevice("newWC");
        assertEquals("newWC",washingMachine.getName());
    }
}