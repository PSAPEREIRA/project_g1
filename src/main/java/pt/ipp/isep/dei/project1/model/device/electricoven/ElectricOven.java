package pt.ipp.isep.dei.project1.model.device.electricoven;

import pt.ipp.isep.dei.project1.model.device.GeneralDeviceProgrammable;

public class ElectricOven extends GeneralDeviceProgrammable {


    public ElectricOven(String name, ElectricOvenSpec electricOvenSpec, ElectricOvenType electricOvenType) {
        super(name,electricOvenSpec,electricOvenType);
    }

}
