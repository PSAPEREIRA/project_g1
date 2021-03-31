package pt.ipp.isep.dei.project1.model.device.microwaveoven;

import pt.ipp.isep.dei.project1.model.device.GeneralDeviceProgrammable;

public class MicrowaveOven extends GeneralDeviceProgrammable {


    public MicrowaveOven(String mName, MicrowaveOvenSpec microwaveOvenSpec, MicrowaveOvenType microwaveOvenType) {
        super(mName,microwaveOvenSpec,microwaveOvenType);
    }


}
