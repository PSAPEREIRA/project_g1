package pt.ipp.isep.dei.project1.model.device.washingmachine;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class WashingMachineType implements DeviceType {

    private static final String TYPE = "Washing Machine";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public WashingMachine createNewDevice(String nameOfDevice) {
        return new WashingMachine(nameOfDevice,new WashingMachineSpec(),this);
    }

    public String getTypeCode(){
        return "WASM";
    }

}
