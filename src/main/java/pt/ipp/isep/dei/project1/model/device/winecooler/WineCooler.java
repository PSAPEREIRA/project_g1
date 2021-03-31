package pt.ipp.isep.dei.project1.model.device.winecooler;

import pt.ipp.isep.dei.project1.model.device.GeneralDevice;


public class WineCooler extends GeneralDevice {
    
    public WineCooler(String mName, WineCoolerSpec wineCoolerSpec, WineCoolerType wineCoolerType) {
        super(mName,wineCoolerSpec,wineCoolerType);

    }

    public double getEstimatedConsumption() {
        return super.getDeviceSpecs().getAttributeValue("annual energy consumption") / 365;
    }


}
