package pt.ipp.isep.dei.project1.model.sensor;

import lombok.Getter;
import lombok.Setter;
import pt.ipp.isep.dei.project1.model.house.Room;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "room_sensor")
public class RoomSensor {


    @Id
    @NotNull
    private String idOfRoomSensor;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private SensorType sensorType;
    private LocalDate installationDate;
    @Embedded
    private ListOfReadings listOfReadings = new ListOfReadings();
    private String unit;
    @Transient
    private final ListOfStatus listOfStatus = new ListOfStatus();
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    public static final String LOWER = "lower";
    public static final String HIGHER = "higher";


    /**
     * Builder's
     */

    public RoomSensor(String idOfRoomSensor, String name, SensorType designation, LocalDate installationDate, String unit) {
        this.idOfRoomSensor = idOfRoomSensor;
        this.name = name;
        this.sensorType = designation;
        this.installationDate = installationDate;
        this.unit = unit;
        validateBeforeCreate();
    }



    public RoomSensor() {
    }

    private void validateBeforeCreate() {
        validateName();
    }


    private void validateName() {
        if (idOfRoomSensor == null || "".equals(idOfRoomSensor))
            throw new RuntimeException("Insert a valid name!");
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof RoomSensor))
            return false;
        RoomSensor sensor1 = (RoomSensor) obj;
        return this.idOfRoomSensor.equals(sensor1.idOfRoomSensor) || this.sensorType.equals(sensor1.sensorType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sensorType);
    }

    public void addReading(Reading reading) {
        listOfReadings.addReading(reading);
    }

    public List<LocalDateTime> getInstancesWithTemperatureHigherLowerComfort(List<Double> listHouseAreaTemp, int cat, String option, LocalDate startDate) {
        List<Double> temperaturesToCompare = temperaturesByCat(listHouseAreaTemp, cat, option);
        if (temperaturesToCompare.equals(Collections.emptyList()))
            return Collections.emptyList();
        ArrayList<LocalDateTime> instanceResultList = new ArrayList<>();
        LocalDate start = startDate;
        if (option.equalsIgnoreCase(HIGHER)) {
            for (int i = 0; i < listHouseAreaTemp.size(); i++) {
                instanceResultList.addAll(listOfReadings.getReadingsOnDayHigherComfort(start, temperaturesToCompare.get(i)));
                start = startDate.plusDays((long) i + 1);
            }
        } else {
            for (int i = 0; i < listHouseAreaTemp.size(); i++) {
                instanceResultList.addAll(listOfReadings.getReadingsOnDayLowerComfort(start, temperaturesToCompare.get(i)));
                start = startDate.plusDays((long) i + 1);
            }
        }
        return instanceResultList;
    }


    private List<Double> temperaturesByCat(List<Double> listHouseAreaTemp, int cat, String option) {
        switch (cat) {
            case 1:
                return temperaturesCatOne(listHouseAreaTemp, option);
            case 2:
                return temperaturesCatTwo(listHouseAreaTemp, option);
            case 3:
                return temperaturesCatThree(listHouseAreaTemp, option);
            default:
                return Collections.emptyList();
        }
    }

    private List<Double> temperaturesCatOne(List<Double> listHouseAreaTemp, String option) {
        List<Double> temperaturesByCatOne = new ArrayList<>();
        if (option.equalsIgnoreCase(HIGHER)) {
            for (double temp : listHouseAreaTemp)
                temperaturesByCatOne.add(0.33 * temp + 20.8);
        } else if (option.equalsIgnoreCase(LOWER)) {
            for (double temp : listHouseAreaTemp)
                temperaturesByCatOne.add(0.33 * temp + 16.8);
        }
        return temperaturesByCatOne;
    }

    private List<Double> temperaturesCatTwo(List<Double> listHouseAreaTemp, String option) {
        List<Double> temperaturesByCatTwo = new ArrayList<>();
        if (option.equalsIgnoreCase(HIGHER)) {
            for (double temp : listHouseAreaTemp)
                temperaturesByCatTwo.add(0.33 * temp + 21.8);
        } else if (option.equalsIgnoreCase(LOWER)) {
            for (double temp : listHouseAreaTemp)
                temperaturesByCatTwo.add(0.33 * temp + 15.8);
        }
        return temperaturesByCatTwo;
    }

    private List<Double> temperaturesCatThree(List<Double> listHouseAreaTemp, String option) {
        List<Double> temperaturesByCatThree = new ArrayList<>();
        if (option.equalsIgnoreCase(HIGHER)) {
            for (double temp : listHouseAreaTemp)
                temperaturesByCatThree.add(0.33 * temp + 22.8);
        } else if (option.equalsIgnoreCase(LOWER)) {
            for (double temp : listHouseAreaTemp)
                temperaturesByCatThree.add(0.33 * temp + 14.8);
        }
        return temperaturesByCatThree;
    }

    public void addStatus(Status status) {
        listOfStatus.getStatusList().add(status);
    }

}