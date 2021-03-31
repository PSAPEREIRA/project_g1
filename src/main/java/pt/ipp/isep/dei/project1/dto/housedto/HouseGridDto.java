package pt.ipp.isep.dei.project1.dto.housedto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import java.util.List;

public class HouseGridDto extends ResourceSupport {

    private String code;
    @Getter @Setter
    private List<String> roomList;

    @Getter @Setter
    private double powerLimiter;

    public String getCode() {
        return code;
    }

    public void setCode(String mCode) {
        this.code = mCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof HouseGridDto))
            return false;
        HouseGridDto c = (HouseGridDto) obj;
        return this.code.equals(c.code);
    }

    @Override
    public int hashCode() {
        return this.code.charAt(0);
    }

}
