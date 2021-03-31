package pt.ipp.isep.dei.project1.io.ui.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ipp.isep.dei.project1.controllers.device.DeactivateRemoveDeviceFromRoomController;
import pt.ipp.isep.dei.project1.dto.device.DeviceDto;
import pt.ipp.isep.dei.project1.dto.device.ListOfDevicesDto;
import pt.ipp.isep.dei.project1.dto.housedto.ListOfRoomsDto;
import pt.ipp.isep.dei.project1.dto.housedto.RoomDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;

@Component
public class DeactivateRemoveDeviceFromRoomUI {

    @Autowired
    private DeactivateRemoveDeviceFromRoomController deactivateRemoveDeviceFromRoomController;
    private static final String DEVICE = "Device ";


    public void run() {
        ListOfRoomsDto listOfRoomsDto = deactivateRemoveDeviceFromRoomController.getListOfRoomsDto();
        if (Validations.printRoomListAsMenu(listOfRoomsDto)) {
            int optionRoom = Validations.verifyIntegerInputsWithLimits(0, listOfRoomsDto.getRoomDto().size() - 1);
            ListOfDevicesDto listOfDevicesDto = deactivateRemoveDeviceFromRoomController.getListOfDevicesDtoFromRoom(listOfRoomsDto.getRoomDto().get(optionRoom));
            if (Validations.printDeviceListAsMenu(listOfDevicesDto)) {
                int optionDevice = Validations.verifyIntegerInputsWithLimits(0, listOfDevicesDto.getListOfDevices().size() - 1);
                checkIfRemoved(optionRoom, optionDevice);
            }
        }
    }

    public void checkIfRemoved(int optionRoom, int optionDevice) {
        ListOfRoomsDto listOfRoomsDto = deactivateRemoveDeviceFromRoomController.getListOfRoomsDto();
        RoomDto roomDto = listOfRoomsDto.getRoomDto().get(optionDevice);
        ListOfDevicesDto listOfDevicesDto = deactivateRemoveDeviceFromRoomController.getListOfDevicesDtoFromRoom(listOfRoomsDto.getRoomDto().get(optionRoom));
        DeviceDto deviceDto = listOfDevicesDto.getListOfDevices().get(optionDevice);
        if (Validations.checkWantToDelete()) {
            if (deactivateRemoveDeviceFromRoomController.removeDeviceFromRoom(roomDto, deviceDto))
                System.out.println(DEVICE + deviceDto.getName() + " removed from room " + roomDto.getName());
            else System.out.println("Cannot remove the device from this room!");
        }
    }

    public void run222() {
        ListOfRoomsDto listOfRoomsDto = deactivateRemoveDeviceFromRoomController.getListOfRoomsDto();
        if (Validations.printRoomListAsMenu(listOfRoomsDto)) {
            int optionRoom = Validations.verifyIntegerInputsWithLimits(0, listOfRoomsDto.getRoomDto().size() - 1);
            ListOfDevicesDto listOfDevicesDto = deactivateRemoveDeviceFromRoomController.getListOfDevicesDtoFromRoom(listOfRoomsDto.getRoomDto().get(optionRoom));
            if (Validations.printDeviceListAsMenu(listOfDevicesDto)) {
                int optionDevice = Validations.verifyIntegerInputsWithLimits(0, listOfDevicesDto.getListOfDevices().size() - 1);
                checkIfDeativated(listOfRoomsDto.getRoomDto().get(optionRoom), listOfDevicesDto.getListOfDevices().get(optionDevice));

            }
        }
    }

    public void checkIfDeativated(RoomDto optionRoom, DeviceDto optionDevice) {
        if (Validations.checkWantToDeactivate()) {
            if (deactivateRemoveDeviceFromRoomController.deactivateDevice(optionRoom, optionDevice))
                System.out.println(DEVICE + optionDevice.getName() + " deactivate.");
            else System.out.println(DEVICE + optionDevice.getName() + " already deactivated before.");
        }
    }
}


