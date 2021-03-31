package pt.ipp.isep.dei.project1.model.device.fridge;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;
import java.util.ArrayList;
import java.util.List;

public class FridgeSpec implements DeviceSpecs {

    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseFridge = new ArrayList<>();
    private static final String NOMINAL_POWER = "nominal power";
    private static final String FREEZER_CAPACITY = "freezer capacity";
    private static final String REFRIGERATOR_CAPACITY = "refrigerator capacity";
    private static final String ANNUAL_ENERGY_CONSUMPTION = "annual energy consumption";


    public FridgeSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public FridgeSpec(double mNominalPowerValue, double mFreezerCapacityValue, double mRefrigeratorCapacity, double mAnnualEnergyConsumptionValue) {
        addAttributeNames();
        mValueOfAttributesCaseFridge.add(mNominalPowerValue);
        mValueOfAttributesCaseFridge.add(mFreezerCapacityValue);
        mValueOfAttributesCaseFridge.add(mRefrigeratorCapacity);
        mValueOfAttributesCaseFridge.add(mAnnualEnergyConsumptionValue);
    }


    public void addAttributeValues() {
        mValueOfAttributesCaseFridge.add(0.0);
        mValueOfAttributesCaseFridge.add(0.0);
        mValueOfAttributesCaseFridge.add(0.0);
        mValueOfAttributesCaseFridge.add(0.0);

    }

    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
        attributeNames.add(FREEZER_CAPACITY);
        attributeNames.add(REFRIGERATOR_CAPACITY);
        attributeNames.add(ANNUAL_ENERGY_CONSUMPTION);
    }

    public List<String> getAttributeNamesAndValues() {
        List<String> listNamesAndValues = new ArrayList<>();

        listNamesAndValues.add(attributeNames.get(0)+" - "+ mValueOfAttributesCaseFridge.get(0));
        listNamesAndValues.add(attributeNames.get(1)+" - "+ mValueOfAttributesCaseFridge.get(1));
        listNamesAndValues.add(attributeNames.get(2)+" - "+ mValueOfAttributesCaseFridge.get(2));
        listNamesAndValues.add(attributeNames.get(3)+" - "+ mValueOfAttributesCaseFridge.get(3));

        return listNamesAndValues;
    }


    @Override
    public double getAttributeValue(String attributeName) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName))
                return mValueOfAttributesCaseFridge.get(i);
        }
        return Double.NaN;
    }

    @Override
    public void setAttributeValue(String attributeName, double value) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName)) {
                mValueOfAttributesCaseFridge.set(i, value);
            }
        }
    }
}
