package pt.ipp.isep.dei.project1.model.geographicarea;

import lombok.Getter;
import lombok.Setter;
import pt.ipp.isep.dei.project1.dto.sensordto.AreaSensorDto;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@Entity
public class GeographicArea {


    @Id
    private String name;
    private String description;
    @Embedded
    private OccupationArea occupationArea;
    @ManyToOne(cascade = CascadeType.ALL)
    private GeographicAreaType geographicAreaType;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "geographicArea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AreaSensor> listOfAreaSensors = new ArrayList<>();
    @Transient
    private List<GeographicArea> listInsideOf = new ArrayList<>();

    private static String rainfall = "rainfall";

    public GeographicArea() {
    }

    public GeographicArea(String name, String description, OccupationArea occupationArea, GeographicAreaType geographicAreaType) {
        this.name = name;
        this.description = description;
        this.occupationArea = occupationArea;
        this.geographicAreaType = geographicAreaType;
        validateBeforeCreate();
    }

    private void validateBeforeCreate() {
        validateName();
    }


    private void validateName() {
        if (name == null || "".equals(name))
            throw new RuntimeException("Insert a valid name of Geographic Area");
    }


    @Override
    public String toString() {
        return "Geographic Area{" +
                ", Name: " + name + "\n" +
                ", Description: " + description + "\n" +
                ", Area of Occupation: " + occupationArea + "\n" +
                ", Geographic Area Type: " + geographicAreaType + "\n" +
                ", List of Sensors: " + listOfAreaSensors + "\n" +
                '}';
    }

    public GeographicAreaType getGeographicAreaType() {
        return geographicAreaType;
    }

    public void setGeographicAreaType(GeographicAreaType mGeographicAreaType) {
        this.geographicAreaType = mGeographicAreaType;
    }


    public OccupationArea getOccupationArea() {
        return occupationArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        this.description = mDescription;
    }

    /**
     * Hashcode and Equals
     *
     * @return
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GeographicArea))
            return false;
        GeographicArea c = (GeographicArea) obj;
        return this.name.equals(c.getName());
    }


    @Override
    public int hashCode() {
        return this.name.charAt(0);
    }


    /**
     * Method to add one Geographic Area inside another
     *
     * @param geographicArea
     * @return
     */

    public boolean addGeographicAreaInsideAnother(GeographicArea geographicArea) {
        if (listInsideOf.contains(geographicArea)) {
            return false;
        }
        listInsideOf.add(geographicArea);
        return true;
    }

    /**
     * Method to get the Average Rainfall in One Geographic Area
     *
     * @param initialDate
     * @param finalDate
     * @return
     */

    public double getAverageRainfallInOneAG(LocalDate initialDate, LocalDate finalDate, SensorType sensorType) {
        LocalDate init = initialDate;
        LocalDate fin = finalDate;
        if (fin.isBefore(init)) {
            LocalDate aux;
            aux = fin;
            fin = init;
            init = aux;
        }
        double average = 0;
        int count = 0;
        for (int sensorIndex = 0; sensorIndex < listOfAreaSensors.size(); sensorIndex++) {
            if (checkIfIsSameType(rainfall) && checkInstallationDate(init)) {
                average += getAverage(init, fin, sensorIndex,sensorType);
                count++;
            }
        }
        if (count == 0)
            return -1;
        return average;
    }

/**

    public double getAverageRainfallInOneAGV2(LocalDate initialDate, LocalDate finalDate) {
        LocalDate init = initialDate;
        LocalDate fin = finalDate;
        if (fin.isBefore(init)) {
            LocalDate aux;
            aux = fin;
            fin = init;
            init = aux;
        }



        List<AreaSensor> rainSensors =

        double average = 0;
        int count = 0;
        for (int sensorIndex = 0; sensorIndex < listOfAreaSensors.size(); sensorIndex++) {
            if (checkIfIsSameType(rainfall) && checkInstallationDate(init)) {
                average += getAverage(init, fin, sensorIndex);
                count++;
            }
        }
        if (count == 0)
            return -1;
        return average / count;
    }
*/

    /////////////////////////////////////////////////////////////////////

    /**
     * AreaSensor methods
     */

    /**
     public AreaSensor getSensorsClosestToHouseArea(Location location, SensorType sensorType) {
     return getSensorClosestForReadings(location, sensorType);
     }*/


    /**
     * public List<String> getListOfSensorsAsString() {
     * return listOfAreaSensors.getSensorNameList();
     * }
     **/


    public int createAndAddSensor(AreaSensorDto areaSensorDto) {
        AreaSensor areaSensor;
        try {
            areaSensor = new AreaSensor(areaSensorDto.getIdOfSensor(), areaSensorDto.getName(),
                    areaSensorDto.getLocation(), areaSensorDto.getSensorType(), areaSensorDto.getInstallationDate(), areaSensorDto.getUnit());
            areaSensor.setGeographicArea(this);
        } catch (Exception e) {
            return -1;
        }
        if (addSensor(areaSensor))
            return 1;
        return -2;
    }

    public boolean addSensor(AreaSensor newAreaSensor) {
        if (!this.getListOfAreaSensors().contains(newAreaSensor)) {
            this.getListOfAreaSensors().add(newAreaSensor);
            return true;
        } else
            return false;
    }

    public AreaSensor gettingSensorFromGeographicArea(int sensorInput) {
        return gettingSensorFromList(sensorInput);
    }

    public AreaSensor gettingSensorFromList(int sensorIndexInput) {
        if (listOfAreaSensors.isEmpty())
            return null;
        return listOfAreaSensors.get(sensorIndexInput);
    }

