package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.device.DeviceDto;
import pt.ipp.isep.dei.project1.model.interfaces.Device;

public final class MapperDevice {

    protected MapperDevice() {
        throw new IllegalStateException("Utility class");
    }

    public static DeviceDto toDto(Device device) {
        DeviceDto deviceDTO = new DeviceDto();
        deviceDTO.setName(device.getName());
        deviceDTO.setDeviceType(device.getDeviceType());
        deviceDTO.setDeviceSpecs(device.getDeviceSpecs());
        return deviceDTO;
    }
}
