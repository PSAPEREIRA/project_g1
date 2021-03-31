package pt.ipp.isep.dei.project1.controllers.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project1.dto.sensordto.ReadingDto;
import pt.ipp.isep.dei.project1.io.ui.utils.Configurations;
import pt.ipp.isep.dei.project1.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project1.model.house.Room;
import pt.ipp.isep.dei.project1.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project1.model.sensor.Reading;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.readers.FileReader;
import pt.ipp.isep.dei.project1.model.repositories.GeographicAreaDomainService;
import pt.ipp.isep.dei.project1.model.repositories.RoomDomainService;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Controller
public class ImportSensorsReadingsController {

    private final GeographicAreaDomainService geographicAreaDomainService;
    private final RoomDomainService roomDomainService;
    //private static final String LOG_READINGS = "logReadings.properties";


    @Autowired
    public ImportSensorsReadingsController(GeographicAreaDomainService geographicAreaDomainService, RoomDomainService roomDomainService) {
        this.geographicAreaDomainService = geographicAreaDomainService;
        this.roomDomainService = roomDomainService;
    }

    public List<Integer> addReadingsToSensorsOfGA(String path) throws Exception {
        List<Integer> list = new ArrayList<>();
        FileReader reader = checkFileReader(path);
        List<ReadingDto> readingDtoList;


        if (reader == null) {
            list.add(-1);
            return list;
        }

        readingDtoList = reader.importReadingsOfSensor(path);


        if (readingDtoList.equals(Collections.emptyList())) {
            list.add(-1);
            return list;
        }
        List<AreaSensor> listOfAreaSensors = geographicAreaDomainService.getAllSensors();
        return setReadingsBySensorIDescription(listOfAreaSensors, readingDtoList);
    }

    public FileReader checkFileReader(String path) throws Exception {
        FileReader fileReader;
        String extension = "";
        try {
            extension = path.substring(path.lastIndexOf('.'));
        } catch (Exception e) {
            e.getSuppressed();
        }
        List<FileReader> fileReadersObjects = new ArrayList<>();

        fileReadersObjects = getListOfReadersOfSensorReadings();

        switch (extension) {
            case ".xml":
                fileReader = fileReadersObjects.get(0);
                return fileReader;
            case ".json":
                fileReader = fileReadersObjects.get(1);
                return fileReader;
            case ".csv":
                fileReader = fileReadersObjects.get(2);
                return fileReader;
            default:
                break;
        }
        return null;
    }


    public List<Integer> setReadingsBySensorIDescription(List<AreaSensor> listOfAreaSensors, List<ReadingDto> readingDtoList) throws IOException {
        List<Integer> list = new ArrayList<>();
        list.add(readingDtoList.get(readingDtoList.size() - 1).getStatus());
        //Logger logger = Logger.getLogger(LOG_READINGS);
        //FileHandler fh = getFh(logger);
        int count = 0;
        for (AreaSensor areaSensor : listOfAreaSensors) {
            for (ReadingDto readingDto : readingDtoList) {
                if (readingDto.getIdOfSensor().equals(areaSensor.getIdOfAreaSensor()) && checkIfAddReadingToSensor(areaSensor, readingDto)) {
                    count++;
                    readingDto.setOnSensor(true);
                }
            }
            GeographicArea ga = geographicAreaDomainService.getGeographicAreaByName(areaSensor.getGeographicArea().getName());
            geographicAreaDomainService.getGeographicAreaRepository().save(ga);
        }

        int countLog = checkIfAddReadingToSensor(readingDtoList);

        list.add(count);
        list.add(countLog);
        //fh.close();
        return list;
    }

    private boolean checkIfAddReadingToSensor(AreaSensor areaSensor, ReadingDto readingDto) {
        //Logger logger = Logger.getLogger(LOG_READINGS);

        if ((readingDto.getDateTime().toLocalDate().isAfter(areaSensor.getInstallationDate()) || readingDto.getDateTime().toLocalDate().isEqual(areaSensor.getInstallationDate())) && checkSensorStatusInDate(areaSensor, readingDto.getDateTime().toLocalDate())) {
            convertUnits(readingDto);
            Reading reading = new Reading(readingDto.getDateTime(), readingDto.getValue());
            reading.setUnit(readingDto.getUnit());
            if (!areaSensor.getListOfReadings().getListOfReading().contains(reading)) {
                areaSensor.addReading(reading);
                return true;
            } else {
            }
            //logger.warning(readingDto.getIdOfSensor() + "," + readingDto.getDateTime() + "," + readingDto.getValue() + " - - This reading already exist");

            //logger.info(readingDto.getIdOfSensor() + "," + readingDto.getDateTime() + "," + readingDto.getValue() + " - - This reading already exist");

        }
        return false;
    }

    public List<Integer> addReadingsToSensorsOfHouse(String path) throws Exception {
        List<Integer> listHouseReadings = new ArrayList<>();
        FileReader reader = checkFileReader(path);
        List<ReadingDto> readingDtoList;

        if (reader == null) {
            listHouseReadings.add(-1);
            return listHouseReadings;
        }

        readingDtoList = reader.importReadingsOfSensor(path);

        if (readingDtoList.equals(Collections.emptyList())) {
            listHouseReadings.add(-1);
            return listHouseReadings;
        }
        List<RoomSensor> listOfSensors = roomDomainService.getAllSensors();
        return setReadingsByRoomSensorIDescription(listOfSensors, readingDtoList);
    }

