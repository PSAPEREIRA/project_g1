package pt.ipp.isep.dei.project1.model.device.freezer;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class FreezerSpec implements DeviceSpecs {

    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseFreezer = new ArrayList<>();
    private static final String NOMINAL_POWER = "nominal power";
    private static final String FREEZER_CAPACITY = "freezer capacity";
    private static final String ANNUAL_ENERGY_CONSUMPTION = "annual energy consumption";


    public FreezerSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public FreezerSpec(double mNominalPowerValue, double mFreezerCapacityValue, double mAnnualEnergyConsumptionValue) {
        addAttributeNames();
        mValueOfAttributesCaseFreezer.add(mNominalPowerValue);
        mValueOfAttributesCaseFreezer.add(mFreezerCapacityValue);
        mValueOfAttributesCaseFreezer.add(mAnnualEnergyConsumptionValue);
    }


    public void addAttributeValues() {
        mValueOfAttributesCaseFreezer.add(0.0);
        mValueOfAttributesCaseFreezer.add(0.0);
        mValueOfAttributesCaseFreezer.add(0.0);

    }

    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
        attributeNames.add(FREEZER_CAPACITY);
        attributeNames.add(ANNUAL_ENERGY_CONSUMPTION);
    }


    public List<String> getAttributeNamesAndValues() {
        List<String> listNamesAndValues = new ArrayList<>();

        listNamesAndValues.add(attributeNames.get(0)+" - "+ mValueOfAttributesCaseFreezer.get(0));
        listNamesAndValues.add(attributeNames.get(1)+" - "+ mValueOfAttributesCaseFreezer.get(1));
        listNamesAndValues.add(attributeNames.get(2)+" - "+ mValueOfAttributesCaseFreezer.get(2));

        return listNamesAndValues;
    }


    @Override
    public double getAttributeValue(String attributeName) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName))
                return mValueOfAttributesCaseFreezer.get(i);
        }
        return Double.NaN;
    }

    @Override
    public void setAttributeValue(String attributeName, double value) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName)) {
                mValueOfAttributesCaseFreezer.set(i, value);
            }
        }
    }
}
