package controller;

import model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("controller")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerFacadeTest {

    private ControllerFacade controllerFacade;
    private ModelFacade modelFacadeMock;
    private UserController userControllerMock;
    private RentController rentControllerMock;

    @BeforeEach
    void setUp() {
        modelFacadeMock = mock(ModelFacade.class);
        userControllerMock = mock(UserController.class);
        rentControllerMock = mock(RentController.class);
        controllerFacade = new ControllerFacade(modelFacadeMock, userControllerMock, rentControllerMock);
    }

    @Test
    @Order(1)
    @Tag("functional")
    void testRegisterUser() {
        when(userControllerMock.registerUser(1, "John", "john@example.com", "customer", "password"))
                .thenReturn(true);

        boolean result = controllerFacade.registerUser(1, "John", "john@example.com", "customer", "password");

        assertTrue(result, "Rejestracja użytkownika powinna się powieść.");
        verify(userControllerMock).registerUser(1, "John", "john@example.com", "customer", "password");
    }

    @Test
    @Order(2)
    @Tag("functional")
    void testLoginUser() {
        User mockUser = new User(1, "John", "john@example.com", "customer", "password");
        when(userControllerMock.loginUser(1, "password")).thenReturn(mockUser);

        User result = controllerFacade.loginUser(1, "password");

        assertEquals(mockUser, result, "Powinien zostać zwrócony zalogowany użytkownik.");
        verify(userControllerMock).loginUser(1, "password");
    }

    @ParameterizedTest
    @CsvSource({
            "1, Bike, Mountain bike, 50.0, 10",
            "2, Tent, Camping tent, 100.0, 5"
    })
    @Order(3)
    @Tag("functional")
    void testAddBikeParameterized(int id, String name, String type, double price, int stock) {
        when(modelFacadeMock.addBike(name, type, price, id, stock)).thenReturn(true);

        boolean result = controllerFacade.addBike(name, type, price, id, stock);

        assertTrue(result, "Dodanie roweru powinno zakończyć się sukcesem.");
        verify(modelFacadeMock).addBike(name, type, price, id, stock);
    }

    @Test
    @Order(4)
    @Tag("functional")
    void testHandleBlockEquipment() {
        when(modelFacadeMock.blockEquipment(1)).thenReturn(true);

        boolean result = controllerFacade.handleBlockEquipment(1);

        assertTrue(result, "Zablokowanie sprzętu powinno zakończyć się sukcesem.");
        verify(modelFacadeMock).blockEquipment(1);
    }

    @Test
    @Order(5)
    @Tag("functional")
    void testHandleRepairEquipment() {
        when(modelFacadeMock.repairEquipment(1, "Naprawiono")).thenReturn(true);

        boolean result = controllerFacade.handleRepairEquipment(1, "Naprawiono");

        assertTrue(result, "Naprawa sprzętu powinna zakończyć się sukcesem.");
        verify(modelFacadeMock).repairEquipment(1, "Naprawiono");
    }

    @Test
    @Order(6)
    @Tag("integration")
    void testGetAvailableEquipment() {
        List<Equipment> mockEquipmentList = List.of(
                new Equipment("Bike", "Mountain bike", 50.0, 10)
        );
        when(modelFacadeMock.getAvailableEquipment()).thenReturn(mockEquipmentList);

        List<Equipment> result = controllerFacade.getAvailableEquipment();

        assertEquals(mockEquipmentList, result, "Lista dostępnego sprzętu powinna być poprawna.");
        verify(modelFacadeMock).getAvailableEquipment();
    }

    @Test
    @Order(7)
    @Tag("exception")
    void testRentEquipmentInvalidData() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() - 86400000L); // Data zakończenia przed rozpoczęciem

        when(rentControllerMock.rentEquipment(eq(1), eq(startDate), eq(endDate))).thenThrow(new IllegalArgumentException("Invalid rental period"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controllerFacade.rentEquipment(1, startDate, endDate);
        });

        assertEquals("Invalid rental period", exception.getMessage());
        verify(rentControllerMock).rentEquipment(eq(1), eq(startDate), eq(endDate));
    }
}
