package model;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RentalServiceTest {

    @Mocked
    private LocalStorage storage;

    @Mocked
    private UserService userService;

    @Mocked
    private EquipmentService equipmentService;

    @Mocked
    private RentalFactory regularRentalFactory;

    @Mocked
    private RentalFactory discountedRentalFactory;

    @Mocked
    private RegularCostStrategy regularCostStrategy;

    private RentalService rentalService;

    @BeforeEach
    void setUp() {
        // Inicjalizacja rzeczywistej instancji LocalStorage i innych usług
        storage = new LocalStorage();
        storage.getUsers().clear();
        storage.getRentals().clear();
        storage.getEquipments().clear();

        userService = new UserService(storage);
        equipmentService = new EquipmentService(storage, new RegularEquipmentFactory(), new SportEquipmentFactory());
        rentalService = new RentalService(storage, userService, new RegularRentalFactory(),
                new EmployeeRentalFactory(), equipmentService);
    }


    @Test
    void testCheckAndExtendRentalWithCollision() {
        Equipment equipment = new Equipment("Tent", "Camping Tent", 30.0, 5);
        Date startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        Date endDate = calendar.getTime();

        Rental rental = new Rental(1, 1, equipment, startDate, endDate, regularCostStrategy);

        // Tworzymy wypożyczenie kolidujące
        Rental conflictingRental = new Rental(2, 2, equipment,
                new Date(endDate.getTime() - 86400000L), // Dzień przed końcem `rental`
                new Date(endDate.getTime() + 86400000L), // Dzień po końcu `rental`
                regularCostStrategy);

        new Expectations() {{
            storage.getRentals();
            result = Arrays.asList(rental, conflictingRental);
        }};

        RentalService rentalService = new RentalService(storage, userService, regularRentalFactory,
                discountedRentalFactory, equipmentService);

        boolean result = rentalService.checkAndExtendRental(1, 3);

        assertFalse(result, "Przedłużenie wypożyczenia powinno się nie udać z powodu kolizji");
    }



    @Test
    void testCheckAndExtendRentalWithNonExistingUser() {
        Equipment equipment = new Equipment("Tent", "Camping Tent", 30.0, 5);
        Date startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        Date endDate = calendar.getTime();

        Rental rental = new Rental(1, 1, equipment, startDate, endDate, regularCostStrategy);

        new Expectations() {{
            storage.getRentals();
            result = Collections.singletonList(rental);

            userService.getUserById(1);
            result = null; // Użytkownik nie istnieje
        }};

        RentalService rentalService = new RentalService(storage, userService, regularRentalFactory,
                discountedRentalFactory, equipmentService);

        assertThrows(IllegalStateException.class, () -> rentalService.checkAndExtendRental(1, 3),
                "Powinno rzucić wyjątek, gdy użytkownik nie istnieje");
    }

    @Test
    void testCheckAndExtendRentalWithInvalidRentalId() {
        new Expectations() {{
            storage.getRentals();
            result = Collections.emptyList(); // Brak wypożyczeń
        }};

        RentalService rentalService = new RentalService(storage, userService, regularRentalFactory,
                discountedRentalFactory, equipmentService);

        assertThrows(IllegalArgumentException.class, () -> rentalService.checkAndExtendRental(999, 3),
                "Powinno rzucić wyjątek, gdy podane ID wypożyczenia nie istnieje");
    }



    @Test
    void testRentEquipment() {
        // Prepare test data
        User loggedUser = new User(1, "John Doe", "john.doe@example.com", "REGULAR", "password123");
        Equipment equipment = new Equipment("Tent", "Camping Tent", 30.0, 5);
        List<Rental> rentals = new ArrayList<>();

        new Expectations() {{
            // Mock getting logged user
            storage.getLoggedUser();
            result = loggedUser;

            // Mock getting equipment by ID
            storage.getEquipmentById(1);
            result = equipment;

            // Mock getting rentals list
            storage.getRentals();
            result = rentals;
            minTimes = 0; // Because it might be called multiple times

            // Mock equipment availability check
            equipment.isAvailable();
            result = true;

            // Don't mock the factory creation since it's created directly in the method
        }};

        // Create RentalService instance
        RentalService rentalService = new RentalService(storage, userService, regularRentalFactory,
                discountedRentalFactory, equipmentService);

        // Test dates
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 86400000); // Next day

        // Execute the method
        boolean result = rentalService.rentEquipment(1, startDate, endDate);

        // Assert the result
        assertTrue(result, "rentEquipment should return true on successful rental");

        // Verify method calls
        new Verifications() {{
            // Verify getLoggedUser was called
            storage.getLoggedUser();
            times = 1;

            // Verify equipment retrieval
            storage.getEquipmentById(1);
            times = 1;

            // Verify equipment availability check
            equipment.isAvailable();
            times = 1;

            // Verify equipment availability was set to false
            equipment.setAvailable(false);
            times = 1;

            // Verify rentals list was accessed
            storage.getRentals();
            minTimes = 1;
        }};
    }


    @Test
    void testGetUserRentalHistory() {
        Rental rental1 = new Rental(1, 1, null, new Date(), new Date(), regularCostStrategy);
        Rental rental2 = new Rental(2, 1, null, new Date(), new Date(), regularCostStrategy);
        Rental rental3 = new Rental(3, 2, null, new Date(), new Date(), regularCostStrategy);

        new Expectations() {{
            storage.getRentals();
            result = Arrays.asList(rental1, rental2);
        }};

        RentalService rentalService = new RentalService(storage, userService, regularRentalFactory, discountedRentalFactory, equipmentService);

        List<Rental> history = rentalService.getUserRentalHistory(1);
        assertEquals(2, history.size());
    }

    @Test
    void testCalculateRentalCost() {
        Equipment equipment = new Equipment("Bike", "Mountain Bike", 50.0, 10);
        Rental rental = new Rental(0, 1, equipment, new Date(), new Date(), regularCostStrategy);

        new Expectations() {{
            // Zwracanie sprzętu na podstawie ID
            equipmentService.getEquipmentById(1);
            result = new Equipment("Bike", "Mountain Bike", 50.0, 10);

            // Mockowanie dostępności sprzętu
            equipmentService.getEquipmentById(1).isAvailable();
            result = true;

            // Zwracanie zalogowanego użytkownika
            userService.getLoggedUser();
            result = new User(1, "John Doe", "john.doe@example.com", "EMPLOYEE", "password123");

            // Tworzenie wypożyczenia w fabryce
            discountedRentalFactory.createRental(0, 1, (Equipment) any, (Date) any, (Date) any);
            result = new Rental(0, 1, new Equipment("Bike", "Mountain Bike", 50.0, 10), new Date(), new Date(), regularCostStrategy);

            // Obliczanie kosztu wypożyczenia
            rental.calculateCost();
            result = 100.0;
        }};

        RentalService rentalService = new RentalService(storage, userService, regularRentalFactory, discountedRentalFactory, equipmentService);

        double cost = rentalService.calculateRentalCost(1, new Date(), new Date());
        assertEquals(100.0, cost);
    }



    @Test
    void testCheckCollision() {
        Rental rental1 = new Rental(1, 1, new Equipment("Tent", "Camping Tent", 30.0, 5), new Date(), new Date(), regularCostStrategy);
        Rental rental2 = new Rental(2, 2, rental1.getEquipment(), new Date(), new Date(), regularCostStrategy);

        new Expectations() {{
            storage.getRentals();
            result = Arrays.asList(rental1, rental2);
        }};

        RentalService rentalService = new RentalService(storage, userService, regularRentalFactory, discountedRentalFactory, equipmentService);

        boolean collision = rentalService.checkCollision(rental1, new Date());
        assertFalse(collision);
    }

    // Additional test for when user is not logged in
    @Test
    void testRentEquipmentWithNoLoggedUser() {
        new Expectations() {{
            storage.getLoggedUser();
            result = null;
        }};

        RentalService rentalService = new RentalService(storage, userService, regularRentalFactory,
                discountedRentalFactory, equipmentService);

        assertThrows(IllegalStateException.class, () ->
                        rentalService.rentEquipment(1, new Date(), new Date()),
                "Should throw IllegalStateException when no user is logged in"
        );
    }

    @Test
    void testRentEquipmentWithUnavailableEquipment() {
        // Prepare test data
        User loggedUser = new User(1, "John Doe", "john.doe@example.com", "EMPLOYEE", "password123");
        Equipment equipment = new Equipment("Tent", "Camping Tent", 30.0, 5);
        equipment.setAvailable(false); // Set equipment as unavailable

        RentalService rentalService = new RentalService(storage, userService, regularRentalFactory,
                discountedRentalFactory, equipmentService);

        new Expectations() {{
            // Must record getLoggedUser() before it's called
            storage.getLoggedUser();
            result = loggedUser;

            // Fix the getEquipmentById expectation
            storage.getEquipmentById(anyInt); // or specifically (1)
            result = equipment; // Return the Equipment object, not a boolean
        }};

        // Test the exception
        assertThrows(IllegalArgumentException.class, () ->
                        rentalService.rentEquipment(1, new Date(), new Date()),
                "Should throw IllegalArgumentException when equipment is unavailable"
        );

        new Verifications() {{
            storage.getLoggedUser(); times = 1;
            storage.getEquipmentById(1); times = 1;
        }};
    }

}
