package pt.ipp.isep.dei.project1.model.repositories;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project1.model.geographicarea.Location;
import pt.ipp.isep.dei.project1.model.house.Address;
import pt.ipp.isep.dei.project1.model.house.House;


@Service
public class HouseDomainService {

    @Getter
    private House house;

    private final HouseRepository houseRepository;

    @Autowired
    public HouseDomainService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
        validate(houseRepository);
    }

    public void validate(HouseRepository houseRepository) {
         if (houseRepository.findAll().isEmpty())
            this.house = new House();
        else
            this.house = houseRepository.findAll().get(0);
    }

    public void add(House house) {
        this.house = house;
        houseRepository.save(house);
    }

    public Address getAddress() {
        return house.getAddress();
    }

    public String getGeographicAreaID() {
        return house.getGeographicAreaID();
    }

    public Location getLocation() {
        return house.getLocationOfHouse();
    }

    public void setAddress(Address address) {
        house.setAddress(address);
        houseRepository.save(house);
    }

    public void setLocationOfHouse(Location location) {
        house.setLocationOfHouse(location);
        houseRepository.save(house);
    }

    public void setGeographicAreaID(String geographicAreaName) {
        house.setGeographicAreaID(geographicAreaName);
        houseRepository.save(house);
    }


}
