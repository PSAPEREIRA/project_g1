package pt.ipp.isep.dei.project1.model.sensor;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@Entity
public class SensorType {

    @Id
    @Column(name = "type")
    private String type;


    public SensorType(String type) {
        this.type = type;
    }

    public SensorType() {
    }


    @Override
    public String toString() {
        return "\n AreaSensor Type: " + type;
    }

    /**
     * Hashcode and Equals
     *
     * @return
     */

    @Override
    public int hashCode() {
        return this.type.charAt(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof SensorType))
            return false;
        SensorType c = (SensorType) obj;
        return (this.type.equals(c.getType()));

    }

    public boolean checkIfIsDesignation (SensorType sensorType){
        return  (this.type.equalsIgnoreCase(sensorType.type));
    }
}

