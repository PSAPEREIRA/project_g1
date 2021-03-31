package pt.ipp.isep.dei.project1.model.house;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HouseGrid {

    @Id @NotNull @Getter @Setter
    private String code;
    private double powerLimiter;
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @Embedded
    private  final List<PowerSource> listOfPowerSources = new ArrayList<>();
    @Transient @Getter @Setter
    private ListOfReadings listOfReadings;
    @ElementCollection(fetch = FetchType.EAGER)
    @Getter @Setter
    private List<String> roomsList = new ArrayList<>();

    public HouseGrid() {
    }

    public HouseGrid(String code, double powerLimiter) {
        this.code = code;
        this.powerLimiter = powerLimiter;
        validateBeforeCreate();
    }

    public HouseGrid(String code) {
        this.code = code;
        validateCode();
    }

    private void validateBeforeCreate() {
        validateCode();
        validatePowerLimiter();
    }

    private void validateCode() {
        if (code == null || "".equals(code))
            throw new RuntimeException("Insert a valid code for this House Grid");
    }

    private void validatePowerLimiter() {
        if (powerLimiter < 0 || Double.isNaN(powerLimiter))
            throw new RuntimeException("Insert a valid Power Limiter for this House Grid");
    }

    @Override
    public int hashCode() {
        return this.code.charAt(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof HouseGrid))
            return false;
        HouseGrid hg = (HouseGrid) obj;
        return this.code.equals(hg.code);
    }

    public boolean addPowerSource(PowerSource powerSource) {
        if (!listOfPowerSources.contains(powerSource)) {
            listOfPowerSources.add(powerSource);
            return true;
        }
        return false;
    }

}