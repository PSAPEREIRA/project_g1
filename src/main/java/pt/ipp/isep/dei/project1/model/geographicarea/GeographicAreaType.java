package pt.ipp.isep.dei.project1.model.geographicarea;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
@Entity
public class GeographicAreaType implements Serializable {


    @Id
    @Column(name = "type")
    private String type;

    /**
     * Builder's
     *
     * @param type
     */


    public GeographicAreaType(String type) {
        this.type = type;
        validateBeforeCreate();
    }

    protected GeographicAreaType() {
    }


    private void validateBeforeCreate() {
        validateName();
    }

    private void validateName() {
        if (type == null || "".equals(type))
            throw new RuntimeException("Insert a valid name of Geographic Area Type");
    }


    @Override
    public int hashCode() {
        return this.type.charAt(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GeographicAreaType))
            return false;
        GeographicAreaType c = (GeographicAreaType) obj;
        return (this.type.equalsIgnoreCase(c.type));
    }

    @Override
    public String toString() {
        return type + "\n";
    }

}