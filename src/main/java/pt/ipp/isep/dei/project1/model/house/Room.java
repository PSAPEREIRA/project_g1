package pt.ipp.isep.dei.project1.model.house;

import lombok.Getter;
import lombok.Setter;
import pt.ipp.isep.dei.project1.dto.sensordto.RoomSensorDto;
import pt.ipp.isep.dei.project1.model.device.ListOfDevices;
import pt.ipp.isep.dei.project1.model.interfaces.Metered;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "room")
public class Room implements Metered {


    @Id
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private double houseFloor;
    @Getter
    @Setter
    private double height;
    @Getter
    @Setter
    private double width;
    @Getter
    @Setter
    private double length;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room", cascade = CascadeType.ALL)
    @Getter
    private List<RoomSensor> listOfSensors = new ArrayList<>();
    private String houseGrid = "";
    @Getter
    @Setter
    @Transient
    private ListOfDevices listOfDevices = new ListOfDevices();


    public Room(String name, String description, double houseFloor, double width, double length, double height) {
        this.name = name;
        this.description = description;
        this.houseFloor = houseFloor;
        this.height = height;
        this.width = width;
        this.length = length;
        this.houseGrid = "";
        validateBeforeCreate();
    }

    protected Room() {
    }

    private void validateBeforeCreate() {
        validateName();
        validateHouseFloor();
        validateDimensions();
    }

    private void validateName() {
        if (name == null || "".equals(name))
            throw new RuntimeException("Insert a valid name of room");
    }

    private void validateHouseFloor() {
        if (houseFloor < 0 || Double.isNaN(houseFloor))
            throw new RuntimeException("Insert a valid house floor");

    }

    private void validateDimensions() {
        if (height <= 0 || Double.isNaN(height) || width <= 0 || Double.isNaN(width) || length <= 0 || Double.isNaN(length))
            throw new RuntimeException("Insert a valid dimension");
    }


    public List<String> getListOfDevicesAsString() {
        return listOfDevices.getNamesOfListOfDevices();
    }


    public String getHouseGrid() {
        return houseGrid;
    }

    public void setHouseGrid(String houseGrid) {
        this.houseGrid = houseGrid;
    }

    /**
     * Equals and HashCode
     *
     * @return
     */

