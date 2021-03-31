package pt.ipp.isep.dei.project1.model.sensor;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListOfStatusTest {


    @Test
    void testGetListOfStatus() {
        //Arrange
        LocalDate lStatus = LocalDate.of(2018, 12, 30);
        ListOfStatus listOfStatus = new ListOfStatus();
        Status status = new Status(true, lStatus);
        //Act
        listOfStatus.addStatusToSensor(status);
        List<Status> resultList = listOfStatus.getStatusList();
        List<Status> expectedListResult = listOfStatus.getStatusList();

        boolean statusResult = resultList.get(0).isSensorStatus();
        LocalDate dateResult = resultList.get(0).getData();

        boolean expectedStatusResult = true;
        LocalDate expectedDateResult = LocalDate.of(2018,12,30);

        //Assert
        assertTrue(expectedStatusResult==statusResult&&expectedDateResult.isEqual(dateResult));
    }

    @Test
    void testAddStatusToSensorTrue() {
        //Arrange
        LocalDate lStatus = LocalDate.of(2018, 12, 30);
        ListOfStatus listOfStatus = new ListOfStatus();
        Status status = new Status(true, lStatus);
        //Act
        boolean result = listOfStatus.addStatusToSensor(status);
        //Assert
        assertTrue(result);
    }

    @Test
    void testAddStatusToSensorFalse() {
        //Arrange
        LocalDate lStatus = LocalDate.of(2018, 12, 30);
        ListOfStatus listOfStatus = new ListOfStatus();
        Status status = new Status(true, lStatus);
        //Act
        listOfStatus.addStatusToSensor(status);
        boolean result = listOfStatus.addStatusToSensor(status);
        //Assert
        assertFalse(result);
    }


    @Test
    void testAddStatusToSensorFalseEmptyList() {
        //Arrange
        LocalDate lStatus = LocalDate.of(2018, 12, 30);
        ListOfStatus listOfStatus = new ListOfStatus();
        //Act
        boolean result = listOfStatus.getStatusByDate(lStatus);
        //Assert
        assertTrue(result);
    }

    @Test
    void testAddStatusToSensorTrue2() {
        //Arrange
        LocalDate lStatus = LocalDate.of(2018, 11, 01);
        LocalDate lStatus2 = LocalDate.of(2018, 11, 30);
        LocalDate date = LocalDate.of(2018, 11, 15);
        ListOfStatus listOfStatus = new ListOfStatus();
        List<Status> lt = new ArrayList<>();
        Status status = new Status();
        status.setData(lStatus);
        status.setSensorStatus(false);
        Status status2 = new Status();
        status2.setData(lStatus2);
        status2.setSensorStatus(true);
        lt.add(status);
        lt.add(status2);
        listOfStatus.setStatusList(lt);
        //Act
        boolean result = listOfStatus.getStatusByDate(date);
        //Assert
        assertFalse(result);
    }

    @Test
    void testAddStatusToSensorFalse2() {
        //Arrange
        LocalDate lStatus = LocalDate.of(2018, 11, 01);
        LocalDate lStatus2 = LocalDate.of(2018, 11, 30);
        LocalDate lStatus3 = LocalDate.of(2018, 12, 15);
        LocalDate date = LocalDate.of(2018, 12, 30);
        ListOfStatus listOfStatus = new ListOfStatus();
        List<Status> lt = new ArrayList<>();
        Status status = new Status();
        status.setData(lStatus);
        status.setSensorStatus(false);
        Status status2 = new Status();
        status2.setData(lStatus2);
        status2.setSensorStatus(true);
        Status status3 = new Status();
        status3.setData(lStatus3);
        status3.setSensorStatus(false);
        lt.add(status);
        lt.add(status2);
        lt.add(status3);
        listOfStatus.setStatusList(lt);
        //Act
        boolean result = listOfStatus.getStatusByDate(date);
        //Assert
        assertFalse(result);
    }

    @Test
    void testAddStatusToSensorFalse3() {
        //Arrange
        LocalDate lStatus = LocalDate.of(2018, 11, 01);
        LocalDate lStatus2 = LocalDate.of(2018, 11, 30);
        LocalDate lStatus3 = LocalDate.of(2018, 12, 15);
        LocalDate date = LocalDate.of(2018, 11, 01);
        ListOfStatus listOfStatus = new ListOfStatus();
        List<Status> lt = new ArrayList<>();
        Status status = new Status();
        status.setData(lStatus);
        status.setSensorStatus(false);
        Status status2 = new Status();
        status2.setData(lStatus2);
        status2.setSensorStatus(true);
        Status status3 = new Status();
        status3.setData(lStatus3);
        status3.setSensorStatus(false);
        lt.add(status);
        lt.add(status2);
        lt.add(status3);
        listOfStatus.setStatusList(lt);
        //Act
        boolean result = listOfStatus.getStatusByDate(date);
        //Assert
        assertFalse(result);
    }

    @Test
    void testAddStatusToSensorTrue3() {
        //Arrange
        LocalDate lStatus = LocalDate.of(2018, 11, 01);
        LocalDate lStatus2 = LocalDate.of(2018, 11, 30);
        LocalDate date = LocalDate.of(2018, 11, 01);
        ListOfStatus listOfStatus = new ListOfStatus();
        List<Status> lt = new ArrayList<>();
        Status status = new Status();
        status.setData(lStatus);
        status.setSensorStatus(false);
        Status status2 = new Status();
        status2.setData(lStatus2);
        status2.setSensorStatus(true);
        lt.add(status);
        lt.add(status2);

        listOfStatus.setStatusList(lt);
        //Act
        boolean result = listOfStatus.getStatusByDate(date);
        //Assert
        assertTrue(result);
    }

}