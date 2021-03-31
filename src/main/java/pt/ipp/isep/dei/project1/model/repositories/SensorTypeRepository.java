package pt.ipp.isep.dei.project1.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.util.List;

@Repository
public interface SensorTypeRepository extends CrudRepository<SensorType, String> {
    List<SensorType> findAll();
}
