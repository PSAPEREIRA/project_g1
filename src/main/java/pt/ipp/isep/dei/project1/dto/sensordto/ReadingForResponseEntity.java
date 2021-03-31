package pt.ipp.isep.dei.project1.dto.sensordto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class ReadingForResponseEntity {

    private double value;
    private String unit = "C";

}
