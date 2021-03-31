package pt.ipp.isep.dei.project1.model.device.winecooler;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class WineCoolerSpec implements DeviceSpecs {
    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseWineCooler = new ArrayList<>();
    private static final String NOMINAL_POWER = "nominal power";
    private static final String NUMBER_OF_BOTTLES = "number of bottles";
    private static final String ANNUL_ENERGY_CONSUMPTION = "annual energy consumption";


    public WineCoolerSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public WineCoolerSpec(double nominalPowerValue, double numberOfBottles, double annualEnergyConsumption) {
        addAttributeNames();
        mValueOfAttributesCaseWineCooler.add(nominalPowerValue);
        mValueOfAttributesCaseWineCooler.add(numberOfBottles);
        mValueOfAttributesCaseWineCooler.add(annualEnergyConsumption);


    }

    public void addAttributeValues() {
        mValueOfAttributesCaseWineCooler.add(0.0);
        mValueOfAttributesCaseWineCooler.add(0.0);
        mValueOfAttributesCaseWineCooler.add(0.0);

    }


    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
        attributeNames.add(NUMBER_OF_BOTTLES);
        attributeNames.add(ANNUL_ENERGY_CONSUMPTION);
    }

    public List<String> getAttributeNamesAndValues() {
        List<String> listNamesAndValues = new ArrayList<>();

        listNamesAndValues.add(attributeNames.get(0) + " - " + mValueOfAttributesCaseWineCooler.get(0));
        listNamesAndValues.add(attributeNames.get(1) + " - " + mValueOfAttributesCaseWineCooler.get(1));
        listNamesAndValues.add(attributeNames.get(2) + " - " + mValueOfAttributesCaseWineCooler.get(2));

        return listNamesAndValues;
    }

    @Override
    public double getAttributeValue(String attributeName) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName))
                return mValueOfAttributesCaseWineCooler.get(i);
        }
        return Double.NaN;
    }

    @Override
    public void setAttributeValue(String attributeName, double value) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName)) {
                mValueOfAttributesCaseWineCooler.set(i, value);
            }
        }
    }

}

