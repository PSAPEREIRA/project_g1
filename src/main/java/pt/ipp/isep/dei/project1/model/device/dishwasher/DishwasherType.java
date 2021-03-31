package pt.ipp.isep.dei.project1.model.device.dishwasher;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class DishwasherType implements DeviceType {

    private static final String TYPE = "Dishwasher";


    @Override
    public String getType() {
        return TYPE;
    }

    
    @Override
    public Dishwasher createNewDevice(String nameOfDevice) {
        return new Dishwasher(nameOfDevice,new DishwasherSpec(),this);
    }

    public String getTypeCode(){
        return "DISW";
    }

}
