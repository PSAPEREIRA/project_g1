package pt.ipp.isep.dei.project1.model.house;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class PowerSourceTest {


    @Test
    public void createPowerSourceNonValidName() {
        try {
            PowerSource powerSource = new PowerSource("",100);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name for this Power Source", e.getMessage());
        }
    }

    @Test
    public void createPowerSourceNonValidNameNull() {
        try {
            PowerSource powerSource = new PowerSource(null,100);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid name for this Power Source", e.getMessage());
        }
    }

    @Test
    public void createPowerSourceNonValidMaxPower() {
        try {
            PowerSource powerSource = new PowerSource("p1",-5);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Max Power for this Power Source", e.getMessage());
        }
    }

    @Test
    public void createPowerSourceNonValidMaxPowerNull() {
        try {
            PowerSource powerSource = new PowerSource("p1",Double.NaN);
            fail("Fail. An exception must be thrown");
        } catch (RuntimeException e) {
            assertEquals("Insert a valid Max Power for this Power Source", e.getMessage());
        }
    }

    @Test
    void newPowerSourceNameOK() {
        //ARRANGE
        PowerSource PS = new PowerSource("power Source 1",0);
        String result = PS.getName();
        assertEquals("power Source 1", result);
    }


    @Test
    void newPowerSourcePowerOK() {
        //ARRANGE
        PowerSource PS = new PowerSource("power Source 2",50);
        String result = PS.getName();
        assertEquals("power Source 2", result);
    }


    @Test
    public void equalsTrueTest() {
        //ARRANGE
        PowerSource ps = new PowerSource("Electric Grid",50);
        ps.getName();
        Object obj = new PowerSource("Electric Grid",50);
        ((PowerSource) obj).getName();
        //ACT
        boolean result = ps.equals(obj);
        //ASSERT
        assertTrue(result);
    }


    @Test
    public void equalsTrueTestMyself() {
        //ARRANGE
        PowerSource ps = new PowerSource("Electric Grid",50);
        Object obj = ps;
        boolean result = ps.equals(obj);
        //ASSERT
        assertTrue(result);
    }

    @Test
    public void equalsFalseTest() {
        //ARRANGE
        PowerSource ps = new PowerSource("Electric Grid",50);
        Object obj = new PowerSource("Electric Grid2",50);
        ((PowerSource) obj).getName();
        //ACT
        boolean result = ps.equals(obj);
        //ASSERT
        assertFalse(result);
    }

    @Test
    public void equalsFalseTestOtherClass() {
        //ARRANGE
        PowerSource ps = new PowerSource("Electric Grid",50);
        ps.getName();
        Object obj = new AreaSensor();
        //ACT
        boolean result = ps.equals(obj);
        //ASSERT
        assertFalse(result);
    }


    @Test
    public void testHashCode() {
        //ARRANGE
        PowerSource ps = new PowerSource("Electric Grid",50);
        ps.getName();
        //ACT
        int result = ps.getName().charAt(0);
        //ASSERT
        assertEquals(ps.hashCode(), result);
    }

    @Test
    void testConstructor() {
        try {
            PowerSource powerSource = new PowerSource();
        }
        catch (IllegalStateException e){
            String message = "Utility class";
            assertEquals(message, e.getMessage());
        }
    }


}
