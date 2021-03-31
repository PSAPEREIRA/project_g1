package pt.ipp.isep.dei.project1.model.device.kettler;

import pt.ipp.isep.dei.project1.model.device.GeneralDevice;


import java.util.List;

public class Kettler extends GeneralDevice {



    public Kettler(String mName, KettleSpec kettlerSpec, KettlerType kettlerType) {
        super(mName,kettlerSpec,kettlerType);
    }


    public double getEstimatedConsumption(List<Double> consumptionInput) {
        double volumeOfWater = consumptionInput.get(0);
        double coldWater = consumptionInput.get(1);
        double performanceRatio = super.getDeviceSpecs().getAttributeValue("performance ratio");
        double specificHeatOfWater = 1.163;
        return specificHeatOfWater * volumeOfWater * (100 - coldWater) * performanceRatio;
    }
}

