package pt.ipp.isep.dei.project1.model.sensor;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListOfReadingsTest {

    @Test
    void getMaxTempOfDay() {
        //Arrange
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 21, 12, 32);
        Reading r3 = new Reading(date3, 14);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        double expectedResult = 14;
        //Act
        LocalDate localDate = LocalDate.of(2018, 12, 21);
        double result = lr1.getMaxTempOfDay(localDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getMaxTempOfDayWithOtherDay() {
        //Arrange
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 21, 12, 32);
        Reading r3 = new Reading(date3, 14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 20, 12, 32);
        Reading r4 = new Reading(date4, 15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);
        double expectedResult = 14;
        //Act
        LocalDate localDate = LocalDate.of(2018, 12, 21);
        double result = lr1.getMaxTempOfDay(localDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getMaxTempOfDayNegativeTemperature() {
        //Arrange
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, -12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, -13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 21, 12, 32);
        Reading r3 = new Reading(date3, -14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 20, 12, 32);
        Reading r4 = new Reading(date4, -15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r5 = new Reading(date5, -11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);
        double expectedResult = -12;
        //Act
        LocalDate localDate = LocalDate.of(2018, 12, 21);
        double result = lr1.getMaxTempOfDay(localDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getMinTempOfDay() {
        //Arrange
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 21, 12, 32);
        Reading r3 = new Reading(date3, 14);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        double expectedResult = 12;
        //Act
        LocalDate localDate = LocalDate.of(2018, 12, 21);
        double result = lr1.getMinTempOfDay(localDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getMinTempOfDayWithOtherDay() {
        //Arrange
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, 12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, 13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 21, 12, 32);
        Reading r3 = new Reading(date3, 14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 20, 12, 32);
        Reading r4 = new Reading(date4, 15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r5 = new Reading(date5, 11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);
        double expectedResult = 12;
        //Act
        LocalDate localDate = LocalDate.of(2018, 12, 21);
        double result = lr1.getMinTempOfDay(localDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getMinTempOfDayNegativeTemperature() {
        //Arrange
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, -12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, -13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 21, 12, 32);
        Reading r3 = new Reading(date3, -14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 20, 12, 32);
        Reading r4 = new Reading(date4, -15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r5 = new Reading(date5, -11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);
        double expectedResult = -14;
        //Act
        LocalDate localDate = LocalDate.of(2018, 12, 21);
        double result = lr1.getMinTempOfDay(localDate);
        //Assert
        assertEquals(expectedResult, result);
    }


    @Test
    void getSumOfValueOfReadingsInCertainDay() {
        //Arrange
        ListOfReadings lr1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2018, 12, 21, 12, 30);
        Reading r1 = new Reading(date1, -12);
        LocalDateTime date2 = LocalDateTime.of(2018, 12, 21, 12, 31);
        Reading r2 = new Reading(date2, -13);
        LocalDateTime date3 = LocalDateTime.of(2018, 12, 21, 12, 32);
        Reading r3 = new Reading(date3, -14);
        LocalDateTime date4 = LocalDateTime.of(2018, 12, 20, 12, 32);
        Reading r4 = new Reading(date4, -15);
        LocalDateTime date5 = LocalDateTime.of(2018, 12, 22, 12, 32);
        Reading r5 = new Reading(date5, -11);
        lr1.addReading(r1);
        lr1.addReading(r2);
        lr1.addReading(r3);
        lr1.addReading(r4);
        lr1.addReading(r5);
        double expectedResult = -39;
        //Act
        LocalDate localDate = LocalDate.of(2018, 12, 21);
        double result = lr1.getSumOfValueOfReadingsInCertainDay(localDate);
        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfMaxValueInList() {

        List<Double> lDouble = new ArrayList<>();
        ListOfReadings listOfReadings = new ListOfReadings();

        lDouble.add(10d);
        lDouble.add(15d);
        lDouble.add(5d);
        lDouble.add(30d);
        lDouble.add(-2d);
        lDouble.add(15d);

        double result = listOfReadings.maxValueInList(lDouble);

        double expectedResult = 30d;

        assertEquals(expectedResult, result);
    }


    @Test
    void checkIfMaxValueInList2() {

        List<Double> lDouble = new ArrayList<>();
        ListOfReadings listOfReadings = new ListOfReadings();

        lDouble.add(30d);
        lDouble.add(15d);
        lDouble.add(5d);
        lDouble.add(29d);
        lDouble.add(-2d);
        lDouble.add(15d);

        double result = listOfReadings.maxValueInList(lDouble);

        double expectedResult = 30d;

        assertEquals(lDouble.get(0), result, 0.0001);
    }

    @Test
    void checkIfMaxValueInList3() {

        List<Double> lDouble = new ArrayList<>();
        ListOfReadings listOfReadings = new ListOfReadings();

        lDouble.add(11d);
        lDouble.add(15d);
        lDouble.add(5d);
        lDouble.add(30d);
        lDouble.add(-2d);
        lDouble.add(30d);

        double result = listOfReadings.maxValueInList(lDouble);

        double expectedResult = 30d;

        assertEquals(expectedResult, result, 0.00001);
    }

    @Test
    void checkIfMaxValueInList4() {

        List<Double> lDouble = new ArrayList<>();
        ListOfReadings listOfReadings = new ListOfReadings();

        lDouble.add(30d);
        lDouble.add(15d);
        lDouble.add(5d);
        lDouble.add(29d);
        lDouble.add(-2d);
        lDouble.add(15d);

        double result = listOfReadings.maxValueInList(lDouble);

        double expectedResult = 30d;

        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfMaxValueInList5() {

        List<Double> lDouble = new ArrayList<>();
        ListOfReadings listOfReadings = new ListOfReadings();

        lDouble.add(30d);
        lDouble.add(30d);
        lDouble.add(30d);
        lDouble.add(30d);
        lDouble.add(30d);
        lDouble.add(30d);

        double result = listOfReadings.maxValueInList(lDouble);

        double expectedResult = 30d;

        assertEquals(expectedResult, result, 0.0001);
    }


    @Test
    void checkIfMaxValueInListEmpty() {

        List<Double> lDouble = new ArrayList<>();
        ListOfReadings listOfReadings = new ListOfReadings();

        double result = listOfReadings.maxValueInList(lDouble);

        double expectedResult = Double.NaN;

        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfMinValueInListEmpty() {

        List<Double> lDouble = new ArrayList<>();
        ListOfReadings listOfReadings = new ListOfReadings();

        double result = listOfReadings.minValueInList(lDouble);

        double expectedResult = Double.NaN;

        assertEquals(expectedResult, result);
    }

    @Test
    void CheckIfGetReadingsOnInterval() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 25);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r7 = new Reading(date7, 3.5);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 21, 12, 35);
        Reading r8 = new Reading(date8, 0);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);
        lRead1.addReading(r8);

        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 6);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 22);

        List<Reading> expectedResult = new ArrayList<>();
        expectedResult.add(r3);
        expectedResult.add(r4);
        expectedResult.add(r5);

        List<Reading> result = lRead1.getReadingsOnInterval(startDate, endDate);


        assertEquals(expectedResult, result);

    }

    @Test
    void CheckIfGetReadingsOnIntervalV2() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 25);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r7 = new Reading(date7, 3.5);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 21, 12, 35);
        Reading r8 = new Reading(date8, 0);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);
        lRead1.addReading(r8);

        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 6);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 22);

        ListOfReadings expectedResult = new ListOfReadings();
        expectedResult.addReading(r3);
        expectedResult.addReading(r4);
        expectedResult.addReading(r5);

        ListOfReadings result = lRead1.getReadingsOnIntervalV2(startDate, endDate);

        List<Reading> expec = expectedResult.getListOfReading();
        List<Reading> res = result.getListOfReading();

        //assertEquals(expectedResult,result);
        assertEquals(expec, res);
    }

    @Test
    void SumReadingsOnIntervalV1() throws Exception {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 34);
        Reading r7 = new Reading(date7, 3.5);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 21, 12, 45);
        Reading r8 = new Reading(date8, 0);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);
        lRead1.addReading(r8);

        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 6);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 45);

        ListOfReadings expectedResult = new ListOfReadings();
        expectedResult.addReading(r3);
        expectedResult.addReading(r4);
        expectedResult.addReading(r5);

        double result = lRead1.sumOfReadingsOnInterval(startDate, endDate);


        assertEquals(5.9, result);
    }

    @Test
    void checkIfSumOfReadingsOnPeriod() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 20);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 34);
        Reading r7 = new Reading(date7, 3.5);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 21, 12, 45);
        Reading r8 = new Reading(date8, 2);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);
        lRead1.addReading(r8);


        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 5);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 45);


        double result = lRead1.sumOfReadingsOnPeriod(date3, date8);

        double expectedResult = r3.getValue() + r4.getValue() + r5.getValue() + r6.getValue() + r7.getValue() + r8.getValue();

        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    void checkIfSumOfReadingsOnPeriod2() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r3 = new Reading(date3, 2);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);

        double result = lRead1.sumOfReadingsOnPeriod(date1, date3);

        double expectedResult = 5.2;

        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    void checkIfSumOfReadingsOnPeriod3() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r3 = new Reading(date3, 2);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);

        double result = lRead1.sumOfReadingsOnPeriod(date1.plusNanos(1), date3);

        double expectedResult = 3.3;

        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    void checkIfSumOfReadingsOnPeriod4() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r3 = new Reading(date3, 2);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);

        double result = lRead1.sumOfReadingsOnPeriod(date1, date3.minusNanos(1));

        double expectedResult = 3.2;

        assertEquals(expectedResult, result, 0.1);
    }

    @Test
    void SumReadingsOnIntervalV2() throws Exception {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r2 = new Reading(date2, 1.3);


        lRead1.addReading(r1);
        lRead1.addReading(r2);


        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 15);


        double result = lRead1.sumOfReadingsOnInterval(startDate, endDate);


        assertEquals(1.3, result);
    }

    @Test
    void SumReadingsOnIntervalV3() throws Exception {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r2 = new Reading(date2, 1.3);


        lRead1.addReading(r1);
        lRead1.addReading(r2);


        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 16);


        double result = lRead1.sumOfReadingsOnInterval(startDate, endDate);


        assertEquals(1.3, result);
    }

    @Test
    void SumReadingsOnIntervalV4() throws Exception {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r2 = new Reading(date2, 1.3);


        lRead1.addReading(r1);
        lRead1.addReading(r2);


        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 14);


        double result = lRead1.sumOfReadingsOnInterval(startDate, endDate);


        assertEquals(0, result);
    }

    @Test
    void SumReadingsOnIntervalV5() throws Exception {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r3 = new Reading(date3, 1.3);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);


        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 30);


        double result = lRead1.sumOfReadingsOnInterval(startDate, endDate);


        assertEquals(2.6, result);
    }

    @Test
    void SumReadingsOnIntervalV6() throws Exception {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r2 = new Reading(date2, 1.3);


        lRead1.addReading(r1);
        lRead1.addReading(r2);


        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 1);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 29);


        double result = lRead1.sumOfReadingsOnInterval(startDate, endDate);


        assertEquals(0, result);
    }

    @Test
    void SumReadingsOnIntervalV7() throws Exception {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r2 = new Reading(date2, 1.3);


        lRead1.addReading(r1);
        lRead1.addReading(r2);


        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 30);


        double result = lRead1.sumOfReadingsOnInterval(startDate, endDate);


        assertEquals(1.3, result);
    }

    @Test
    void CheckIfGetReadingsOnIntervalV2_ReadingsWithSameTime() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r7 = new Reading(date7, 3.5);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 21, 12, 35);
        Reading r8 = new Reading(date8, 0);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);
        lRead1.addReading(r8);

        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 11, 0);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 1);

        ListOfReadings expectedResult = new ListOfReadings();

        LocalDateTime expectedDate1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading expectedReading = new Reading(expectedDate1, 1.9);

        expectedResult.addReading(expectedReading);
        //expectedResult.addReading(r2);
        //expectedResult.addReading(r3);
        // expectedResult.addReading(r4);
        //expectedResult.addReading(r5);
        // expectedResult.addReading(r6);
        //expectedResult.addReading(r7);

        ListOfReadings result = lRead1.getReadingsOnIntervalV2(startDate, endDate);

        List<Reading> expec = expectedResult.getListOfReading();
        List<Reading> res = result.getListOfReading();

        //assertEquals(expectedResult,result);
        assertEquals(expec.get(0).getDateTime(), res.get(0).getDateTime());
        //assertEquals(expec.get(0).getValue(),res.get(0).getValue());
    }

    @Test
    void CheckIfGetReadingsOnIntervalV3_OneReadingForEachDate_SumValuesSameDates() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r7 = new Reading(date7, 3.5);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);

        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 31);

        ListOfReadings expectedResult = new ListOfReadings();

        LocalDateTime expectedDate1 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading expectedReading1 = new Reading(expectedDate1, 1.3 + 4.1 + 3.5);
        LocalDateTime expectedDate2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading expectedReading2 = new Reading(expectedDate2, 1.9 + 2.4);
        LocalDateTime expectedDate3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading expectedReading3 = new Reading(expectedDate3, 2);

        expectedResult.addReading(expectedReading1);
        expectedResult.addReading(expectedReading2);
        expectedResult.addReading(expectedReading3);


        ListOfReadings result = lRead1.getReadingsOnIntervalCutDuplicates(startDate, endDate);

        List<Reading> expec = expectedResult.getListOfReading();
        List<Reading> res = result.getListOfReading();
