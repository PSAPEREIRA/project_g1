package pt.ipp.isep.dei.project1.model.device.tv;

import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class TvType implements DeviceType {

    private static final String TYPE = "Tv";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Tv createNewDevice(String nameOfDevice) {
        return new Tv(nameOfDevice, new TvSpec(), this);
    }

    @Override
    public String getTypeCode() {
        return "Tv";
    }
}
