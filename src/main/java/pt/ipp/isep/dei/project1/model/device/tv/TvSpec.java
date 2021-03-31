package pt.ipp.isep.dei.project1.model.device.tv;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class TvSpec implements DeviceSpecs {

    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseTv = new ArrayList<>();
    private static final String NOMINAL_POWER = "nominal power";
    private static final String STANDBY_POWER = "standby power";

    public TvSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public TvSpec(double mNominalPowerValue, double mStandbyPowerValue) {
        addAttributeNames();
        mValueOfAttributesCaseTv.add(mNominalPowerValue);
        mValueOfAttributesCaseTv.add(mStandbyPowerValue);
    }

    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
        attributeNames.add(STANDBY_POWER);
    }

    public void addAttributeValues() {
        mValueOfAttributesCaseTv.add(0.0);
        mValueOfAttributesCaseTv.add(0.0);
    }



    @Override
    public double getAttributeValue(String attributeName) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName))
                return mValueOfAttributesCaseTv.get(i);
        }
        return Double.NaN;
    }

    @Override
    public void setAttributeValue(String attributeName, double value) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName)) {
                mValueOfAttributesCaseTv.set(i, value);
            }
        }
    }

    @Override
    public List<String> getAttributeNamesAndValues() {
        List<String> listNamesAndValues = new ArrayList<>();

        listNamesAndValues.add(attributeNames.get(0) + " - " + mValueOfAttributesCaseTv.get(0));
        listNamesAndValues.add(attributeNames.get(1) + " - " + mValueOfAttributesCaseTv.get(1));

        return listNamesAndValues;
    }
}
