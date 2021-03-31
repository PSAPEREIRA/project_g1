package pt.ipp.isep.dei.project1.model.geographicarea;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Embeddable
public class Location {


    @Column(name="Altitude")
    private double altitude; // degrees
    @Column(name="Latitude")
    private double latitude; // degrees
    @Column(name="Longitude")
    private double longitude; // degrees

    protected Location() {
    }

    public Location(double latitude, double longitude, double altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        validateBeforeCreate();
    }


    @Override
    public String toString() {
        return "Latitude: " + latitude + "\n" + " Longitude: " + longitude + "\n" + "Altitude: " + altitude;
    }

    private void validateBeforeCreate() {
        validateLongitude();
        validateLatitude();
        validateAltitude();
    }

    private void validateAltitude() {
        if (Double.isNaN(altitude))
            throw new RuntimeException("Insert a valid Altitude");
    }

    private void validateLatitude() {
        if (Double.isNaN(latitude))
            throw new RuntimeException("Insert a valid Latitude");
    }

    private void validateLongitude() {
        if (Double.isNaN(longitude))
            throw new RuntimeException("Insert a valid Longitude");
    }

    /**
     * Method to get distance between two points
     *
     * @param destination
     * @return
     */

    public double distanceTo(Location destination) { /**na variavel Location temos os
     valores referentes à latitude e longitude**/

        double originLatitude = this.latitude; /**como a variável origin tem dois valores (latitude e longitude), temos
         que especificar a qual se refere cada um delas**/
        double originLongitude = this.longitude;
        double destinationLatitude = destination.latitude;
        double destinationLongitude = destination.longitude;
        double earthRadiusKm = 6371.137;

        /** como na fórmula usada precisamos do valor da latitude em radianos
         * e também da diferença da latitude (destinyLatitude -
         originLatitude) precisamos de calcular separadamente**/

        double dLat = degreesToRadians(destinationLatitude - originLatitude);
        double dLon = degreesToRadians(destinationLongitude - originLongitude);

        originLatitude = degreesToRadians(originLatitude);
        destinationLatitude = degreesToRadians(destinationLatitude);

        double firstFormulaeResult = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2)
                * Math.cos(originLatitude) * Math.cos(destinationLatitude);
        double secondFormulaeResult = 2 * Math.atan2(Math.sqrt(firstFormulaeResult), Math.sqrt(1 - firstFormulaeResult));


        return earthRadiusKm * secondFormulaeResult;
    }

    /**
     * Method to transform degrees to radians
     *
     * @param degrees
     * @return
     */

    public double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    /**
     * Hashcod and Equals
     *
     * @return
     */


    @Override
    public int hashCode() {
        Double latitudeValue = Double.valueOf(latitude);
        return latitudeValue.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Location))
            return false;
        Location loc = (Location) obj;
        Double latitudeValue = Double.valueOf(latitude);
        Double longitudeValue = Double.valueOf(longitude);
        return (latitudeValue.equals(loc.getLatitude()) && longitudeValue.equals(loc.getLongitude()));

    }

}
