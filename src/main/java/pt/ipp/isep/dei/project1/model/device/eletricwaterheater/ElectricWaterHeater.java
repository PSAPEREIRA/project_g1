package pt.ipp.isep.dei.project1.model.device.eletricwaterheater;

import pt.ipp.isep.dei.project1.model.device.GeneralDevice;

import java.util.List;

public class ElectricWaterHeater extends GeneralDevice {



    public ElectricWaterHeater(String name, ElectricWaterHeaterSpec electricWaterHeaterSpec, ElectricWaterHeaterType electricWaterHeaterType) {
        super(name,electricWaterHeaterSpec,electricWaterHeaterType);

    }

    /**
     * GET CONSUMPTION -
     **/

    public double getEstimatedConsumption(List<Double> consumptionInput) {
        double volumeOfWater = consumptionInput.get(0);
        double hotWater = consumptionInput.get(1);
        double coldWater = consumptionInput.get(2);
        double performanceRatio = super.getDeviceSpecs().getAttributeValue("performance ratio");
        double specificHeatOfWater = 1.163;
        return specificHeatOfWater * volumeOfWater * (hotWater - coldWater) * performanceRatio;
    }


}