    public List<Integer> setReadingsByRoomSensorIDescription(List<RoomSensor> roomSensorList, List<ReadingDto> readingDtoList) throws IOException {
        List<Integer> list = new ArrayList<>();
        list.add(readingDtoList.get(readingDtoList.size() - 1).getStatus());
        //Logger logger = Logger.getLogger(LOG_READINGS);

        //FileHandler fh = getFh(logger);
        int count = 0;
        for (RoomSensor roomSensor : roomSensorList) {
            for (ReadingDto readingDto : readingDtoList) {
                if (readingDto.getIdOfSensor().equals(roomSensor.getIdOfRoomSensor()) && checkIfAddReadingToRoomSensor(roomSensor, readingDto)) {
                    count++;
                    readingDto.setOnSensor(true);
                }
            }
            Room room = roomDomainService.getRoomByName(roomSensor.getRoom().getName());
            roomDomainService.getRoomRepository().save(room);
        }

        int countLog = checkIfAddReadingToSensor(readingDtoList);

        list.add(count);
        list.add(countLog);
        //fh.close();
        return list;
    }


    private boolean checkIfAddReadingToRoomSensor(RoomSensor roomSensor, ReadingDto readingDto) {
        //Logger logger = Logger.getLogger(LOG_READINGS);

        if ((readingDto.getDateTime().toLocalDate().isAfter(roomSensor.getInstallationDate()) || readingDto.getDateTime().toLocalDate().isEqual(roomSensor.getInstallationDate())) && checkRoomSensorStatusInDate(roomSensor, readingDto.getDateTime().toLocalDate())) {
            convertUnits(readingDto);
            Reading reading = new Reading(readingDto.getDateTime(), readingDto.getValue());
            reading.setUnit(readingDto.getUnit()); /** A ReadingDTO tem null nas unidades -  System.out.println(readingDto); */
            if (!roomSensor.getListOfReadings().getListOfReading().contains(reading)) {
                roomSensor.addReading(reading);
                return true;
            } else {
            }
            //logger.warning(readingDto.getIdOfSensor() + "," + readingDto.getDateTime() + "," + readingDto.getValue() + " - This reading already exist");

            //logger.info(readingDto.getIdOfSensor() + "," + readingDto.getDateTime() + "," + readingDto.getValue() + " - This reading already exist");

        }
        return false;
    }

    private int checkIfAddReadingToSensor(List<ReadingDto> readingDtoList) {
        int countLog = 0;
        //Logger logger = Logger.getLogger(LOG_READINGS);

        for (ReadingDto readingDto : readingDtoList) {
            if (!readingDto.isOnSensor()) {
                countLog++;
                //logger.warning(readingDto.getIdOfSensor() + "," + readingDto.getDateTime() + "," + readingDto.getValue() + " - Impossible to add this reading, cannot find correct Sensor");

                //logger.info(readingDto.getIdOfSensor() + "," + readingDto.getDateTime() + "," + readingDto.getValue() + " - Impossible to add this reading, cannot find correct Sensor");

            }
        }
        return countLog;
    }


    public void convertUnits(ReadingDto readingDto) {
        if ("F".equalsIgnoreCase(readingDto.getUnit())) {
            double d = Math.pow(10, 2);
            readingDto.setValue(Math.round(((readingDto.getValue()) - 32) * (5d / 9d) * d) / d);
            readingDto.setUnit("C");
        }
    }

    public List<FileReader> getListOfReadersOfSensorReadings() throws Exception {
        FileReader readerOfSensorReadings;
        List<FileReader> readerOfSensorReadingsList = new ArrayList<>();
        List<String> readListOfReaders = Configurations.readListOfFileReadersOfSensorReadings();

        for (int i = 0; i < readListOfReaders.size(); i++) {
            readerOfSensorReadings = (FileReader) Class.forName(readListOfReaders.get(i)).newInstance();
            readerOfSensorReadingsList.add(readerOfSensorReadings);
        }
        return readerOfSensorReadingsList;
    }

    public boolean checkSensorStatusInDate(AreaSensor areaSensor, LocalDate localDate) {
        return areaSensor.getListOfStatus().getStatusByDate(localDate);
    }

    public boolean checkRoomSensorStatusInDate(RoomSensor roomSensor, LocalDate localDate) {
        return roomSensor.getListOfStatus().getStatusByDate(localDate);
    }

    /**

     public FileHandler getFh(Logger logger) throws IOException {
     ClassLoader classLoader = getClass().getClassLoader();
     File file = new File(classLoader.getResource(LOG_READINGS).getFile());
     String path = file.getPath();
     FileHandler fh = new FileHandler(URLDecoder.decode(path, "UTF-8"));
     logger.addHandler(fh);
     logger.setUseParentHandlers(false);
     SimpleFormatter formatter = new SimpleFormatter();
     fh.setFormatter(formatter);
     return fh;
     }
     **/
}
