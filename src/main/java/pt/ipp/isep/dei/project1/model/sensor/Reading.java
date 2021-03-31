package pt.ipp.isep.dei.project1.model.sensor;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public final class Reading {

    private LocalDateTime dateTime;
    private double value;
    private String unit;


    public Reading(LocalDateTime date, double value) {
        this.dateTime = date;
        this.value = value;
    }


    public Reading() {
    }

    @Override
    public String toString() {
        return  "Reading{" +
                "Value: " + value + '\'' + "\n" +
                ", Local Date time: " + dateTime + "\n" +
                ", Unit: " + unit + "\n" +
                '}';
    }


    public String getDateAsString(){
        return dateTime.toString();
    }

    public boolean isValueOfDay(LocalDate dateInput) {
        return dateTime.toLocalDate().equals(dateInput);
    }

    public boolean checkIfDateIsBefore(LocalDate finalDate) {
        return dateTime.toLocalDate().isBefore(finalDate);
    }

    public boolean checkIfDateIsAfter(LocalDate initialDate) {
        return dateTime.toLocalDate().isAfter(initialDate);
    }

    @Override
    public int hashCode() {
        return this.dateTime.getDayOfMonth();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Reading))
            return false;
        Reading reading = (Reading) obj;
        return (this.dateTime.equals(reading.dateTime));
    }

}
