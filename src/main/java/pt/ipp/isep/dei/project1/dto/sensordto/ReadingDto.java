package pt.ipp.isep.dei.project1.dto.sensordto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ReadingDto {


    private String idOfSensor;
    private LocalDateTime dateTime;
    private double value;
    private String unit;
    private int status;
    private boolean onSensor;

    public ReadingDto(String idOfSensor, LocalDateTime dateTime, double value, String unit, int status) {
        this.idOfSensor = idOfSensor;
        this.dateTime = dateTime;
        this.value = value;
        this.unit = unit;
        this.status = status;
    }

    @Override
    public String toString(){
        return idOfSensor +"\n"+ dateTime +"\n"+ value +"\n"+ unit +"\n"+ status;
    }
}
