package pt.ipp.isep.dei.project1.model.device.kettler;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class KettleSpec implements DeviceSpecs {

    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseKettler = new ArrayList<>();
    private static final String NOMINAL_POWER = "nominal power";
    private static final String VOLUME_OF_WATER = "volume of water";
    private static final String PERFORMANCE_RATIO = "performance ratio";

    public KettleSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public KettleSpec(double nominalPowerValue, double volumeOfWaterValue, double performanceRatio) {
        addAttributeNames();
        mValueOfAttributesCaseKettler.add(nominalPowerValue);
        mValueOfAttributesCaseKettler.add(volumeOfWaterValue);
        mValueOfAttributesCaseKettler.add(performanceRatio);

    }

    public void addAttributeValues() {
        mValueOfAttributesCaseKettler.add(0.0);
        mValueOfAttributesCaseKettler.add(0.0);
        mValueOfAttributesCaseKettler.add(0.0);
    }


    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
        attributeNames.add(VOLUME_OF_WATER);
        attributeNames.add(PERFORMANCE_RATIO);
    }


    public List<String> getAttributeNamesAndValues() {
        List<String> listNamesAndValues = new ArrayList<>();

        listNamesAndValues.add(attributeNames.get(0) + " - " + mValueOfAttributesCaseKettler.get(0));
        listNamesAndValues.add(attributeNames.get(1) + " - " + mValueOfAttributesCaseKettler.get(1));
        listNamesAndValues.add(attributeNames.get(2) + " - " + mValueOfAttributesCaseKettler.get(2));

        return listNamesAndValues;
    }

    @Override
    public double getAttributeValue(String attributeName) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName))
                return mValueOfAttributesCaseKettler.get(i);
        }
        return Double.NaN;
    }

    @Override
    public void setAttributeValue(String attributeName, double value) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName)) {
                mValueOfAttributesCaseKettler.set(i, value);
            }
        }
    }

}

