package pt.ipp.isep.dei.project1.dto.device;


import pt.ipp.isep.dei.project1.model.interfaces.DeviceSpecs;
import pt.ipp.isep.dei.project1.model.interfaces.DeviceType;

public class DeviceDto {

    private String mName;
    private DeviceType mDeviceType;
    private DeviceSpecs mDeviceSpecs;
    private long mId;

    public DeviceSpecs getDeviceSpecs() {
        return mDeviceSpecs;
    }

    public void setDeviceSpecs(DeviceSpecs deviceSpecs) {
        this.mDeviceSpecs = deviceSpecs;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public DeviceType getDeviceType() {
        return mDeviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.mDeviceType = deviceType;
    }
}
