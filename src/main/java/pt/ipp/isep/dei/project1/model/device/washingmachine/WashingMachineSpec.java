package pt.ipp.isep.dei.project1.model.device.washingmachine;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;
import java.util.ArrayList;
import java.util.List;


public class WashingMachineSpec implements DeviceSpecs {

    private static final String NOMINAL_POWER = "nominal power";
    private static final String CAPACITY = "capacity";
    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseWashingMachine = new ArrayList<>();

    public WashingMachineSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public WashingMachineSpec(double nominalPower, double capacity) {
        addAttributeNames();
        mValueOfAttributesCaseWashingMachine.add(nominalPower);
        mValueOfAttributesCaseWashingMachine.add(capacity);
    }

    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
        attributeNames.add(CAPACITY);

    }

    public void addAttributeValues() {
        mValueOfAttributesCaseWashingMachine.add(0.0);
        mValueOfAttributesCaseWashingMachine.add(0.0);
    }
    
    public List<String> getAttributeNamesAndValues() {
        List<String> listNamesAndValues = new ArrayList<>();
        listNamesAndValues.add(attributeNames.get(0) + " - " + mValueOfAttributesCaseWashingMachine.get(0));
        listNamesAndValues.add(attributeNames.get(1) + " - " + mValueOfAttributesCaseWashingMachine.get(1));
        return listNamesAndValues;
    }


    @Override
    public double getAttributeValue(String attributeName) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName))
                return mValueOfAttributesCaseWashingMachine.get(i);
        }
        return Double.NaN;
    }

    @Override
    public void setAttributeValue(String attributeName, double value) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName)) {
                mValueOfAttributesCaseWashingMachine.set(i, value);
            }
        }
    }

}
