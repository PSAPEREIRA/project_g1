package pt.ipp.isep.dei.project1.model.device.walltowelheater;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;


import java.util.ArrayList;
import java.util.List;

public class WallTowelHeaterSpec implements DeviceSpecs {

    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseWallTowelHeater = new ArrayList<>();
    private static final String NOMINAL_POWER = "nominal power";
    private static final String HEATER_FLUX = "heater flux";


    public WallTowelHeaterSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public WallTowelHeaterSpec(double mNominalPowerValue, double mHeaterFluxValue) {
        addAttributeNames();
        mValueOfAttributesCaseWallTowelHeater.add(mNominalPowerValue);
        mValueOfAttributesCaseWallTowelHeater.add(mHeaterFluxValue);
    }

    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
        attributeNames.add(HEATER_FLUX);
    }

    public void addAttributeValues() {
        mValueOfAttributesCaseWallTowelHeater.add(0.0);
        mValueOfAttributesCaseWallTowelHeater.add(0.0);
    }
    
    public List<String> getAttributeNamesAndValues() {
        List<String> listNamesAndValues = new ArrayList<>();

        listNamesAndValues.add(attributeNames.get(0)+" - "+ mValueOfAttributesCaseWallTowelHeater.get(0));
        listNamesAndValues.add(attributeNames.get(1)+" - "+ mValueOfAttributesCaseWallTowelHeater.get(1));

        return listNamesAndValues;
    }


    @Override
    public double getAttributeValue(String attributeName) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName))
                return mValueOfAttributesCaseWallTowelHeater.get(i);
        }
        return Double.NaN;
    }

    @Override
    public void setAttributeValue(String attributeName, double value) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName)) {
                mValueOfAttributesCaseWallTowelHeater.set(i, value);
            }
        }
    }

}
