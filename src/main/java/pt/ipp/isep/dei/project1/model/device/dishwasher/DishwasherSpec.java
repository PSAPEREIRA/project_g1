package pt.ipp.isep.dei.project1.model.device.dishwasher;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;
import java.util.ArrayList;
import java.util.List;

public class DishwasherSpec implements DeviceSpecs {

    private static final String NOMINAL_POWER = "nominal power";
    private static final String CAPACITY = "capacity";
    @Getter
    private final List<String> attributeNames = new ArrayList<>();
    private final List<Double> mValueOfAttributesCaseDishwasher = new ArrayList<>();


    public DishwasherSpec() {
        addAttributeNames();
        addAttributeValues();
    }

    public DishwasherSpec(double nominalPower,double capacity)
    {
        addAttributeNames();
        mValueOfAttributesCaseDishwasher.add(nominalPower);
        mValueOfAttributesCaseDishwasher.add(capacity);

    }

    public void addAttributeValues() {
        mValueOfAttributesCaseDishwasher.add(0.0);
        mValueOfAttributesCaseDishwasher.add(0.0);
    }

    public void addAttributeNames() {
        attributeNames.add(NOMINAL_POWER);
        attributeNames.add(CAPACITY);
    }


    @Override
    public double getAttributeValue(String attributeName) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName))
                return mValueOfAttributesCaseDishwasher.get(i);
        }
        return Double.NaN;
    }


    @Override
    public void setAttributeValue(String attributeName, double value) {
        for (int i = 0; i < attributeNames.size(); i++) {
            if (attributeNames.get(i).equals(attributeName)) {
                mValueOfAttributesCaseDishwasher.set(i, value);
            }
        }
    }


    public List<String> getAttributeNamesAndValues() {
        List<String> la = new ArrayList<>();

        la.add("Nominal Power - " + mValueOfAttributesCaseDishwasher.get(0));
        la.add("Capacity - " + mValueOfAttributesCaseDishwasher.get(1));

        return la;
    }



}


