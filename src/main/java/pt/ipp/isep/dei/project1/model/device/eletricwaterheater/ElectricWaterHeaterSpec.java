package pt.ipp.isep.dei.project1.model.device.eletricwaterheater;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class ElectricWaterHeaterSpec implements DeviceSpecs {

    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseElectricWaterHeater = new ArrayList<>();
    private static final String NOMINAL_POWER = "nominal power";
    private static final String VOLUME_OF_WATER = "volume of water";
    private static final String HOT_WATER_TEMPERATURE = "hot water temperature";
    private static final String PERFORMANCE_RATIO = "performance ratio";

    public ElectricWaterHeaterSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public ElectricWaterHeaterSpec(double nominalPowerValue, double volumeOfWaterValue, double maxValueForHotWater, double performanceRatio) {
        addAttributeNames();
        mValueOfAttributesCaseElectricWaterHeater.add(nominalPowerValue);
        mValueOfAttributesCaseElectricWaterHeater.add(volumeOfWaterValue);
        mValueOfAttributesCaseElectricWaterHeater.add(maxValueForHotWater);
        mValueOfAttributesCaseElectricWaterHeater.add(performanceRatio);

    }

    public void addAttributeValues() {
        mValueOfAttributesCaseElectricWaterHeater.add(0.0);
        mValueOfAttributesCaseElectricWaterHeater.add(0.0);
        mValueOfAttributesCaseElectricWaterHeater.add(0.0);
        mValueOfAttributesCaseElectricWaterHeater.add(0.0);
    }


    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
        attributeNames.add(VOLUME_OF_WATER);
        attributeNames.add(HOT_WATER_TEMPERATURE);
        attributeNames.add(PERFORMANCE_RATIO);

    }

    public List<String> getAttributeNamesAndValues() {
        List<String> listNamesAndValues = new ArrayList<>();

        listNamesAndValues.add(attributeNames.get(0)+" - "+ mValueOfAttributesCaseElectricWaterHeater.get(0));
        listNamesAndValues.add(attributeNames.get(1)+" - "+ mValueOfAttributesCaseElectricWaterHeater.get(1));
        listNamesAndValues.add(attributeNames.get(2)+" - "+ mValueOfAttributesCaseElectricWaterHeater.get(2));
        listNamesAndValues.add(attributeNames.get(3)+" - "+ mValueOfAttributesCaseElectricWaterHeater.get(3));

        return listNamesAndValues;
    }

    @Override
    public double getAttributeValue(String attributeName) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName))
                return mValueOfAttributesCaseElectricWaterHeater.get(i);
        }
        return Double.NaN;
    }

    @Override
    public void setAttributeValue(String attributeName, double value) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName)) {
                mValueOfAttributesCaseElectricWaterHeater.set(i, value);
            }
        }
    }

}
