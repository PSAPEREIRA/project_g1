package pt.ipp.isep.dei.project1.dto.sensordto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.ipp.isep.dei.project1.model.sensor.ListOfStatus;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RoomSensorDto {

    private String idOfSensor;
    private String name;
    private SensorType sensorType;
    private LocalDate installationDate;
    private String unit;
    private ListOfStatus listOfStatus;


}
