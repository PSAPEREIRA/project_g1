package pt.ipp.isep.dei.project1.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;

import java.util.List;

@Repository
public interface GeographicAreaTypeRepository extends CrudRepository<GeographicAreaType,String> {
    List<GeographicAreaType> findAll();
}
