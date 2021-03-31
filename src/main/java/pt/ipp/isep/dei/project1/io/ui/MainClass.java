package pt.ipp.isep.dei.project1.io.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pt.ipp.isep.dei.project1.io.ui.device.*;
import pt.ipp.isep.dei.project1.io.ui.geographicarea.*;
import pt.ipp.isep.dei.project1.io.ui.house.*;
import pt.ipp.isep.dei.project1.io.ui.sensor.*;
import pt.ipp.isep.dei.project1.io.ui.utils.Configurations;
import pt.ipp.isep.dei.project1.io.ui.utils.Validations;
import pt.ipp.isep.dei.project1.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project1.io.ui.security.User;
import pt.ipp.isep.dei.project1.io.ui.security.UserRepository;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "pt.ipp.isep.dei.project1")
@ComponentScan(basePackages = "pt.ipp.isep.dei.project1")
@EntityScan(basePackages = "pt.ipp.isep.dei.project1")
public class MainClass implements CommandLineRunner {

    private static final String MENU_INTERFACE = "------------------------------------------------------------------------------";
    private static final String BACK_TO_MAIN_MENU = "0 - Back to main menu";
    private static final String BACK_CONFIGURATIONS_MENU = "0 - Back to configurations menu";
    private static final String BACK_DATA_MENU = "0 - Back to data menu";
    @Autowired
    private CreateNewSensorAndAttachToRoomUI ui253;
    @Autowired
    private DefineNewGeographicAreaTypeUI ui01;
    @Autowired
    private PrintListOfGeographicAreaTypesUI ui02;
    @Autowired
    private SpecifyCharacteristicsThatSensorsCanReadUI ui05;
    @Autowired
    private ImportSensorsReadingsUI importSensorsReadingsUI;
    @Autowired
    private ImportGeosFromFileUIV2 ui15v2;
    @Autowired
    private ConfigureHouseFromFileUI ui100;
    @Autowired
    private ImportRoomSensorsFromFileUI ui260;
    @Autowired
    private CreateNewSensorAndAssociateToGeographicAreaUI ui06;
    @Autowired
    private CreateSaveNewGeographicAreaUI ui03;
    @Autowired
    private PrintAreasOfTheSameTypeUI ui04;
    @Autowired
    private DefineIfGeographicAreaIsInsideOtherAreaUI ui07;
    @Autowired
    private PrintInfoIfOneGeographicAreaIsInOtherUI ui08;
    @Autowired
    private DeactivateRemoveSensorInGeographicAreaUI ui10;
    @Autowired
    private AddNewRoomToTheHouseUI ui105;
    @Autowired
    private ConfigureTheLocationOfTheHouseUI ui101;
    @Autowired
    private AddNewDeviceToRoomUI usi210;
    @Autowired
    private EditConfigurationOfDeviceUI ui215;
    @Autowired
    private DeactivateRemoveDeviceFromRoomUI ui220;
    @Autowired
    private DeactivateRemoveDeviceFromRoomUI ui222;
    @Autowired
    private CreateAHouseGridUI ui130;
    @Autowired
    private ListOfDevicesInGridGroupedByTypeIncludeLocationUI ui160;
    @Autowired
    private AddANewPowerSourceToAHouseGridUI ui135;
    @Autowired
    private HaveListOfExistingRoomsAttachedToHouseGridUI ui145;
    @Autowired
    private AttachDetachRoomToHouseGridUI ui147;
    @Autowired
    private AttachDetachRoomToHouseGridUI ui149;
    @Autowired
    private GetAListOfAllSensorsInARoomSoThatICanConfigureThemUI usi250;
    @Autowired
    private GetAListOfAllDevicesInARoomToBePossibleToConfigureThemUI usi201;
    @Autowired
    private HaveAListOfExistingRoomsUI ui108;
    @Autowired
    private GetTheCurrentTemperatureInHouseAreaUI ui600;
    @Autowired
    private GetTotalAndAverageRainfallInHouseAreaForAGivenDayUI ui620;
    @Autowired
    private GetTotalAndAverageRainfallInHouseAreaForAGivenDayUI ui623;
    @Autowired
    private GetTheLastColdestDayInGivenPeriodUI ui630;
    @Autowired
    private GetTheFirstHottestDayInGivenPeriodUI ui631;
    @Autowired
    private GetTheDayWithHighestTemperatureAmplitudeUI ui633;
    @Autowired
    private GetTheCurrentTemperatureInRoomUI ui605;
    @Autowired
    private GetMaximumTemperatureInRoomUI ui610;
    @Autowired
    private TotalNominalPowerConnectedToHouseGridUI ui172;
    @Autowired
    private KnowTheTotalNominalPowerOfARoomUI ui230;
    @Autowired
    private GetNominalPowerOfRoomsOrDevicesUI ui705;
    @Autowired
    private WaterHeatersEstimatedPowerConsumptionUI ui752;
    @Autowired
    private GetDataSeriesToDesignGraphUI ui730;
    @Autowired
    private GetEnergyConsumptionUI ui720;
    @Autowired
    private GetEnergyConsumptionUI ui721;
    @Autowired
    private GetEnergyConsumptionUI ui722;
    @Autowired
    private RoomComfortLevelUI roomComfortLevelUI;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MainClass.class);
    }

    @Override
    public void run(String... args) throws Exception {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
        User regular = new User("regular", passwordEncoder.encode("regular123"), "REGULAR", "ACCESS_TEST1");
        User roomOwner = new User("roomOwner", passwordEncoder.encode("ro123"), "ROOMOWNER", "ACCESS_TEST3");


        List<User> users = Arrays.asList(admin, regular,roomOwner);
        // Save to db
        this.userRepository.saveAll(users);
/**

        int option = -1;
        while (option != 0) {
            Validations.validateFiles(Configurations.getReadingInterval());
            mainMenuInterface();
            option = Validations.verifyIntegerInputsWithLimits(0, 3);
            switch (option) {
                case 0:
                    break;
                case 1:
                    configurations();
                    break;
                case 2:
                    dataManagement();
                    break;
                default:
                    break;
            }
        }
**/
    }
