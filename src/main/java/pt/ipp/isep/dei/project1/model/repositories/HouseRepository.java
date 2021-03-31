package pt.ipp.isep.dei.project1.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project1.model.house.House;

import java.util.List;


@Repository
public interface HouseRepository extends CrudRepository<House, String> {
    List<House> findAll();
}
