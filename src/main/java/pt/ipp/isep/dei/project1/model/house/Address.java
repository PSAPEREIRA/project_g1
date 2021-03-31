package pt.ipp.isep.dei.project1.model.house;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Address {

    private String street;
    private String zip;
    private String town;
    private String number;
    private String country;


    protected Address() {
    }

    public Address(String street, String zip, String town, String number, String country) {
        this.street = street;
        this.zip = zip;
        this.town = town;
        this.number = number;
        this.country = country;
    }

}
