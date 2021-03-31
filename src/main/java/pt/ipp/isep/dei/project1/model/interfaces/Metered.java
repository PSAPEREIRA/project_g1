package pt.ipp.isep.dei.project1.model.interfaces;

import java.time.LocalDateTime;
import pt.ipp.isep.dei.project1.model.sensor.ListOfReadings;

public interface Metered {

    double getNominalPower();

    double getConsumption(LocalDateTime initialDate, LocalDateTime finalDate) throws Exception;

    ListOfReadings getDataSeries(LocalDateTime startDate, LocalDateTime endDate);




}
