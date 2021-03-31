package pt.ipp.isep.dei.project1.model.sensor;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class Status {

    private boolean sensorStatus;
    private LocalDate data;

    public Status() {
    }

    public Status(boolean sensorStatus, LocalDate data) {
        this.sensorStatus = sensorStatus;
        this.data = data;
    }



    @Override
    public int hashCode() {
        return this.data.getDayOfMonth();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Status))
            return false;
        Status status = (Status) obj;
        return (this.data.equals(status.data));
    }

}
