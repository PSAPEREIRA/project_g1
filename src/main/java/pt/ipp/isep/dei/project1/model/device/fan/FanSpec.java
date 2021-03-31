package pt.ipp.isep.dei.project1.model.device.fan;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class FanSpec implements DeviceSpecs {

    private static final String NOMINAL_POWER = "nominal power";
    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseFan = new ArrayList<>();


    public FanSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public FanSpec (double nominalPower) {
        addAttributeNames();
        mValueOfAttributesCaseFan.add(nominalPower);
    }

    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
    }

    public void addAttributeValues() {
        mValueOfAttributesCaseFan.add(0.0);
    }




    /**
     * Method to get the value for String
     **/

    @Override
    public double getAttributeValue(String attributeName) {
        int index;
        for (index = 0; index < attributeNames.size(); index++) {
            if (attributeNames.get(index).equals(attributeName))
                return mValueOfAttributesCaseFan.get(index);
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
                mValueOfAttributesCaseFan.set(index, value);
        }
    }


    /**
     * Method to get the String of the program and the value of program
     **/

    @Override
    public List<String> getAttributeNamesAndValues() {
        List<String> listOfNamesAndValues = new ArrayList<>();
        listOfNamesAndValues.add(attributeNames.get(0) + " - " + mValueOfAttributesCaseFan.get(0));
        return listOfNamesAndValues;
    }

}

