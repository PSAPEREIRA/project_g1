package pt.ipp.isep.dei.project1.model.device;

import lombok.Getter;
import pt.ipp.isep.dei.project1.model.interfaces.Device;
import pt.ipp.isep.dei.project1.model.interfaces.Programmable;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import java.util.ArrayList;
import java.util.List;

public class ListOfDevices {

    @Getter
    private final List<Device> deviceList = new ArrayList<>();

    /**
     * Method to add a Device to a List
     **/


    public boolean addDeviceToList(Device device) {
        if (deviceList.isEmpty()) {
            deviceList.add(device);
            return true;
        } else {
            for (int index = 0; index < deviceList.size(); index++) {
                if (!deviceList.get(index).equals(device)) {
                    deviceList.add(device);
                    return true;
                }
            }
            return false;
        }
    }

    public Device gettingDeviceFromList(int deviceIndexInput) {
        if (deviceList.isEmpty())
            return null;
        return deviceList.get(deviceIndexInput);
    }



    public List<String> getNamesOfDevicesInListMenu() {

        /**this method is to wait input from user to pick from**/

        List<String> lisDevNam = new ArrayList<>();

        for (int i = 0; i < deviceList.size(); i++) {
            lisDevNam.add((i + 1) + " - " + deviceList.get(i).getName());
        }
        lisDevNam.add("0 - Exit");
        return lisDevNam;
    }



    public List<String> getNamesOfListOfDevicesForEach() {

        List<String> lisDevNam = new ArrayList<>();
        for (Device i : deviceList) {
            lisDevNam.add(i.getName());
        }

        return lisDevNam;
    }

    public ListOfReadings getListOfReadingsOfAllDevicesInListOfDev() {
        ListOfReadings lReadings = new ListOfReadings();

        for (int i = 0; i < deviceList.size(); i++) {
            lReadings.getListOfReading().addAll(deviceList.get(i).getListOfReadings().getListOfReading());
        }

        return lReadings;
    }



    /**
     * Method to get a Current temperature
     *
     * @return
     */


    public Device getDeviceByName (String deviceName){
        for(Device device: deviceList)
            if(device.getName().equals(deviceName))
                return device;
        return null;
    }


    public void getDeviceToEditProgram(int deviceIndex, int programIndex, int fieldIndex, String newValue) {
        /**((Programmable)deviceList.get(deviceIndex).getDeviceSpecs()).editSelectedProgramAndField(programIndex,fieldIndex,newValue);
         if (deviceList.get(deviceIndex).checkIfIsProgrammable())**/
        ((Programmable) deviceList.get(deviceIndex)).editSelectedProgramAndField(programIndex, fieldIndex, newValue);
    }

    public void goToDeviceToDeleteProg(int deviceIndex, int programIndex) {
        /**((Programmable) deviceList.get(deviceIndex).getDeviceSpecs()).deleteProgram(programIndex);
         if (deviceList.get(deviceIndex).checkIfIsProgrammable())**/
        ((Programmable) deviceList.get(deviceIndex)).deleteProgram(programIndex);
    }


    public int getSizeOfDeviceList() {
        return deviceList.size();
    }


    public List<String> getNamesOfListOfDevices() {
        /**this method is to wait input from user to pick from**/
        List<String> lisDevNam = new ArrayList<>();
        for (int i = 0; i < deviceList.size(); i++)
            lisDevNam.add(deviceList.get(i).getName());
        return lisDevNam;
    }



    public boolean removeDevice(Device deviceToRemove) {
        if (deviceList.contains(deviceToRemove)) {
            deviceList.remove(deviceToRemove);
            return true;
        } else
            return false;
    }



}




