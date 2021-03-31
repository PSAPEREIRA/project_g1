package pt.ipp.isep.dei.project1.dto.device;

import pt.ipp.isep.dei.project1.mapper.MapperDevice;
import pt.ipp.isep.dei.project1.model.interfaces.Device;

import java.util.ArrayList;
import java.util.List;

public class ListOfDevicesDto {

    private final List<DeviceDto> mListOfDevices = new ArrayList<>();

    public List<DeviceDto> getListOfDevices() {
        return new ArrayList<>(mListOfDevices);
    }

    public void setListOfDevices(List<Device> listOfDevices) {
        for (Device device:listOfDevices)
            mListOfDevices.add(MapperDevice.toDto(device));
    }
}
