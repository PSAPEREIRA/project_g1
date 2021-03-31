package pt.ipp.isep.dei.project1.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project1.model.house.HouseGrid;

import java.util.List;


@Repository
public interface HouseGridRepository extends CrudRepository<HouseGrid, Long> {
    List<HouseGrid> findAll();

}
