package pt.ipp.isep.dei.project1.controllersweb.housemonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.HouseDomainService;
import pt.ipp.isep.dei.project1.model.sensor.SensorType;

import java.time.LocalDate;


@RestController
@RequestMapping(value = "/house-monitoring")
@CrossOrigin(origins={"http://localhost:3000","http://smarthomeg1.azurewebsites.net"}, maxAge = 3600)
public class GetTheFirstHottestDayInGivenPeriodRestController {

        private final GeographicAreaDomainService geographicAreaDomainService;
        private final HouseDomainService houseDomainService;
        private static final String TEMPERATURE = "Temperature";

        @Autowired
        public GetTheFirstHottestDayInGivenPeriodRestController (HouseDomainService houseDomainService, GeographicAreaDomainService geographicAreaDomainService) {
            this.geographicAreaDomainService = geographicAreaDomainService;
            this.houseDomainService = houseDomainService;
        }

        @ModelAttribute
        LocalDate initLocalDate() {
            return LocalDate.now();
        }

        @GetMapping(value = "/house/geographic-area/sensors/hottestDay")
        public ResponseEntity<Object> getFirstHottestDayInGivenPeriodDto(@RequestParam(name = "s") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate, @RequestParam(name = "e") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate){
            if(houseDomainService.getGeographicAreaID()==null)
                return new ResponseEntity<>("No Geographic Area defined!", HttpStatus.NOT_ACCEPTABLE);
            if(!geographicAreaDomainService.checkIfHaveSensorsOnHouseAreaWithTypeAndReadings(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(TEMPERATURE)))
                return new ResponseEntity<>("No sensors in Geographic Area!", HttpStatus.NOT_ACCEPTABLE);
            ReadingDto result = geographicAreaDomainService.getFirstHottestDayInGivenPeriodDto(houseDomainService.getGeographicAreaID(), houseDomainService.getLocation(), new SensorType(TEMPERATURE), initialDate, finalDate);
            LocalDate responseBody = result.getDateTime().toLocalDate();
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
}
