package pt.ipp.isep.dei.project1.model.device.lamp;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;
import java.util.ArrayList;
import java.util.List;

public class LampSpec implements DeviceSpecs {

    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseLamp = new ArrayList<>();
    private static final String NOMINAL_POWER = "nominal power";
    private static final String LUMINOUS_FLUX = "luminous flux";

    public LampSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public LampSpec(double mNominalPowerValue, double mLuminousFluxValue) {
        addAttributeNames();
        mValueOfAttributesCaseLamp.add(mNominalPowerValue);
        mValueOfAttributesCaseLamp.add(mLuminousFluxValue);
    }

    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
        attributeNames.add(LUMINOUS_FLUX);
    }

    public void addAttributeValues() {
        mValueOfAttributesCaseLamp.add(0.0);
        mValueOfAttributesCaseLamp.add(0.0);
    }

    public List<String> getAttributeNamesAndValues() {
        List<String> listNamesAndValues = new ArrayList<>();

        listNamesAndValues.add(attributeNames.get(0)+" - "+ mValueOfAttributesCaseLamp.get(0));
        listNamesAndValues.add(attributeNames.get(1)+" - "+ mValueOfAttributesCaseLamp.get(1));

        return listNamesAndValues;
    }


    @Override
    public double getAttributeValue(String attributeName) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName))
                return mValueOfAttributesCaseLamp.get(i);
        }
        return Double.NaN;
    }

    @Override
    public void setAttributeValue(String attributeName, double value) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName)) {
                mValueOfAttributesCaseLamp.set(i, value);
            }
        }
    }

}
