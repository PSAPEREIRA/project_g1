package pt.ipp.isep.dei.project1.model.device.eletricwaterheater;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class ElectricWaterHeaterType implements DeviceType {

    private static final String TYPE = "Electric Water Heater";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public ElectricWaterHeater createNewDevice(String nameOfDevice) {
        return new ElectricWaterHeater(nameOfDevice,new ElectricWaterHeaterSpec(),this);
    }

    public String getTypeCode(){
        return "EWH";
    }

}
