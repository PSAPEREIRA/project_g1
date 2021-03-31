package pt.ipp.isep.dei.project1.model.house;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
public class PowerSource {

    @Getter @Setter
    private String name;
    double maxPower;


    public PowerSource(String powerSourceName, double maxPower) {
        this.name = powerSourceName;
        this.maxPower = maxPower;
        validateBeforeCreate();
    }

    protected PowerSource() {
    }

    private void validateName() {
        if (name == null || "".equals(name))
            throw new RuntimeException("Insert a valid name for this Power Source");
    }

    private void validateBeforeCreate() {
        validateName();
        validateMaxPower();
    }

    private void validateMaxPower() {
        if (maxPower < 0 || Double.isNaN(maxPower))
            throw new RuntimeException("Insert a valid Max Power for this Power Source");
    }


    @Override
    public int hashCode() {
        return this.name.charAt(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PowerSource))
            return false;
        PowerSource objectOfPowerSource = (PowerSource) obj;
        return (this.name.equals(objectOfPowerSource.name));
    }

}
