package pt.ipp.isep.dei.project1.readers.readerjson;

public class LocationFromFile {

    private double latitude;
    private double longitude;
    private double altitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString(){
        return latitude +" "+ longitude +" "+ altitude;
    }
}