/**
    private void mainMenuInterface() {
        System.out.println(MENU_INTERFACE);
        System.out.println(MENU_INTERFACE);
        System.out.println("------------------------------ Main Menu -------------------------------------");
        System.out.println(MENU_INTERFACE);
        System.out.println(MENU_INTERFACE);
        System.out.println("1) Configurations");
        System.out.println("2) Data Management");
        System.out.println("0 - Exit");
    }

 **/

    public void configurations() throws Exception {
        int option1 = -1;
        while (option1 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------------ Configurations Menu ---------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Geographic Area");
            System.out.println("2) Sensor");
            System.out.println("3) Device");
            System.out.println("4) House Grid");
            System.out.println("5) Room");
            System.out.println("6) General Options");
            System.out.println(BACK_TO_MAIN_MENU);
            option1 = Validations.verifyIntegerInputsWithLimits(0, 6);
            switch (option1) {
                case 0:
                    break;
                case 1:
                    geographicAreaMenu();
                    break;
                case 2:
                    sensorMenu();
                    break;
                case 3:
                    deviceMenu();
                    break;
                case 4:
                    houseGridMenu();
                    break;
                case 5:
                    roomMenu();
                    break;
                case 6:
                    generalMenu();
                    break;
                default:
                    break;
            }
        }
    }

    public void dataManagement() throws Exception {
        int option2 = -1;
        while (option2 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------------ Data Management Menu --------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Weather");
            System.out.println("2) Home Environment");
            System.out.println("3) Power");
            System.out.println("4) Energy");
            System.out.println(BACK_TO_MAIN_MENU);
            option2 = Validations.verifyIntegerInputsWithLimits(0, 4);
            switch (option2) {
                case 0:
                    break;
                case 1:
                    weatherMenu();
                    break;
                case 2:
                    homeEnvironmentMenu();
                    break;
                case 3:
                    powerMenu();
                    break;
                case 4:
                    energyMenu();
                    break;
                default:
                    break;
            }
        }
    }

    public void geographicAreaMenu() {
        int option3 = -1;
        while (option3 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("----------------- Geographic Area Configurations Menu ------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Define a new geographic area type");
            System.out.println("2) Print a list of geographic area types");
            System.out.println("3) Create a new geographic area");
            System.out.println("4) Print areas of the same type");
            System.out.println("5) Define if one geographic area is inside other area");
            System.out.println("6) Print info if one geographic area is(direct or indirect) in other area");
            System.out.println("7) Import geographical areas and it's sensors from a JSON or a XML file");
            System.out.println(BACK_CONFIGURATIONS_MENU);
            option3 = Validations.verifyIntegerInputsWithLimits(0, 7);
            switch (option3) {
                case 0:
                    break;
                case 1:
                    ui01.run();
                    break;
                case 2:
                    ui02.run();
                    break;
                case 3:
                    ui03.run();
                    break;
                case 4:
                    ui04.run();
                    break;
                case 5:
                    ui07.run();
                    break;
                case 6:
                    ui08.run();
                    break;
                case 7:
                    ui15v2.run();
                    break;
                default:
                    break;
            }
        }

    }

    public void sensorMenu() throws Exception {
        int option4 = -1;
        while (option4 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------- Sensor Configurations Menu -------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Specify the characteristics that sensors can read");
            System.out.println("2) Create a new sensor and associate to a following geographic area");
            System.out.println("3) Create a new sensor and attach to a room");
            System.out.println("4) Deactivate sensor from a geographic area");
            System.out.println("5) Remove sensor from a geographic area");
            System.out.println("6) Import geographical areas sensor(s) reading(s) into the application from a file");
            System.out.println("7) Import room sensor(s) reading(s) into the application from a file");

            System.out.println(BACK_CONFIGURATIONS_MENU);
            option4 = Validations.verifyIntegerInputsWithLimits(0, 7);
            switch (option4) {
                case 0:
                    break;
                case 1:
                    ui05.run();
                    break;
                case 2:
                    ui06.run();
                    break;
                case 3:
                    ui253.run();
                    break;
                case 4:
                    ui10.runDeactivation();
                    break;
                case 5:
                    ui10.runRemoval();
                    break;
                case 6:
                    importSensorsReadingsUI.run(6);
                    break;
                case 7:
                    importSensorsReadingsUI.run(7);
                    break;
                default:
                    break;
            }
        }
    }

    public void deviceMenu() throws Exception {
        int option5 = -1;
        while (option5 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------- Device Configurations Menu -------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Add a new device to room");
            System.out.println("2) Edit the configuration of an existing device");
            System.out.println("3) Remove a device from a room");
            System.out.println("4) Deactivate a device");
            System.out.println(BACK_CONFIGURATIONS_MENU);
            option5 = Validations.verifyIntegerInputsWithLimits(0, 4);
            switch (option5) {
                case 0:
                    break;
                case 1:
                    usi210.run();
                    break;
                case 2:
                    ui215.run();
                    break;
                case 3:
                    ui220.run();
                    break;
                case 4:
                    ui222.run222();
                    break;
                default:
                    break;
            }
        }
    }

    public void houseGridMenu() {
        int option6 = -1;
        while (option6 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------------ House Grid Menu -------------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Create a house grid");
            System.out.println("2) Get a list of all devices in a house grid, grouped by device type");
            System.out.println("3) Add a power source to a house grid");
            System.out.println("4) Have a list of existing rooms attached to a house grid");
            System.out.println("5) Attach a room to a house grid");
            System.out.println("6) Detach a room from a house grid");
            System.out.println(BACK_CONFIGURATIONS_MENU);
            option6 = Validations.verifyIntegerInputsWithLimits(0, 6);
            switch (option6) {
                case 0:
                    break;
                case 1:
                    ui130.run();
                    break;
                case 2:
                    ui160.run();
                    break;
                case 3:
                    ui135.run();
                    break;
                case 4:
                    ui145.run();
                    break;
                case 5:
                    ui147.runAttach();
                    break;
                case 6:
                    ui149.runDetach();
                    break;
                default:
                    break;
            }
        }
    }

    public void roomMenu() {
        int option7 = -1;
        while (option7 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------------ Room Menu -------------------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Add a new room to the house, in order to configure it (name, house floor and dimensions)");
            System.out.println("2) Get a list of all sensors in a room, so that i can configure them");
            System.out.println("3) Get a list of all devices in a room, so that i can configure them");
            System.out.println("4) Have a list of existing rooms, so that i can choose one to edit it");
            System.out.println("5) Import sensor(s) to room(s) from file");
            System.out.println(BACK_CONFIGURATIONS_MENU);
            option7 = Validations.verifyIntegerInputsWithLimits(0, 5);
            switch (option7) {
                case 0:
                    break;
                case 1:
                    ui105.run();
                    break;
                case 2:
                    usi250.run();
                    break;
                case 3:
                    usi201.run();
                    break;
                case 4:
                    ui108.run();
                    break;
                case 5:
                    ui260.run();
                    break;
                default:
                    break;
            }
        }
    }

    public void generalMenu() {
        int option8 = -1;
        while (option8 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------------ General Options Menu --------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Configure the house from a file");
            System.out.println("2) Configure the location of the house");
            System.out.println(BACK_CONFIGURATIONS_MENU);
            option8 = Validations.verifyIntegerInputsWithLimits(0, 2);
            switch (option8) {
                case 0:
                    break;
                case 1:
                    ui100.run();
                    break;
                case 2:
                    ui101.run();
                    break;
                default:
                    break;
            }
        }
    }

    public void weatherMenu() {
        int option9 = -1;
        while (option9 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------- Weather Menu ---------------------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Get the current temperature in the house area using the nearest sensor");
            System.out.println("2) Get the total rainfall in the house area for a given day");
            System.out.println("3) Get the average daily rainfall in the house area for a given period (days)");
            System.out.println("4) Get the last coldest day (lower maximum temperature) in the house area in a given period");
            System.out.println("5) Get the first hottest day (higher maximum temperature) in the house area in a given period");
            System.out.println("6) Get the day with the highest temperature amplitude in the house area in a given period");
            System.out.println(BACK_DATA_MENU);
            option9 = Validations.verifyIntegerInputsWithLimits(0, 6);
            switch (option9) {
                case 0:
                    break;
                case 1:
                    ui600.run();
                    break;
                case 2:
                    ui620.runTotal();
                    break;
                case 3:
                    ui623.runAverage();
                    break;
                case 4:
                    ui630.run();
                    break;
                case 5:
                    ui631.run();
                    break;
                case 6:
                    ui633.run();
                    break;
                default:
                    break;
            }
        }
    }

    public void homeEnvironmentMenu() {
        int option10 = -1;
        while (option10 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------- Home Environment Menu ------------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Get the current temperature in a room, in order to check if meets my personal comfort requirements");
            System.out.println("2) Get the maximum temperature in a room in a" +
                    "given day, in order to check if heating/cooling in that room was effective");
            System.out.println("3) I want to have a list of the instants in which the temperature fell below the comfort " +
                    "level in a given time interval and category (annex A.2 of EN 15251)");
            System.out.println("4) I want to have a list of the instants in which the temperature fell above the comfort " +
                    "level in a given time interval and category (annex A.2 of EN 15251)");
            System.out.println(BACK_DATA_MENU);
            option10 = Validations.verifyIntegerInputsWithLimits(0, 4);
            switch (option10) {
                case 0:
                    break;
                case 1:
                    ui605.run();
                    break;
                case 2:
                    ui610.run();
                    break;
                case 3:
                    roomComfortLevelUI.runUS440(RoomSensor.LOWER);
                    break;
                case 4:
                    roomComfortLevelUI.runUS440(RoomSensor.HIGHER);
                    break;
                default:
                    break;
            }
        }
    }

    public void powerMenu() {
        int option11 = -1;
        while (option11 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------- Power Menu -----------------------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Get the total nominal power connected to a house grid");
            System.out.println("2) Get the total nominal power of a room");
            System.out.println("3) Get the total nominal power of rooms or devices");
            System.out.println(BACK_DATA_MENU);
            option11 = Validations.verifyIntegerInputsWithLimits(0, 3);
            switch (option11) {
                case 0:
                    break;
                case 1:
                    ui172.run();
                    break;
                case 2:
                    ui230.run();
                    break;
                case 3:
                    ui705.run();
                    break;
                default:
                    break;
            }
        }
    }

    public void energyMenu() throws Exception {
        int option12 = -1;
        while (option12 != 0) {
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("------------------------- Energy menu ----------------------------------------");
            System.out.println(MENU_INTERFACE);
            System.out.println(MENU_INTERFACE);
            System.out.println("1) Estimate the total energy used in heating water in a given day");
            System.out.println("2) Have the data series necessary to design an energy consumption chart" +
                    " of the metered energy consumption of a device/room/grid in a given time interval");
            System.out.println("3) Have the total energy consumption of a device");
            System.out.println("4) Have the total energy consumption of a room");
            System.out.println("5) Have the total energy consumption of a grid");
            System.out.println(BACK_DATA_MENU);
            option12 = Validations.verifyIntegerInputsWithLimits(0, 5);
            switch (option12) {
                case 0:
                    break;
                case 1:
                    ui752.run();
                    break;
                case 2:
                    ui730.run();
                    break;
                case 3:
                    ui720.runUS720();
                    break;
                case 4:
                    ui721.runUS721();
                    break;
                case 5:
                    ui722.runUS722();
                    break;
                default:
                    break;
            }
        }
    }

/**
    @Bean
    public ServletWebServerFactory servletContainer() {
        // Enable SSL Trafic
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        // Add HTTP to HTTPS redirect
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

        return tomcat;
    }

**/

    //We need to redirect from HTTP to HTTPS. Without SSL, this application used
    //port 8082. With SSL it will use port 8443. So, any request for 8082 needs to be
    //redirected to HTTPS on 8443.

/**
    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8082);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
 **/

}



