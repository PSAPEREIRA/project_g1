package pt.ipp.isep.dei.project1.io.ui.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class Configurations {

    private static final String EXCEPTION = "Exception: ";

    private Configurations() {
    }

    public static int[] getReadingInterval() throws IOException {

        InputStream inputStream = null;
        int[] result = new int[2];

        try {
            Properties prop = new Properties();
            String propFileName = "readingTime.properties";

            inputStream = Configurations.class.getClassLoader().getResourceAsStream(propFileName);

            checkPropertyNameInPatch(prop, inputStream);

            String valueDevice = prop.getProperty("deviceReadings");
            String valueGrid = prop.getProperty("gridReadings");
            result[0] = Integer.parseInt(valueDevice);
            result[1] = Integer.parseInt(valueGrid);


        } catch (Exception e) {
            System.out.println(EXCEPTION + e);
        } finally {
            if (inputStream != null)
                inputStream.close();
        }
        return result;
    }


    public static List<String> readListOfDeviceTypes() throws IOException {
        List<String> devicesTypes = new ArrayList<>();
        String propFileName = "config.properties";
        addProperties(propFileName, devicesTypes);
        return devicesTypes;
    }

    public static List<String> readListOfFileReadersOfSensorReadings() throws IOException {
        List<String> readers = new ArrayList<>();
        String propFileName = "reader.properties";
        addProperties(propFileName, readers);
        return readers;
    }


    public static void addProperties(String propFileName, List<String> readers) throws IOException {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            inputStream = Configurations.class.getClassLoader().getResourceAsStream(propFileName);
            checkPropertyNameInPatch(prop, inputStream);
            String total = prop.getProperty("type.count");
            int totalInt = Integer.parseInt(total);

            for (int i = 1; i <= totalInt; i++)
                readers.add(prop.getProperty("type." + i));
        } catch (Exception e) {
            System.out.println(EXCEPTION + e);
        } finally {
            if (inputStream != null)
                inputStream.close();
        }
    }

    public static List<String> importPackFromProperties(String fileName) throws IOException {
        List<String> readers = new ArrayList<>();

        InputStream inputStream = null;

        try {
            String propFileName = fileName + ".properties";
            Properties prop = new Properties();

            inputStream = Configurations.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the path");
            }

            String total = prop.getProperty("type.count");
            int totalInt = Integer.parseInt(total);

            for (int i = 1; i <= totalInt; i++)
                readers.add(prop.getProperty("type." + i));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                inputStream.close();
        }
        return readers;
    }

    public static void checkPropertyNameInPatch(Properties prop, InputStream inputStream) throws IOException {
        if (inputStream != null) {
            prop.load(inputStream);
        }
    }
}