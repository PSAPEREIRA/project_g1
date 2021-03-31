package pt.ipp.isep.dei.project1.model.sensor;

import lombok.Getter;
import lombok.Setter;
import pt.ipp.isep.dei.project1.io.ui.utils.Configurations;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Getter
@Setter
@Embeddable
@Table(name = "ReadingList")
public class ListOfReadings {

    @ElementCollection(fetch = FetchType.EAGER)
    @Embedded
    private List<Reading> listOfReading;


    /**
     * Builder
     */

    public ListOfReadings() {
        this.listOfReading = new ArrayList<>();
    }

    /**
     * Getter
     *
     * @return
     */

    @Override
    public String toString() {
        return listOfReading + "\n";
    }


    /**
     * Method to add Readings
     *
     * @param reading
     */

    public void addReading(Reading reading) {
        listOfReading.add(reading);
    }

    /**
     * Method to get the Last Reading
     *
     * @return
     */


    /**
     * Method to get readings within the time range defined by configuration file
     *
     * @param reading
     */

    public void addReadingToDeviceListOfReadings(Reading reading, boolean isActive) throws IOException {
        if (isActive) {
            int minutesDefinedByConfigurationFileToDevice;
            minutesDefinedByConfigurationFileToDevice = Configurations.getReadingInterval()[0];
            int minutesOfReading = reading.getDateTime().getMinute();
            if (minutesOfReading % minutesDefinedByConfigurationFileToDevice == 0)
                listOfReading.add(reading);
        }
    }

    public void addReadingToGridListOfReadings(Reading reading) throws IOException {
        int minutesDefinedByConfigurationFileToGrid;
        minutesDefinedByConfigurationFileToGrid = Configurations.getReadingInterval()[1];
        int minutesOfReading = reading.getDateTime().getMinute();
        if (minutesOfReading % minutesDefinedByConfigurationFileToGrid == 0)
            listOfReading.add(reading);
    }

    public double getLastReading() {

        return getListOfReading().get(getListOfReading().size() - 1).getValue();
    }

    public LocalDateTime getLastReadingDate() {

        return getListOfReading().get(getListOfReading().size() - 1).getDateTime();
    }

    /**
     * Method to get the Maximum of Temperature of Day
     *
     * @param date
     * @return
     */

