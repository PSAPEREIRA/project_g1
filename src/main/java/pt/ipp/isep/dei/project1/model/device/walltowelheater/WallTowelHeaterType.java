package pt.ipp.isep.dei.project1.model.device.walltowelheater;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class WallTowelHeaterType implements DeviceType {

    private static final String TYPE = "WallTowelHeater";

    @Override
    public String getType() {
        return TYPE;
    }


    @Override
    public WallTowelHeater createNewDevice(String nameOfDevice) {
        return new WallTowelHeater(nameOfDevice,new WallTowelHeaterSpec(),this);
    }

    public String getTypeCode(){
        return "WATH";
    }

}
