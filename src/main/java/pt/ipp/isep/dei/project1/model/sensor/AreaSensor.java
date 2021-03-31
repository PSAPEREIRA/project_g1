package pt.ipp.isep.dei.project1.model.sensor;

import lombok.Getter;
import lombok.Setter;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class AreaSensor {

    @Id
    @NotNull
    private String idOfAreaSensor;
    private String name;
    @Embedded
    private Location location;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private SensorType sensorType;
    private LocalDate installationDate;
    @Embedded
    private ListOfReadings listOfReadings = new ListOfReadings();
    private String unit;
    @Embedded
    private ListOfStatus listOfStatus = new ListOfStatus();
    @ManyToOne
    @JoinColumn(name = "geographicArea_id")
    private GeographicArea geographicArea;


    /**
     * Builder's
     */


    public AreaSensor(String idOfAreaSensor, String name, Location location, SensorType designation, LocalDate installationDate, String unit) {
        this.idOfAreaSensor = idOfAreaSensor;
        this.name = name;
        this.sensorType = designation;
        this.installationDate = installationDate;
        this.location = location;
        this.unit = unit;
        validateBeforeCreate();
    }

    public AreaSensor() {
    }

    private void validateBeforeCreate() {
        validateName();
    }


    private void validateName() {
        if (idOfAreaSensor == null || "".equals(idOfAreaSensor))
            throw new RuntimeException("Insert a valid name!");
    }


    /**
     * Equals and Hashcode
     *
     * @return
     */

    @Override
    public String toString() {
        return "\n IdLocal: " + idOfAreaSensor + "\n " +
                "Name: " + name + "\n Location: " + location +
                sensorType + "\n Installation Date: " + installationDate +
                "\n Units: " + unit + "\n Readings: " +
                listOfReadings + "\n";
    }

    @Override
    public int hashCode() {
        return this.name.charAt(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof AreaSensor))
            return false;
        AreaSensor areaSensor1 = (AreaSensor) obj;
        return (this.idOfAreaSensor.equals(areaSensor1.idOfAreaSensor));
    }


    /**
     * Method to get distance between 2 sensors
     *
     * @param destinationAreaSensor
     * @return
     */

    public double getDistanceBetween2Sensors(AreaSensor destinationAreaSensor) {
        Location destinationSensorLocation = destinationAreaSensor.getLocation();
        return this.location.distanceTo(destinationSensorLocation);
    }


    /**
     * @return
     */

    public double getCurrentTemperature() {
        double currentTemperature;
        currentTemperature = listOfReadings.getLastReading();
        return currentTemperature;
    }

    // >= 0 is valid
    // <0 not valid


    public double getAverageInOneDay(LocalDate date) {
        double total = 0;
        int numberOfReadings = 0;
        for (int readingIndex = 0; readingIndex < listOfReadings.getListOfReading().size(); readingIndex++)
            if (listOfReadings.getListOfReading().get(readingIndex).getDateTime().toLocalDate().equals(date)) {
                total += listOfReadings.getListOfReading().get(readingIndex).getValue();
                numberOfReadings++;
            }
        if (numberOfReadings == 0)
            return 0;
        else
            return total / numberOfReadings;
    }


    public double getAverageInOneInterval(LocalDate initialDate, LocalDate finalDate) {
        double average = 0;
        int count = 0;
        LocalDate init = initialDate;
        while (init.isBefore(finalDate) || init.isEqual(finalDate)) {
            average += getAverageInOneDay(init);
            count++;
            init = init.plusDays(1);
        }
        if (count == 0)
            return -1;
        else
            return average / count;
    }


    public double getSumOfRainfallInCertainDay(AreaSensor areaSensorInput, LocalDate localDateInput) {
        double sumOfRainfall = 0;
        for (int readingIndex = 0; readingIndex < areaSensorInput.getListOfReadings().getListOfReading().size(); readingIndex++) {
            if (localDateInput.equals(areaSensorInput.getListOfReadings().getListOfReading().get(readingIndex).getDateTime().toLocalDate()))
                sumOfRainfall += areaSensorInput.getListOfReadings().getListOfReading().get(readingIndex).getValue();
        }
        return sumOfRainfall;
    }

    public double getSumOfValueOnSensorInCertainDay(LocalDate dateInput) {
        return listOfReadings.getSumOfValueOfReadingsInCertainDay(dateInput);
    }

    public boolean checkSensorType(SensorType sensorType) {
        return (this.sensorType.checkIfIsDesignation(sensorType));
    }

    public boolean checkListOfReadingsOfSensorIsEmpty() {
        return (listOfReadings.checkIfListOfReadingsIsEmpty());
    }

    public double calculateDistanceBetweenSensorToHouse(Location location) {
        return this.location.distanceTo(location);
    }

    public boolean checkIfInstallationBefore(LocalDate initialDate) {
        return installationDate.isBefore(initialDate);
    }

    public boolean checkSensorTypeToList(String sensorType) {
        return sensorType.equals(this.sensorType.getType());
    }


    public Reading getLastColdestDayInGivenPeriod(LocalDate initDate, LocalDate finalDate) {
        Reading lastColdestTemperature;
        lastColdestTemperature = listOfReadings.getLastColdestDayInGivenPeriod(initDate, finalDate);
        return lastColdestTemperature;
    }

    public Reading getFirstHottestDayInGivenPeriod(LocalDate initDate, LocalDate finalDate) {
        Reading firstHottestTemperature;
        firstHottestTemperature = listOfReadings.getFirstHottestDayInGivenPeriod(initDate, finalDate);
        return firstHottestTemperature;
    }
    public void addReading(Reading reading) {
        listOfReadings.addReading(reading);
    }


    public List<LocalDate> getDayWithHighestTemperatureAmplitude(LocalDate initialDate, LocalDate finalDate) {
        return listOfReadings.getHighestTemperatureAmplitudeAndDate(initialDate, finalDate);
    }

    public double getHighestTemperatureAmplitude(LocalDate initialDate, LocalDate finalDate) {
        return listOfReadings.getHighestTemperatureAmplitude(initialDate, finalDate);
    }


    public void addStatus(Status status2) {
        listOfStatus.getStatusList().add(status2);
    }

}