    public double getMaxTempOfDay(LocalDate date) {
        List<Double> listOfTodayReadings = new ArrayList<>();
        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++) {
            if (listOfReading.get(readingIndex).getDateTime().toLocalDate().equals(date)) {
                listOfTodayReadings.add(listOfReading.get(readingIndex).getValue());
            }
        }
        return maxValueInList(listOfTodayReadings);
    }


    public double getMinTempOfDay(LocalDate date) {
        List<Double> listOfTodayReadings = new ArrayList<>();
        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++) {
            if (listOfReading.get(readingIndex).getDateTime().toLocalDate().equals(date)) {
                listOfTodayReadings.add(listOfReading.get(readingIndex).getValue());
            }
        }
        return minValueInList(listOfTodayReadings);
    }


    /**
     * Method to get summation of values of Readings in Certain Day
     *
     * @param dateInput
     * @return
     */

    public double getSumOfValueOfReadingsInCertainDay(LocalDate dateInput) {
        double sum = 0;
        double count;

        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++) {
            if (listOfReading.get(readingIndex).isValueOfDay(dateInput)) {
                count = listOfReading.get(readingIndex).getValue();
                sum += count;
            }
        }
        return sum;
    }

    /**
     * Method to Check If Have Readings in Certain Day
     *
     * @param dateInput
     * @return
     */

    public boolean checkIfHaveReadingsInCertainDay(LocalDate dateInput) {
        int count = 0;

        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++) {
            if (listOfReading.get(readingIndex).isValueOfDay(dateInput))
                count++;
        }
        return count > 1;
    }

    /**
     * Method to Confirm if List of Readings is Empty
     *
     * @return
     */

    public boolean checkIfListOfReadingsIsEmpty() {
        return (listOfReading.isEmpty());
    }


    public double sumOfReadingsOnInterval(LocalDateTime initialDate, LocalDateTime finalDate) throws Exception {
        double sum = 0;
        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++) {
            if (listOfReading.get(readingIndex).getDateTime().isAfter(initialDate) && listOfReading.get(readingIndex).getDateTime().isBefore(finalDate.plus(1, ChronoUnit.MINUTES))
                    && Math.abs(listOfReading.get(readingIndex).getDateTime().until(initialDate, ChronoUnit.MINUTES)) >= Configurations.getReadingInterval()[0]) {
                sum += listOfReading.get(readingIndex).getValue();
            }
        }
        return sum;
    }

    public double sumOfReadingsOnPeriod(LocalDateTime periodStart, LocalDateTime periodEnd) {
        double sum = 0;
        for (int i = 0; i < listOfReading.size(); i++) {
            if (listOfReading.get(i).getDateTime().plusNanos(1).isAfter(periodStart) && listOfReading.get(i).getDateTime().minusNanos(1).isBefore(periodEnd)) {
                sum = sum + listOfReading.get(i).getValue();
            }
        }
        return sum;
    }

    public List<Reading> getReadingsOnInterval(LocalDateTime startDate1, LocalDateTime endDate) {
        List<Reading> readingList = new ArrayList<>();
        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++) {
            if (listOfReading.get(readingIndex).getDateTime().isAfter(startDate1) && listOfReading.get(readingIndex).getDateTime().isBefore(endDate))
                readingList.add(listOfReading.get(readingIndex));
        }

        return readingList;
    }


    public List<LocalDateTime> getReadingsOnDayHigherComfort(LocalDate startDate1, double tempMax) {
        List<LocalDateTime> readingList = new ArrayList<>();
        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++)
            if (listOfReading.get(readingIndex).getDateTime().toLocalDate().isEqual(startDate1)
                    && (listOfReading.get(readingIndex).getValue() > tempMax))
                readingList.add(listOfReading.get(readingIndex).getDateTime());
        return readingList;
    }


    public List<LocalDateTime> getReadingsOnDayLowerComfort(LocalDate startDate1, double tempMin) {
        List<LocalDateTime> readingList = new ArrayList<>();
        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++)
            if (listOfReading.get(readingIndex).getDateTime().toLocalDate().isEqual(startDate1)
                    && (listOfReading.get(readingIndex).getValue() < tempMin))
                readingList.add(listOfReading.get(readingIndex).getDateTime());
        return readingList;
    }


    public ListOfReadings getReadingsOnIntervalV2(LocalDateTime startDate, LocalDateTime endDate) {
        ListOfReadings lRead = new ListOfReadings();
        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++) {
            if (listOfReading.get(readingIndex).getDateTime().isAfter(startDate) && listOfReading.get(readingIndex).getDateTime().isBefore(endDate))
                lRead.addReading(listOfReading.get(readingIndex));
        }

        return lRead;
    }

    public ListOfReadings getReadingsOnIntervalCutDuplicates(LocalDateTime startDate2, LocalDateTime endDate) {
        ListOfReadings lRead = new ListOfReadings();
        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++) {
            if (listOfReading.get(readingIndex).getDateTime().isAfter(startDate2) && listOfReading.get(readingIndex).getDateTime().isBefore(endDate.plus(1, ChronoUnit.MINUTES))) {
                lRead.cutDuplicateReadingsSummingTheirValues(listOfReading.get(readingIndex));
            }
        }

        lRead.getListOfReading().sort(Comparator.comparing(Reading::getDateTime));
        return lRead;
    }

    public ListOfReadings cutDuplicateReadingsSummingTheirValues(Reading r1) {
        ListOfReadings lRead = new ListOfReadings();
        lRead.setListOfReading(listOfReading);
        boolean check = true;

        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++) {
            if (listOfReading.get(readingIndex).getDateTime().equals(r1.getDateTime())) {
                listOfReading.get(readingIndex).setValue(listOfReading.get(readingIndex).getValue() + r1.getValue());
                check = false;
            }
        }
        if (check) {
            lRead.addReading(r1);
        }

        return lRead;
    }

    public List<String[]> getReadingsToShowAsStringList() {
        List<String[]> list = new ArrayList<>();

        for (int readingIndex = 0; readingIndex < listOfReading.size(); readingIndex++) {
            list.add(new String[]{listOfReading.get(readingIndex).getDateAsString(), String.valueOf(listOfReading.get(readingIndex).getValue())});
        }

        return list;
    }


    /**
     * Method to Get The Maximum Values
     **/


    /**
     * Method to get the first hottest day in given period
     **/

    public Reading getFirstHottestDayInGivenPeriod(LocalDate initialDate, LocalDate finalDate) {
        ListOfReadings listOfReadingsInSpecificPeriod = getListOfReadingsInSpecificPeriod(initialDate, finalDate);
        if (listOfReadingsInSpecificPeriod.listOfReading.isEmpty())
            return null;
        double maxValue = getMaxValueInListOfDays(listOfReadingsInSpecificPeriod);
        ListOfReadings listOfMaxValues = getListOfMaximumValues(listOfReadingsInSpecificPeriod, maxValue);
        LocalDateTime oldestLocalDateTimeInListOfReading = getTheOldestLocalDateTimeInListOfReading(listOfMaxValues);
        return getReadingInSpecificLocalDateTime(listOfMaxValues, oldestLocalDateTimeInListOfReading);
    }


    public Reading getLastColdestDayInGivenPeriod(LocalDate initDate, LocalDate finalDate) {
        ListOfReadings listOfReadingsInSpecificPeriod = getListOfReadingsInSpecificPeriod(initDate, finalDate);
        if (listOfReadingsInSpecificPeriod.listOfReading.isEmpty())
            return null;
        double minValue = getMinValueInListOfMaximumOfDays(listOfReadingsInSpecificPeriod);
        ListOfReadings listOfMinValues = getListOfMinValues(listOfReadingsInSpecificPeriod, minValue);
        LocalDateTime mostRecentLocalDateTimeInListOfReading = getMostRecentLocalDateTimeInListOfReading(listOfMinValues);
        return getReadingInSpecificLocalDateTime(listOfMinValues, mostRecentLocalDateTimeInListOfReading);
    }


    public ListOfReadings getListOfReadingsInSpecificPeriod(LocalDate initDate, LocalDate finalDate) {
        LocalDate initDate1 = initDate;
        LocalDate finalDate1 = finalDate;

        if (initDate1 == null || finalDate1 == null) {
            return new ListOfReadings();
        }

        if (initDate1.isAfter(finalDate1)) {
            initDate1 = finalDate;
            finalDate1 = initDate;
        }
        ListOfReadings listOfReadings = new ListOfReadings();
        for (Reading r : listOfReading) {
            if (r.checkIfDateIsBefore(finalDate1.plusDays(1)) && r.checkIfDateIsAfter(initDate1.minusDays(1)))
                listOfReadings.addReading(r);
        }
        return listOfReadings;
    }


    public double getMinValueInListOfMaximumOfDays(ListOfReadings listOfReadings) {
        List<LocalDate> localDates = new ArrayList<>();
        List<Double> list = new ArrayList<>();
        double maxValue;
        for (Reading r : listOfReadings.listOfReading) {
            if (!localDates.contains(r.getDateTime().toLocalDate())) {
                localDates.add(r.getDateTime().toLocalDate());
                maxValue = getMaxTempOfDay(r.getDateTime().toLocalDate());
                list.add(maxValue);
            }

        }
        return minValueInList(list);
    }

    /**
     * Method to get the Maximum Value In List Of Days
     **/

    public double getMaxValueInListOfDays(ListOfReadings listOfReadings) {
        List<LocalDate> localDates = new ArrayList<>();
        List<Double> list = new ArrayList<>();
        double maximumValue;
        for (Reading reading : listOfReadings.listOfReading) {
            if (!localDates.contains(reading.getDateTime().toLocalDate())) {
                localDates.add(reading.getDateTime().toLocalDate());
                maximumValue = getMaxTempOfDay(reading.getDateTime().toLocalDate());
                list.add(maximumValue);
            }
        }
        return maxValueInList(list);
    }

    public ListOfReadings getListOfMaximumValues(ListOfReadings listOfReadings, double maximumValue) {
        ListOfReadings listOfReadingsOfMaxValue = new ListOfReadings();
        double maxValue;
        for (Reading r : listOfReadings.listOfReading) {
            maxValue = getMaxTempOfDay(r.getDateTime().toLocalDate());
            if (Double.compare(r.getValue(), maxValue) == 0 && Double.compare(r.getValue(), maximumValue) == 0)
                listOfReadingsOfMaxValue.listOfReading.add(r);
        }
        return listOfReadingsOfMaxValue;
    }


    public ListOfReadings getListOfMinValues(ListOfReadings listOfReadings, double minValue) {
        ListOfReadings listOfReadings1 = new ListOfReadings();
        double maxValue;
        for (Reading r : listOfReadings.listOfReading) {
            maxValue = getMaxTempOfDay(r.getDateTime().toLocalDate());
            if (Double.compare(r.getValue(), maxValue) == 0 && Double.compare(r.getValue(), minValue) == 0)
                listOfReadings1.listOfReading.add(r);
        }

        return listOfReadings1;
    }

    public LocalDateTime getMostRecentLocalDateTimeInListOfReading(ListOfReadings listOfReadings) {
        List<LocalDateTime> localDates = new ArrayList<>();
        for (Reading r : listOfReadings.listOfReading)
            localDates.add(r.getDateTime());
        if (localDates.isEmpty())
            return null;
        return Collections.max(localDates);

    }

    public LocalDateTime getTheOldestLocalDateTimeInListOfReading(ListOfReadings listOfReadings) {
        List<LocalDateTime> localDates = new ArrayList<>();
        for (Reading r : listOfReadings.getListOfReading())
            localDates.add(r.getDateTime());
        if (localDates.isEmpty())
            return null;
        return Collections.min(localDates);

    }


    public Reading getReadingInSpecificLocalDateTime(ListOfReadings listOfReadings, LocalDateTime localDateTime) {
        Reading reading = new Reading();
        for (Reading r : listOfReadings.getListOfReading())
            if (r.getDateTime().equals(localDateTime))
                reading = r;
        return reading;
    }


    public double getHighestTemperatureAmplitude(LocalDate startDate, LocalDate finalDate) {
        List<Double> amplitudeList = new ArrayList<>();
        LocalDate start = startDate;
        LocalDate end = finalDate;
        if (start.isAfter(end)) {
            start = finalDate;
            end = startDate;
        }
        while (start.isBefore(end.plusDays(1))) {
            amplitudeList.add(Math.abs(getMaxTempOfDay(start) - getMinTempOfDay(start)));
            start = start.plusDays(1);
        }
        for (int i = 0; i < amplitudeList.size(); i++) {
            if (amplitudeList.get(i).isNaN())
                amplitudeList.set(i, 0.0);
            start = start.plusDays(1);
        }
        return maxValueInList(amplitudeList);
    }

    public List<LocalDate> getHighestTemperatureAmplitudeAndDate(LocalDate startDate, LocalDate finalDate) {
        List<LocalDate> dateList = new ArrayList<>();
        double maxAmplitude = getHighestTemperatureAmplitude(startDate, finalDate);
        LocalDate start = startDate;
        while (start.isBefore(finalDate.plusDays(1))) {
            double result = Math.abs(getMaxTempOfDay(start) - getMinTempOfDay(start));
            if (Double.compare(maxAmplitude, result) == 0)
                dateList.add(start);
            start = start.plusDays(1);
        }
        return dateList;
    }

    /**
     * @param listOfValues
     * @return
     */

    public double maxValueInList(List<Double> listOfValues) {
        if (listOfValues.isEmpty())
            return Double.NaN;


/**        DecimalFormat df = new DecimalFormat();
//        df.setMaximumFractionDigits(2);
//        String s = df.format(result);
//        System.out.println("double" +result);
//        System.out.println("string"+s);
//        result = Double.valueOf(df.format(result));**/

        return Collections.max(listOfValues);
    }

    /**
     * @param listOfValues
     * @return
     */

    public double minValueInList(List<Double> listOfValues) {
        if (listOfValues.isEmpty())
            return Double.NaN;
        return Collections.min(listOfValues);
    }


}
