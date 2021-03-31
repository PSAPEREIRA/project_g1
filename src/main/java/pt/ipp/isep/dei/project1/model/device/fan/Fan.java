package pt.ipp.isep.dei.project1.model.device.fan;

import pt.ipp.isep.dei.project1.model.device.GeneralDeviceProgrammable;

public class Fan extends GeneralDeviceProgrammable {

    public Fan(String mName, FanSpec fanSpec, FanType fanType) {
        super(mName,fanSpec,fanType);
    }


}

