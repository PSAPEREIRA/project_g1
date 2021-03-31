package pt.ipp.isep.dei.project1.model.device.stove;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;
import java.util.ArrayList;
import java.util.List;

public class StoveSpec implements DeviceSpecs {

    private static final String NOMINAL_POWER = "nominal power";
    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseStove = new ArrayList<>();


    public StoveSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public StoveSpec (double nominalPower) {
        addAttributeNames();
        mValueOfAttributesCaseStove.add(nominalPower);
    }

    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
    }

    public void addAttributeValues() {
        mValueOfAttributesCaseStove.add(0.0);
    }


    /**
     * Method to get the value for String
     **/

    @Override
    public double getAttributeValue(String attributeName) {
        int index;
        for (index = 0; index < attributeNames.size(); index++) {
            if (attributeNames.get(index).equals(attributeName))
                return mValueOfAttributesCaseStove.get(index);
        }
        return Double.NaN;
    }


    /**
     * Method to alter the value for String
     **/

    @Override
    public void setAttributeValue(String attributeName, double value) {
        int index;
        for (index = 0; index < attributeNames.size(); index++) {
            if (attributeNames.get(index).equals(attributeName))
                mValueOfAttributesCaseStove.set(index, value);
        }
    }

    /**
     * Method to get the String of the program and the value of program
     **/

    @Override
    public List<String> getAttributeNamesAndValues() {
        List<String> listOfNamesAndValues = new ArrayList<>();
        listOfNamesAndValues.add(attributeNames.get(0) + " - " + mValueOfAttributesCaseStove.get(0));
        return listOfNamesAndValues;
    }







}
