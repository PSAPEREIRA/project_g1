package pt.ipp.isep.dei.project1.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;

import java.util.List;

@Repository
public interface GeographicAreaRepository extends CrudRepository<GeographicArea,String> {

    List<GeographicArea> findAll();

    GeographicArea getGeographicAreaByName (String name);
}
