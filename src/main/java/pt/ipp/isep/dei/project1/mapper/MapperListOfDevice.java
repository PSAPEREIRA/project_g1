package pt.ipp.isep.dei.project1.mapper;

import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.model.interfaces.Device;

import java.util.List;

public final class MapperListOfDevice {

    protected MapperListOfDevice()  {
        throw new IllegalStateException("Utility class");
    }

    public static ListOfDevicesDto toDto(List<Device> deviceList) {
        ListOfDevicesDto listOfDevicesDto = new ListOfDevicesDto();
        listOfDevicesDto.setListOfDevices(deviceList);
        return listOfDevicesDto;
    }
}
