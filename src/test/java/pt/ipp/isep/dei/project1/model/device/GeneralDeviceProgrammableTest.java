package pt.ipp.isep.dei.project1.model.device;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherSpec;
import pt.ipp.isep.dei.project1.model.device.dishwasher.DishwasherType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneralDeviceProgrammableTest {

    @Test
    public void testHashCodeLocation() {

        DishwasherSpec dishwasherSpec = new DishwasherSpec();
        DishwasherType dishwasherType = new DishwasherType();
        GeneralDeviceProgrammable generalDeviceProgrammable = new GeneralDeviceProgrammable("And",
                dishwasherSpec, dishwasherType);
        Object obj = new GeneralDeviceProgrammable("And",
                dishwasherSpec, dishwasherType);

        int result = generalDeviceProgrammable.getName().hashCode();

        assertEquals(((GeneralDeviceProgrammable) obj).getName().hashCode(), result);

    }

    @Test
    public void testEquals_Symmetric() {
        GeneralDeviceProgrammable x = new GeneralDeviceProgrammable("Foo Bar",new DishwasherSpec(),new DishwasherType());  // equals and hashCode check name field value
        GeneralDeviceProgrammable y = new GeneralDeviceProgrammable("Foo Bar",new DishwasherSpec(),new DishwasherType());
        Assert.assertTrue(x.equals(y) && y.equals(x));
        Assert.assertTrue(x.hashCode() == y.hashCode());
    }


    @Test
    public void hashCodeturntest() {
        //ARRANGE
        GeneralDeviceProgrammable s1 = new GeneralDeviceProgrammable("d1",new DishwasherSpec(),new DishwasherType());
        Object obj = new GeneralDeviceProgrammable("d1",new DishwasherSpec(),new DishwasherType());
        //ACT
        int result = s1.getName().charAt(0);
        //ASSERT
        assertEquals(s1.hashCode(), result);
    }


}