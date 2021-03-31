package pt.ipp.isep.dei.project1.dto.sensordto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;
import java.time.LocalDate;


@Getter
@Setter
public class AreaSensorDto extends ResourceSupport {

    private String name;
    private Location location;
    private SensorType sensorType;
    private LocalDate installationDate;
    private String unit;
    private String idOfSensor;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof AreaSensorDto))
            return false;
        AreaSensorDto c = (AreaSensorDto) obj;
        return this.idOfSensor.equals(c.idOfSensor);
    }

    @Override
    public int hashCode() {
        return this.idOfSensor.charAt(0);
    }



}