    public boolean checkIfIsSameType(String sensorType) {
        for (int sensorIndex = 0; sensorIndex < listOfAreaSensors.size(); sensorIndex++) {
            if (listOfAreaSensors.get(sensorIndex).checkSensorTypeToList(sensorType))
                return true;
        }
        return false;
    }

    public boolean checkInstallationDate(LocalDate localDate) {
        for (int sensorIndex = 0; sensorIndex < listOfAreaSensors.size(); sensorIndex++) {
            if (listOfAreaSensors.get(sensorIndex).checkIfInstallationBefore(localDate))
                return true;
        }
        return false;
    }


    public double getAverage(LocalDate initialDate, LocalDate finalDate, int i, SensorType sensorType) {

        if (listOfAreaSensors.get(i).getSensorType().equals(sensorType))
            return listOfAreaSensors.get(i).getAverageInOneInterval(initialDate, finalDate);

        else{
            return 0;
        }
    }


    public AreaSensor getSensorClosestForReadings(Location location, SensorType sensorType) {
        List<AreaSensor> listOfSensorsOfTheSameType = getListOfSensorsOfTheSameType(sensorType);
        if (listOfSensorsOfTheSameType.isEmpty())
            return null;
        else {
            double distance = listOfSensorsOfTheSameType.get(0).calculateDistanceBetweenSensorToHouse(location);
            List<AreaSensor> listOfSensorsResult = new ArrayList<>();
            listOfSensorsResult.add(listOfSensorsOfTheSameType.get(0));

            for (int sensorTypeIndex = 1; sensorTypeIndex < listOfSensorsOfTheSameType.size(); sensorTypeIndex++) {
                if (listOfSensorsOfTheSameType.get(sensorTypeIndex).calculateDistanceBetweenSensorToHouse(location) < distance) {
                    listOfSensorsResult.clear();
                    listOfSensorsResult.add(listOfSensorsOfTheSameType.get(sensorTypeIndex));
                }
                if (Double.compare(listOfSensorsOfTheSameType.get(sensorTypeIndex).calculateDistanceBetweenSensorToHouse(location), distance) == 0) {
                    listOfSensorsResult.add(listOfSensorsOfTheSameType.get(sensorTypeIndex));
                }
            }
            return getSensorByListResultAndListSameType(listOfSensorsResult);
        }
    }

    public AreaSensor getSensorByListResultAndListSameType(List<AreaSensor> listOfSensorsResult) {
        if (listOfSensorsResult.size() == 1)
            return listOfSensorsResult.get(0);
        else {
            for (int sensorIndex = 0; sensorIndex < listOfSensorsResult.size() - 1; sensorIndex++) {
                LocalDateTime result = listOfSensorsResult.get(sensorIndex).getListOfReadings().getLastReadingDate();
                if (listOfSensorsResult.get(sensorIndex + 1).getListOfReadings().getLastReadingDate().isAfter(result))
                    listOfSensorsResult.set(0, listOfSensorsResult.get(sensorIndex + 1));
            }
        }
        return listOfSensorsResult.get(0);
    }


    public List<AreaSensor> getListOfSensorsOfTheSameType(SensorType sensorType) {
        List<AreaSensor> areaSensors = new ArrayList<>();
        for (int sensorIndex = 0; sensorIndex < this.listOfAreaSensors.size(); sensorIndex++) {
            if (this.listOfAreaSensors.get(sensorIndex).checkSensorType(sensorType) && !this.listOfAreaSensors.get(sensorIndex).checkListOfReadingsOfSensorIsEmpty())
                areaSensors.add(this.listOfAreaSensors.get(sensorIndex));
        }
        if (areaSensors.isEmpty())
            return Collections.emptyList();
        return areaSensors;

    }

    public AreaSensor newSensor(String idOfSensor, String name, Location location, SensorType sensorType, LocalDate localDate, String unit) {
        return new AreaSensor(idOfSensor, name, location, sensorType, localDate, unit);
    }

    public boolean addSensorToList(AreaSensor newAreaSensor) {
        if (!listOfAreaSensors.contains(newAreaSensor)) {
            listOfAreaSensors.add(newAreaSensor);
            return true;
        }
        return false;
    }

    public AreaSensor getSensorBySensorID(String sensorID) {
        for (int SensorIndex = 0; SensorIndex < listOfAreaSensors.size(); SensorIndex++)
            if (listOfAreaSensors.get(SensorIndex).getIdOfAreaSensor().equals(sensorID))
                return listOfAreaSensors.get(SensorIndex);
        return null;
    }

    public List<AreaSensor> getListOfSensorsActive() {
        List<AreaSensor> listOfSensorsActivated = new ArrayList<>();
        for (int i = 0; i < listOfAreaSensors.size(); i++) {
            int sizeStatus = listOfAreaSensors.get(i).getListOfStatus().getStatusList().size();
            if (listOfAreaSensors.get(i).getListOfStatus().getStatusList().isEmpty() || listOfAreaSensors.get(i).getListOfStatus().getStatusList().get(sizeStatus - 1).isSensorStatus())
                listOfSensorsActivated.add(listOfAreaSensors.get(i));
        }
        return listOfSensorsActivated;
    }

    public double getAverageDailyTemperatureInHouseArea(Location location, SensorType sensorType, LocalDate date) {
        AreaSensor areaSensor = getSensorClosestForReadings(location, sensorType);
        return areaSensor.getAverageInOneDay(date);
    }
}