/*
        for (int i=0;i< result.getListOfReadings().size();i++){
            System.out.println("Result - "+result.getListOfReadings().get(i).getDateTime()+" - Expected - "+expectedResult.getListOfReadings().get(i).getDateTime());
            System.out.println("Result - "+result.getListOfReadings().get(i).getValue()+" - Expected - "+expectedResult.getListOfReadings().get(i).getValue());

        }
*/
        //assertEquals(expectedResult,result);
        assertEquals(expec.get(2).getValue(), res.get(2).getValue());
    }

    @Test
    void CheckIfGetReadingsOnIntervalEmpty() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r7 = new Reading(date7, 3.5);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);

        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 40);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 31);

        ListOfReadings expectedResult = new ListOfReadings();

        LocalDateTime expectedDate1 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading expectedReading1 = new Reading(expectedDate1, 1.3 + 4.1 + 3.5);
        LocalDateTime expectedDate2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading expectedReading2 = new Reading(expectedDate2, 1.9 + 2.4);
        LocalDateTime expectedDate3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading expectedReading3 = new Reading(expectedDate3, 2);

        expectedResult.addReading(expectedReading1);
        expectedResult.addReading(expectedReading2);
        expectedResult.addReading(expectedReading3);


        ListOfReadings result = lRead1.getReadingsOnIntervalCutDuplicates(startDate, endDate);

        List<Reading> expec = new ArrayList<>();
        List<Reading> res = result.getListOfReading();

        for (int i = 0; i < result.getListOfReading().size(); i++) {
            System.out.println(result.getListOfReading().get(i).getDateTime());
            System.out.println(result.getListOfReading().get(i).getValue());

        }
        //assertEquals(expectedResult,result);
        assertEquals(expec, res);
    }

    @Test
    void CheckIfGetReadingsOnIntervalEmptyV2() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r7 = new Reading(date7, 3.5);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);

        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 40);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 10, 30);

        ListOfReadings expectedResult = new ListOfReadings();

        LocalDateTime expectedDate1 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading expectedReading1 = new Reading(expectedDate1, 1.3 + 4.1 + 3.5);
        LocalDateTime expectedDate2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading expectedReading2 = new Reading(expectedDate2, 1.9 + 2.4);
        LocalDateTime expectedDate3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading expectedReading3 = new Reading(expectedDate3, 2);

        expectedResult.addReading(expectedReading1);
        expectedResult.addReading(expectedReading2);
        expectedResult.addReading(expectedReading3);


        ListOfReadings result = lRead1.getReadingsOnIntervalCutDuplicates(startDate, endDate);

        List<Reading> expec = new ArrayList<>();
        List<Reading> res = result.getListOfReading();

        for (int i = 0; i < result.getListOfReading().size(); i++) {
            System.out.println(result.getListOfReading().get(i).getDateTime());
            System.out.println(result.getListOfReading().get(i).getValue());

        }
        //assertEquals(expectedResult,result);
        assertEquals(expec, res);
    }

    @Test
    void CheckIfGetReadingsOnIntervalEmptyV3() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r7 = new Reading(date7, 3.5);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);

        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 15);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 15);

        ListOfReadings expectedResult = new ListOfReadings();

        LocalDateTime expectedDate1 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading expectedReading1 = new Reading(expectedDate1, 1.3 + 4.1 + 3.5);
        LocalDateTime expectedDate2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading expectedReading2 = new Reading(expectedDate2, 1.9 + 2.4);
        LocalDateTime expectedDate3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading expectedReading3 = new Reading(expectedDate3, 2);

        expectedResult.addReading(expectedReading1);
        expectedResult.addReading(expectedReading2);
        expectedResult.addReading(expectedReading3);


        ListOfReadings result = lRead1.getReadingsOnIntervalCutDuplicates(startDate, endDate);

        List<Reading> expec = new ArrayList<>();
        List<Reading> res = result.getListOfReading();

        for (int i = 0; i < result.getListOfReading().size(); i++) {
            System.out.println(result.getListOfReading().get(i).getDateTime());
            System.out.println(result.getListOfReading().get(i).getValue());

        }
        //assertEquals(expectedResult,result);
        assertEquals(expec, res);
    }

    @Test
    void CheckIfGetReadingsOnIntervalNull() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r4 = new Reading(date4, 0);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r5 = new Reading(date5, 4.1);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading r6 = new Reading(date6, 2.4);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r7 = new Reading(date7, 3.5);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);

        LocalDateTime startDate = LocalDateTime.of(2019, 1, 21, 12, 40);
        LocalDateTime endDate = LocalDateTime.of(2019, 1, 21, 12, 31);

        ListOfReadings expectedResult = new ListOfReadings();

        LocalDateTime expectedDate1 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading expectedReading1 = new Reading(expectedDate1, 1.3 + 4.1 + 3.5);
        LocalDateTime expectedDate2 = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading expectedReading2 = new Reading(expectedDate2, 1.9 + 2.4);
        LocalDateTime expectedDate3 = LocalDateTime.of(2019, 1, 21, 12, 30);
        Reading expectedReading3 = new Reading(expectedDate3, 2);

        expectedResult.addReading(expectedReading1);
        expectedResult.addReading(expectedReading2);
        expectedResult.addReading(expectedReading3);


        ListOfReadings result = lRead1.getReadingsOnIntervalCutDuplicates(startDate, endDate);

        List<Reading> expec = new ArrayList<>();
        List<Reading> res = result.getListOfReading();


        //assertEquals(expectedResult,result);
        assertEquals(expec, res);
    }


    @Test
    void checkIfCutDuplicateReadingsSummingTheirValues() {

        ListOfReadings lRead = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 00);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r3 = new Reading(date3, 2);

        lRead.getListOfReading().add(r1);
        lRead.getListOfReading().add(r2);
        lRead.getListOfReading().add(r3);

        LocalDateTime dateTest = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading rTest = new Reading(dateTest, 3);

        ListOfReadings resultList = lRead.cutDuplicateReadingsSummingTheirValues(rTest);


        double result = resultList.getListOfReading().get(2).getValue();
        double expectedResult = 5;


        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void checkIfCutDuplicateReadingsSummingTheirValues_NoDuplicate() {

        ListOfReadings lRead = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 00);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r3 = new Reading(date3, 2);

        lRead.getListOfReading().add(r1);
        lRead.getListOfReading().add(r2);
        lRead.getListOfReading().add(r3);

        LocalDateTime dateTest = LocalDateTime.of(2019, 1, 21, 12, 15);
        Reading rTest = new Reading(dateTest, 3);

        ListOfReadings resultList = lRead.cutDuplicateReadingsSummingTheirValues(rTest);


        double result = resultList.getListOfReading().get(3).getValue();
        double expectedResult = 3;

        assertEquals(expectedResult, result, 0.001);
    }

    @Test
    void CheckIfGetReadingsToShowAsStringList() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 00);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1.3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r3 = new Reading(date3, 2);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);

        List<String[]> expectedResult = new ArrayList<>();

        String[] st1 = new String[]{date1.toString(), "1.9"};
        String[] st2 = new String[]{date2.toString(), "1.3"};
        String[] st3 = new String[]{date3.toString(), "2.0"};
        expectedResult.add(st1);
        expectedResult.add(st2);
        expectedResult.add(st3);

        List<String[]> result = lRead1.getReadingsToShowAsStringList();


        assertArrayEquals(expectedResult.get(1), result.get(1));

    }


    @Test
    void testGetLastColdestDayInGivenPeriod() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r1 = new Reading(date1, 2);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r3 = new Reading(date3, 2);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);

        Reading expectedResult = r2;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date3.toLocalDate());


        assertEquals(expectedResult, result);

    }

    @Test
    void testGetFirstHottestDayInGivenPeriod() {

        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r1 = new Reading(date1, 2);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r2 = new Reading(date2, 1);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r3 = new Reading(date3, 2);

        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);

        Reading expectedResult = r1;
        Reading result = listOfReadings.getFirstHottestDayInGivenPeriod(date1.toLocalDate(), date3.toLocalDate());

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetLastColdestDayInGivenPeriodMoreDates() {

        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r1 = new Reading(date1, 2);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 30, 12, 00);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r4 = new Reading(date4, 2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r6 = new Reading(date6, 2);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 26, 12, 10);
        Reading r7 = new Reading(date7, 3);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 25, 12, 10);
        Reading r8 = new Reading(date8, 2);
        LocalDateTime date9 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r9 = new Reading(date9, 2);
        LocalDateTime date10 = LocalDateTime.of(2019, 1, 27, 12, 10);
        Reading r10 = new Reading(date10, 2);
        LocalDateTime date11 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r11 = new Reading(date11, 2);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        lRead1.addReading(r7);
        lRead1.addReading(r8);
        lRead1.addReading(r9);
        lRead1.addReading(r10);
        lRead1.addReading(r11);


        Reading expectedResult = r3;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date3.toLocalDate());


        assertEquals(expectedResult, result);

    }

    @Test
    void testGetFirstHottestDayInGivenPeriodMoreDates() {

        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r1 = new Reading(date1, 1.9);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 3);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r3 = new Reading(date3, 1.9);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r4 = new Reading(date4, 2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r5 = new Reading(date5, 1.9);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 20, 12, 5);
        Reading r6 = new Reading(date6, 2);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 20, 12, 10);
        Reading r7 = new Reading(date7, 1.9);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 20, 12, 10);
        Reading r8 = new Reading(date8, 1.9);
        LocalDateTime date9 = LocalDateTime.of(2019, 1, 20, 12, 10);
        Reading r9 = new Reading(date9, 2);
        LocalDateTime date10 = LocalDateTime.of(2019, 1, 20, 12, 10);
        Reading r10 = new Reading(date10, 1.9);
        LocalDateTime date11 = LocalDateTime.of(2019, 1, 20, 12, 10);
        Reading r11 = new Reading(date11, 2);

        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        listOfReadings.addReading(r7);
        listOfReadings.addReading(r8);
        listOfReadings.addReading(r9);
        listOfReadings.addReading(r10);
        listOfReadings.addReading(r11);

        Reading expectedResult = r2;
        Reading result = listOfReadings.getFirstHottestDayInGivenPeriod(date1.toLocalDate(), date5.toLocalDate());

        assertEquals(expectedResult, result);

    }


    @Test
    void testGetLastColdestDayInGivenPeriodNonSelected() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 1);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 2);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r4 = new Reading(date4, 2);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);

        Reading expectedResult = r3;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date2.toLocalDate(), date4.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetFirstHottestDayInGivenPeriodSelected() {

        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 1);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 2);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r4 = new Reading(date4, 2);

        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);

        Reading expectedResult = r2;
        Reading result = listOfReadings.getFirstHottestDayInGivenPeriod(date1.toLocalDate(), date3.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetLastColdestDayEmptyPeriod() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 1);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 1.9);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1.3);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r4 = new Reading(date4, 2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 25, 12, 10);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 28, 12, 10);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);

        Reading expectedResult = null;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date5.toLocalDate(), date6.toLocalDate());

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFirstHottestDayEmptyPeriod() {

        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 1);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 1.9);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1.3);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r4 = new Reading(date4, 2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 25, 12, 10);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 28, 12, 10);

        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);

        Reading expectedResult = null;
        Reading result = listOfReadings.getFirstHottestDayInGivenPeriod(date5.toLocalDate(), date6.toLocalDate());

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetLastColdestDayNegatives() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 1);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 2);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r4 = new Reading(date4, -2);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);

        Reading expectedResult = r4;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date4.toLocalDate());

        assertEquals(expectedResult, result);

    }


    @Test
    void testGetFirstHottestDayNegatives() {

        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 1);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 2);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r4 = new Reading(date4, -2);

        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);

        Reading expectedResult = r2;

        Reading result = listOfReadings.getFirstHottestDayInGivenPeriod(date1.toLocalDate(), date4.toLocalDate());

        assertEquals(expectedResult, result);

    }


    @Test
    void testGetLastColdestDayMultiplesOnDays() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 2);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 1);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 2);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);

        Reading expectedResult = r3;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date6.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetFirstHottestDayMultiplesOnDays() {

        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 4);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 19, 12, 00);
        Reading r2 = new Reading(date2, 2);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 21, 12, 5);
        Reading r3 = new Reading(date3, 2);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 21, 12, 10);
        Reading r4 = new Reading(date4, -2);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r5 = new Reading(date5, 2);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 22, 12, 10);
        Reading r6 = new Reading(date6, 3);

        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);

        Reading expectedResult = r1;

        Reading result = listOfReadings.getFirstHottestDayInGivenPeriod(date1.toLocalDate(), date6.toLocalDate());

        assertEquals(expectedResult, result);

    }


    @Test
    void testGetLastColdestDayRegisteredOnSameDay() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 1);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 16, 12, 3);
        Reading r2 = new Reading(date2, 1.9);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 16, 12, 5);
        Reading r3 = new Reading(date3, 1.3);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 16, 12, 10);
        Reading r4 = new Reading(date4, 2);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);

        Reading expectedResult = r4;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date4.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetFirstHottestDayRegisteredOnSameDay() {

        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 16, 12, 00);
        Reading r1 = new Reading(date1, 1);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 16, 12, 3);
        Reading r2 = new Reading(date2, 1.9);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 16, 12, 5);
        Reading r3 = new Reading(date3, 1.3);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 16, 12, 10);
        Reading r4 = new Reading(date4, 2);


        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);

        Reading expectedResult = r4;

        Reading result = listOfReadings.getFirstHottestDayInGivenPeriod(date1.toLocalDate(), date4.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetLastColdestDayRegisteredOnSameDay2() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 20);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 16, 12, 3);
        Reading r2 = new Reading(date2, 21);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 18, 12, 10);
        Reading r4 = new Reading(date4, 23);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);

        Reading expectedResult = r1;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date4.toLocalDate());

        assertEquals(expectedResult, result);

    }


    @Test
    void testGetLastColdestDayRegisteredOnSameDay3() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 20);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 16, 12, 3);
        Reading r2 = new Reading(date2, 20);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 18, 12, 10);
        Reading r4 = new Reading(date4, 23);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);

        Reading expectedResult = r2;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date4.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetLastColdestDayRegisteredEqualsValues() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 20);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 16, 12, 3);
        Reading r2 = new Reading(date2, 20);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 20);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 18, 12, 10);
        Reading r4 = new Reading(date4, 20);


        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);

        Reading expectedResult = r4;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date4.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetFirstHottestDayRegisteredEqualsValues() {

        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 20);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 16, 12, 3);
        Reading r2 = new Reading(date2, 20);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 20);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 18, 12, 10);
        Reading r4 = new Reading(date4, 20);


        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);

        Reading expectedResult = r1;

        Reading result = listOfReadings.getFirstHottestDayInGivenPeriod(date1.toLocalDate(), date4.toLocalDate());

        assertEquals(expectedResult, result);

    }

    @Test
    void testGetLastColdestDayRegisteredNoReadings() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 18, 12, 10);
        Reading expectedResult = null;
        Reading result = lRead1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date4.toLocalDate());
        assertEquals(expectedResult, result);

    }


    @Test
    void testGetFirstHottestDayRegisteredNoReadings() {

        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 18, 12, 10);
        Reading expectedResult = null;
        Reading result = listOfReadings.getFirstHottestDayInGivenPeriod(date1.toLocalDate(), date4.toLocalDate());
        assertEquals(expectedResult, result);

    }


    @Test
    void testGetLastColdestDayRegisteredOnSameDay4() {
        ListOfReadings lRead1 = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 18);

        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);

        Reading expectedResult = r1;

        Reading result = lRead1.getLastColdestDayInGivenPeriod(date1.toLocalDate(), date6.toLocalDate());

        assertEquals(expectedResult, result);
    }

    @Test
    void getHighestTemperatureAmplitude() {
        //ARRANGE
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 25);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        //ACT
        double result = lRead1.getHighestTemperatureAmplitude(startDate, finalDate);
        double expectedResult = 10;
        //ASSERT
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void getHighestTemperatureAmplitudeDatesInverted() {
        //ARRANGE
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 25);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        //ACT
        double result = lRead1.getHighestTemperatureAmplitude(finalDate, startDate);
        double expectedResult = 10;
        //ASSERT
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void getHighestTemperatureAmplitudeDate() {
        //ARRANGE
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, 15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, 25);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        //ACT
        List<LocalDate> localDates;
        localDates = lRead1.getHighestTemperatureAmplitudeAndDate(startDate, finalDate);
        LocalDate expectedResult = LocalDate.of(2019, 1, 14);
        //ASSERT
        assertEquals(expectedResult, localDates.get(0));
    }

    @Test
    void getHighestTemperatureAmplitudeNegativeValues() {
        //ARRANGE
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        //ACT
        double result = lRead1.getHighestTemperatureAmplitude(startDate, finalDate);
        double expectedResult = 13;
        //ASSERT
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void getHighestTemperatureAmplitudeNegativeValuesDates() {
        //ARRANGE
        LocalDate startDate = LocalDate.of(2019, 01, 13);
        LocalDate finalDate = LocalDate.of(2019, 01, 18);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        //ACT
        List<LocalDate> localDates;
        localDates = lRead1.getHighestTemperatureAmplitudeAndDate(startDate, finalDate);
        LocalDate expectedResult = LocalDate.of(2019, 1, 14);
        //ASSERT
        assertEquals(expectedResult, localDates.get(0));
    }

    @Test
    void getHighestTemperatureAmplitudeEmpty() {
        //ARRANGE
        LocalDate startDate = LocalDate.of(2019, 01, 10);
        LocalDate finalDate = LocalDate.of(2019, 01, 12);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        //ACT
        double result = lRead1.getHighestTemperatureAmplitude(startDate, finalDate);
        double expectedResult = 0.0;
        //ASSERT
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void getHighestTemperatureAmplitudeEmptyDate() {
        //ARRANGE
        LocalDate startDate = LocalDate.of(2019, 01, 10);
        LocalDate finalDate = LocalDate.of(2019, 01, 12);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReading(r1);
        lRead1.addReading(r2);
        lRead1.addReading(r3);
        lRead1.addReading(r4);
        lRead1.addReading(r5);
        lRead1.addReading(r6);
        //ACT
        List<LocalDate> localDates;
        localDates = lRead1.getHighestTemperatureAmplitudeAndDate(startDate, finalDate);
        List<LocalDate> localDatesExpected = new ArrayList<>();
        //ASSERT
        assertEquals(localDatesExpected.isEmpty(), localDates.isEmpty());
    }


    @Test
    void testAddReadingToDeviceListOfReadingsActive() throws IOException {
        //ARRANGE
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        boolean isActive = true;
        //ACT
        listOfReadings1.addReadingToDeviceListOfReadings(r1, isActive);
        Reading result = listOfReadings1.getListOfReading().get(0);
        Reading expectedResult = r1;
        //ASSERT
        assertEquals(expectedResult, result);
    }

    @Test
    void testAddReadingToDeviceListOfReadingsNotActive() throws IOException {
        //ARRANGE
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        boolean isActive = false;
        //ACT
        listOfReadings1.addReadingToDeviceListOfReadings(r1, isActive);
        double expectedResult = 0;
        //ASSERT
        assertEquals(expectedResult, listOfReadings1.getListOfReading().size());
    }

    @Test
    void testAddReadingToDeviceListOfReadingsInvalidMinutesOfReading() throws IOException {
        //ARRANGE
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 13);
        Reading r1 = new Reading(date1, 18);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        boolean isActive = true;
        //ACT
        listOfReadings1.addReadingToDeviceListOfReadings(r1, isActive);
        double expectedResult = 0;
        //ASSERT
        assertEquals(expectedResult, listOfReadings1.getListOfReading().size());
    }

    @Test
    void testAddReadingToDeviceListOfReadingsInvalidMinutesOfReading2() throws IOException {
        //ARRANGE
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 16);
        Reading r1 = new Reading(date1, 18);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        boolean isActive = true;
        //ACT
        listOfReadings1.addReadingToDeviceListOfReadings(r1, isActive);
        double expectedResult = 0;
        //ASSERT
        assertEquals(expectedResult, listOfReadings1.getListOfReading().size());
    }


    @Test
    void testAddReadingToDeviceListOfReadingsValidMinutesOfReading() throws IOException {
        //ARRANGE
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 15);
        Reading r1 = new Reading(date1, 18);
        ListOfReadings listOfReadings1 = new ListOfReadings();
        boolean isActive = true;
        //ACT
        listOfReadings1.addReadingToDeviceListOfReadings(r1, isActive);
        Reading result = listOfReadings1.getListOfReading().get(0);
        Reading expectedResult = r1;
        //ASSERT
        assertEquals(expectedResult, result);
    }


    @Test
    void addReadingToDeviceListOfReadings() throws IOException {
        //ARRANGE
        LocalDate startDate = LocalDate.of(2019, 01, 10);
        LocalDate finalDate = LocalDate.of(2019, 01, 12);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReadingToDeviceListOfReadings(r1, true);
        lRead1.addReadingToDeviceListOfReadings(r2, true);
        //ACT
        List<LocalDate> localDates;
        localDates = lRead1.getHighestTemperatureAmplitudeAndDate(startDate, finalDate);
        List<LocalDate> localDatesExpected = new ArrayList<>();
        //ASSERT
        assertEquals(localDatesExpected.isEmpty(), localDates.isEmpty());
    }

    @Test
    void addReadingToDeviceListOfReadingsFalse() throws IOException {
        //ARRANGE
        LocalDate startDate = LocalDate.of(2019, 01, 10);
        LocalDate finalDate = LocalDate.of(2019, 01, 12);
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 17, 12, 5);
        Reading r3 = new Reading(date3, 22);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 17, 12, 6);
        Reading r4 = new Reading(date4, 23);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r5 = new Reading(date5, -15);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r6 = new Reading(date6, -2);
        ListOfReadings lRead1 = new ListOfReadings();
        lRead1.addReadingToDeviceListOfReadings(r1, false);
        lRead1.addReadingToDeviceListOfReadings(r2, false);
        //ACT
        List<LocalDate> localDates;
        localDates = lRead1.getHighestTemperatureAmplitudeAndDate(startDate, finalDate);
        List<LocalDate> localDatesExpected = new ArrayList<>();
        //ASSERT
        assertEquals(localDatesExpected.isEmpty(), localDates.isEmpty());
    }

    @Test
    void getTheOldestDateNull() {
        //ARRANGE
        ListOfReadings lRead1 = new ListOfReadings();
        //ACT
        LocalDateTime lt = lRead1.getTheOldestLocalDateTimeInListOfReading(lRead1);
        //ASSERT
        assertEquals(null, lt);
    }

    @Test
    void getTheRecentDateNull() {
        //ARRANGE
        ListOfReadings lRead1 = new ListOfReadings();
        //ACT
        LocalDateTime lt = lRead1.getMostRecentLocalDateTimeInListOfReading(lRead1);
        //ASSERT
        assertEquals(null, lt);
    }

    @Test
    void testGetReadingsOnDayLowerComfortLessThanTempMin() {
        //ARRANGE
        ListOfReadings lRead = new ListOfReadings();
        LocalDate startDate = LocalDate.of(2019, 1, 15);

        List<LocalDateTime> dateListResult;

        List<LocalDateTime> dateListExpectedResult = new ArrayList<>();

        double tempMin = 20.0;

        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);

        lRead.addReading(r1);
        lRead.addReading(r2);

        //ACT
        dateListResult = lRead.getReadingsOnDayLowerComfort(startDate, tempMin);

        dateListExpectedResult.add(date1);
        dateListExpectedResult.add(date2);

        //ASSERT
        assertEquals(dateListExpectedResult, dateListResult);
    }

    @Test
    void testGetReadingsOnDayLowerComfortMoreThanTempMin() {
        //ARRANGE
        ListOfReadings lRead = new ListOfReadings();
        LocalDate startDate = LocalDate.of(2019, 1, 15);

        List<LocalDateTime> dateListResult;

        List<LocalDateTime> dateListExpectedResult = new ArrayList<>();

        double tempMin = 10.0;

        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 18);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 15);

        lRead.addReading(r1);
        lRead.addReading(r2);

        //ACT
        dateListResult = lRead.getReadingsOnDayLowerComfort(startDate, tempMin);

        //ASSERT
        assertEquals(dateListExpectedResult, dateListResult);
    }

    @Test
    void testGetReadingsOnDayLowerComfortEqualThanTempMin() {
        //ARRANGE
        ListOfReadings lRead = new ListOfReadings();
        LocalDate startDate = LocalDate.of(2019, 1, 15);

        List<LocalDateTime> dateListResult;

        List<LocalDateTime> dateListExpectedResult = new ArrayList<>();

        double tempMin = 10.0;

        LocalDateTime date1 = LocalDateTime.of(2019, 1, 15, 12, 00);
        Reading r1 = new Reading(date1, 10);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 15, 12, 3);
        Reading r2 = new Reading(date2, 10);

        lRead.addReading(r1);
        lRead.addReading(r2);

        //ACT
        dateListResult = lRead.getReadingsOnDayLowerComfort(startDate, tempMin);

        //ASSERT
        assertEquals(dateListExpectedResult, dateListResult);
    }


    @Test
    void testGetReadingsOnDayHigherComfort() {

        LocalDate startDate = LocalDate.of(2019, 01, 14);
        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 14, 12, 0);
        Reading r1 = new Reading(date1, 15);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 14, 12, 5);
        Reading r2 = new Reading(date2, 20);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r3 = new Reading(date3, 25);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r4 = new Reading(date4, 25);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 20);
        Reading r5 = new Reading(date5, 26);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 25);
        Reading r6 = new Reading(date6, 15);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 14, 12, 30);
        Reading r7 = new Reading(date7, 31);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 14, 12, 35);
        Reading r8 = new Reading(date8, 14);

        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        listOfReadings.addReading(r7);
        listOfReadings.addReading(r8);

        List<LocalDateTime> result = listOfReadings.getReadingsOnDayHigherComfort(startDate, 20);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.add(r3.getDateTime());
        expectedResult.add(r4.getDateTime());
        expectedResult.add(r5.getDateTime());
        expectedResult.add(r7.getDateTime());

        assertEquals(expectedResult, result);
    }


    @Test
    void testGetReadingsOnDayHigherComfort1() {

        LocalDate startDate = LocalDate.of(2019, 1, 01);
        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 21, 12, 0);
        Reading r1 = new Reading(date1, 15);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 26, 12, 5);
        Reading r2 = new Reading(date2, 15);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 23, 12, 10);
        Reading r3 = new Reading(date3, 25);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 23, 12, 15);
        Reading r4 = new Reading(date4, 25);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 20);
        Reading r5 = new Reading(date5, 26);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 03, 12, 25);
        Reading r6 = new Reading(date6, 15);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 26, 12, 30);
        Reading r7 = new Reading(date7, 31);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 21, 12, 35);
        Reading r8 = new Reading(date8, 14);

        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        listOfReadings.addReading(r7);
        listOfReadings.addReading(r8);

        List<LocalDateTime> result = listOfReadings.getReadingsOnDayHigherComfort(startDate, 20);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.isEmpty();

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetReadingsOnDayHigherComfort2() {

        LocalDate startDate = LocalDate.of(2019, 1, 14);
        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 14, 12, 0);
        Reading r1 = new Reading(date1, 20);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 14, 12, 5);
        Reading r2 = new Reading(date2, 20);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r3 = new Reading(date3, 20);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r4 = new Reading(date4, 20);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 20);
        Reading r5 = new Reading(date5, 20);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 25);
        Reading r6 = new Reading(date6, 20);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 14, 12, 30);
        Reading r7 = new Reading(date7, 20);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 14, 12, 35);
        Reading r8 = new Reading(date8, 20);

        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        listOfReadings.addReading(r7);
        listOfReadings.addReading(r8);

        List<LocalDateTime> result = listOfReadings.getReadingsOnDayHigherComfort(startDate, 20);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.isEmpty();

        assertEquals(expectedResult, result);
    }


    @Test
    void testGetReadingsOnDayHigherComfort3() {

        LocalDate startDate = LocalDate.of(2019, 1, 14);
        ListOfReadings listOfReadings = new ListOfReadings();
        LocalDateTime date1 = LocalDateTime.of(2019, 1, 14, 12, 0);
        Reading r1 = new Reading(date1, 10);
        LocalDateTime date2 = LocalDateTime.of(2019, 1, 14, 12, 5);
        Reading r2 = new Reading(date2, 10);
        LocalDateTime date3 = LocalDateTime.of(2019, 1, 14, 12, 10);
        Reading r3 = new Reading(date3, 10);
        LocalDateTime date4 = LocalDateTime.of(2019, 1, 14, 12, 15);
        Reading r4 = new Reading(date4, 10);
        LocalDateTime date5 = LocalDateTime.of(2019, 1, 14, 12, 20);
        Reading r5 = new Reading(date5, 10);
        LocalDateTime date6 = LocalDateTime.of(2019, 1, 14, 12, 25);
        Reading r6 = new Reading(date6, 10);
        LocalDateTime date7 = LocalDateTime.of(2019, 1, 14, 12, 30);
        Reading r7 = new Reading(date7, 10);
        LocalDateTime date8 = LocalDateTime.of(2019, 1, 14, 12, 35);
        Reading r8 = new Reading(date8, 10);

        listOfReadings.addReading(r1);
        listOfReadings.addReading(r2);
        listOfReadings.addReading(r3);
        listOfReadings.addReading(r4);
        listOfReadings.addReading(r5);
        listOfReadings.addReading(r6);
        listOfReadings.addReading(r7);
        listOfReadings.addReading(r8);

        List<LocalDateTime> result = listOfReadings.getReadingsOnDayHigherComfort(startDate, 15);
        List<LocalDateTime> expectedResult = new ArrayList<>();
        expectedResult.isEmpty();

        assertEquals(expectedResult, result);
    }

    @Test
    void getListOfReadingsInSpecificPeriod() {
        LocalDate date1 = null;
        LocalDate date2 = LocalDate.of(2019, 1, 14);

        ListOfReadings expectedResult = new ListOfReadings();


        ListOfReadings resultList = new ListOfReadings();

        ListOfReadings result = resultList.getListOfReadingsInSpecificPeriod(date1,date2);

        assertEquals(expectedResult.getListOfReading().isEmpty(), result.getListOfReading().isEmpty());

    }

    @Test
    void getListOfReadingsInSpecificPeriodLatsCond() {
        LocalDate date1 = null;
        LocalDate date2 = LocalDate.of(2019, 1, 14);

        ListOfReadings expectedResult = new ListOfReadings();


        ListOfReadings resultList = new ListOfReadings();

        ListOfReadings result = resultList.getListOfReadingsInSpecificPeriod(date2,date1);

        assertEquals(expectedResult.getListOfReading().isEmpty(), result.getListOfReading().isEmpty());

    }

    @Test
    void getListOfReadingsInSpecificPeriodBothNull() {
        LocalDate date1 = null;
        LocalDate date2 = null;

        ListOfReadings expectedResult = new ListOfReadings();

        ListOfReadings resultList = new ListOfReadings();

        ListOfReadings result = resultList.getListOfReadingsInSpecificPeriod(date2,date1);

        assertEquals(expectedResult.getListOfReading().isEmpty(), result.getListOfReading().isEmpty());
    }


}
