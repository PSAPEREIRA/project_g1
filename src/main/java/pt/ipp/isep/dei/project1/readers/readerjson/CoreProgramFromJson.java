package pt.ipp.isep.dei.project1.readers.readerjson;

import com.fasterxml.jackson.annotation.JsonGetter;

public class CoreProgramFromJson {

    private ListOfGaFromFile geographicalAreaList;

    @JsonGetter("geographical_area_list")
    public ListOfGaFromFile getGeographicalAreaList() {
        return geographicalAreaList;
    }


    public void setGeographicalAreaList(ListOfGaFromFile geographicalAreaList) {
        this.geographicalAreaList = geographicalAreaList;
    }

    @Override
    public String toString(){
        return geographicalAreaList +"\n";
    }
}
