package pt.ipp.isep.dei.project1.dto.geographicareadto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import pt.ipp.isep.dei.project1.dto.sensordto.ListOfAreaSensorsDto;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.OccupationArea;

@Getter @Setter
public class GeographicAreaDto extends ResourceSupport {

    private String name;
    private String description;
    private OccupationArea occupationArea;
    private GeographicAreaType geographicAreaType;
    private ListGeographicAreaDto listInsideOf = new ListGeographicAreaDto();
    private ListOfAreaSensorsDto listOfAreaSensorsDto = new ListOfAreaSensorsDto();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GeographicAreaDto))
            return false;
        GeographicAreaDto c = (GeographicAreaDto) obj;
        return this.name.equals(c.getName());
    }

    @Override
    public int hashCode() {
        return this.name.charAt(0);
    }


}
