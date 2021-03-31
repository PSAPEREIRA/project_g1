package pt.ipp.isep.dei.project1.model.repositories;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project1.dto.geographicareadto.GeographicAreaTypeDto;
import pt.ipp.isep.dei.project1.mapper.MapperListOfGeographicAreaType;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicAreaType;
import java.util.Collections;
import java.util.List;

@Service
public class GeographicAreaTypeRepo {

    @Getter
    private final List<GeographicAreaType> listOfGeographicAreaType;
    private final GeographicAreaTypeRepository geographicAreaTypeRepository;

    @Autowired
    public GeographicAreaTypeRepo(GeographicAreaTypeRepository geographicAreaTypeRepository) {
        this.geographicAreaTypeRepository = geographicAreaTypeRepository;
        this.listOfGeographicAreaType = geographicAreaTypeRepository.findAll();
    }

    public GeographicAreaType getTypeByName(String name) {
        for (int geoAreaTypeIndex = 0; geoAreaTypeIndex < listOfGeographicAreaType.size(); geoAreaTypeIndex++)
            if (listOfGeographicAreaType.get(geoAreaTypeIndex).getType().equals(name))
                return listOfGeographicAreaType.get(geoAreaTypeIndex);
        return null;
    }

    public boolean add(GeographicAreaType type) {
        if (!listOfGeographicAreaType.contains(type)) {
            listOfGeographicAreaType.add(type);
            geographicAreaTypeRepository.save(type);
            return true;
        } else
            return false;
    }

    public List<GeographicAreaTypeDto> getListOfAreaTypesDTO() {
        if (!listOfGeographicAreaType.isEmpty())
            return MapperListOfGeographicAreaType.toDto(listOfGeographicAreaType).getListOfGATypesDto();
        return Collections.emptyList();
    }


}
