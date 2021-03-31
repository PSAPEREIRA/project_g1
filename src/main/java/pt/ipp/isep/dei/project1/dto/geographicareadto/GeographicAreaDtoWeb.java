package pt.ipp.isep.dei.project1.dto.geographicareadto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
public class GeographicAreaDtoWeb extends ResourceSupport {

    private String name;
    private String description;


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GeographicAreaDtoWeb))
            return false;
        GeographicAreaDtoWeb c = (GeographicAreaDtoWeb) obj;
        return this.name.equals(c.getName());
    }

    @Override
    public int hashCode() {
        return this.name.charAt(0);
    }
}