    @Override
    public int hashCode() {
        return this.name.charAt(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Room))
            return false;
        Room c = (Room) obj;
        return this.name.equals(c.name);
    }


    //////////////////////////////////// AreaSensor Methods //////////////////////////////////


    public RoomSensor newSensor(String idOfSensor, String name, SensorType sensorType, LocalDate localDate, String unit) {
        return new RoomSensor(idOfSensor, name, sensorType, localDate, unit);
    }

    public String[] getCurrentTemperature() {
        RoomSensor s1 = checkTemperatureSensorOfRoom();
        String[] temp = checkValidationsOfSensor(s1);
        if (temp[0] != null)
            return temp;
        double currentTemperature = s1.getListOfReadings().getListOfReading().get(s1.getListOfReadings().
                getListOfReading().size() - 1).getValue();
        temp[0] = String.valueOf(currentTemperature);
        temp[1] = s1.getUnit();
        return temp;
    }

    public String[] getMaxRoomTemperature(LocalDate date) {
        RoomSensor s1 = checkTemperatureSensorOfRoom();
        String[] temp = checkValidationsOfSensor(s1);
        if (temp[0] != null)
            return temp;
        else if (!s1.getListOfReadings().checkIfHaveReadingsInCertainDay(date)) {
            temp[0] = "There are no readings on that date";
            return temp;
        }
        double maxTemperature = s1.getListOfReadings().getMaxTempOfDay(date);
        temp[0] = String.valueOf(maxTemperature);
        temp[1] = s1.getUnit();
        return temp;
    }

    public boolean addSensor(RoomSensor s1) {
        if (!listOfSensors.contains(s1)) {
            listOfSensors.add(s1);
            return true;
        }
        return false;
    }


    public RoomSensor checkTemperatureSensorOfRoom() {
        RoomSensor s1 = null;
        for (int sensorIndex = 0; sensorIndex < listOfSensors.size(); sensorIndex++) {
            if ("temperature".equalsIgnoreCase(listOfSensors.get(sensorIndex).getSensorType().getType()))
                s1 = listOfSensors.get(sensorIndex);
        }
        return s1;
    }

    public String[] checkValidationsOfSensor(RoomSensor s1) {
        String[] temp = new String[2];
        if (s1 == null) {
            temp[0] = "There are no temperature sensors in this room";
            return temp;
        } else if (s1.getListOfReadings().getListOfReading().isEmpty()) {
            temp[0] = "There are no readings yet";
            return temp;
        }

        return temp;

    }

    // GET Nominal Power - Metered
    @Override
    public double getNominalPower() {
        return getTotalNominalPowerOfDevicesInRoom();
    }


    public double getTotalNominalPowerOfDevicesInRoom() {
        double totalNominalPower = 0;
        for (int i = 0; i < listOfDevices.getDeviceList().size(); i++) {
            /**  if (listOfDevices.get(i).checkIfDwIsMetered())*/
            totalNominalPower += ((Metered) listOfDevices.getDeviceList().get(i)).getNominalPower();
        }
        return totalNominalPower;
    }

    @Override
    public double getConsumption(LocalDateTime initialDate, LocalDateTime finalDate) throws Exception {
        double consumption = 0;
        for (int deviceIndex = 0; deviceIndex < getListOfDevices().getDeviceList().size(); deviceIndex++)
            consumption += ((Metered) getListOfDevices().getDeviceList().get(deviceIndex)).getConsumption(initialDate, finalDate);
        return consumption;
    }

    @Override
    public ListOfReadings getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        return getListOfReadingsOfAllDevicesInListOfDev().getReadingsOnIntervalCutDuplicates(startDate, endDate);
    }

    public ListOfReadings getListOfReadingsOfAllDevicesInListOfDev() {
        ListOfReadings lReadings = new ListOfReadings();

        for (int i = 0; i < listOfDevices.getDeviceList().size(); i++) {
            lReadings.getListOfReading().addAll(listOfDevices.getDeviceList().get(i).getListOfReadings().getListOfReading());
        }

        return lReadings;
    }


    public ListOfDevices listOfWaterHeatersSelection() {
        ListOfDevices listOfWaterHeaters = new ListOfDevices();
        for (int deviceIndex = 0; deviceIndex < listOfDevices.getDeviceList().size(); deviceIndex++) {
            if ("Electric Water Heater".equalsIgnoreCase(listOfDevices.getDeviceList().get(deviceIndex).getDeviceType().getType()))
                listOfWaterHeaters.addDeviceToList(listOfDevices.getDeviceList().get(deviceIndex));
        }
        return listOfWaterHeaters;
    }


    public boolean checkListOfSensorsEmpty() {
        return listOfSensors.isEmpty();
    }


    public void editProgramFieldOnDevice(int deviceIndex, int programIndex, int fieldIndex, String newValue) {

        /**((Programmable) listOfDevices.getWaterHeatersDevicesTroughListOfRooms().get(deviceIndex).getDeviceSpecs()).editSelectedProgramAndField(programIndex,fieldIndex,newValue);**/

        listOfDevices.getDeviceToEditProgram(deviceIndex, programIndex, fieldIndex, newValue);
    }

    public void goToDevListToDeleteProg(int deviceIndex, int programIndex) {
        listOfDevices.goToDeviceToDeleteProg(deviceIndex, programIndex);
    }

    public List<LocalDateTime> getInstancesWithTemperatureHigherLowerComfort(List<Double> listHouseAreaTemp, int cat, String option, LocalDate startDate) {
        RoomSensor roomSensor = checkTemperatureSensorOfRoom();
        if (roomSensor == null)
            return Collections.emptyList();
        return roomSensor.getInstancesWithTemperatureHigherLowerComfort(listHouseAreaTemp, cat, option, startDate);
    }

    public int createAndAddRoomSensor(RoomSensorDto roomSensorDto) {
        RoomSensor roomSensor;
        try {
            roomSensor = new RoomSensor(roomSensorDto.getIdOfSensor(), roomSensorDto.getName(),
                    roomSensorDto.getSensorType(), roomSensorDto.getInstallationDate(), roomSensorDto.getUnit());
            roomSensor.setRoom(this);
        } catch (Exception e) {
            return -1;
        }
        if (addSensor(roomSensor))
            return 1;
        return -2;
    }
}





