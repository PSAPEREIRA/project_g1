package pt.ipp.isep.dei.project1.model.sensor;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Embeddable
@Table(name = "StatusList")
public class ListOfStatus {

    @Fetch(FetchMode.SELECT)
    @ElementCollection(fetch= FetchType.EAGER)
    @Embedded
    private List<Status> statusList = new ArrayList<>();


    public boolean getStatusByDate(LocalDate localDate) {
        if(statusList.isEmpty())
            return true;
        for (int i = 0; i< statusList.size()-1; i++)
            if(localDate.isAfter(statusList.get(i).getData()) && localDate.isBefore(statusList.get(i+1).getData()))
                return statusList.get(i).isSensorStatus();
        return statusList.get(statusList.size()-1).isSensorStatus();//return the last status of the list
    }

    public boolean addStatusToSensor (Status status){
        if (!statusList.contains(status)) {
            statusList.add(status);
            return true;
        }
        return false;
    }

}
