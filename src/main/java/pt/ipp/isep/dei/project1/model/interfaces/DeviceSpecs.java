package pt.ipp.isep.dei.project1.model.interfaces;

import java.util.List;

public interface DeviceSpecs {

    double getAttributeValue(String attributeName);
    void setAttributeValue(String attributeName, double value);
    List<String> getAttributeNames();
    List<String> getAttributeNamesAndValues();



}
