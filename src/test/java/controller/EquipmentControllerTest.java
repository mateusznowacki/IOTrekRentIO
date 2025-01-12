package controller;

import model.ModelFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipmentControllerTest {

    private EquipmentController equipmentController;
    private ModelFacade modelFacadeMock;

    @BeforeEach
    void setUp() {
        // Tworzenie mocka dla ModelFacade
        modelFacadeMock = mock(ModelFacade.class);
        equipmentController = new EquipmentController(modelFacadeMock);
    }

    @Test
    void testAddEquipment() {
        // Mockowanie metody addEquipment w modelFacade
        when(modelFacadeMock.addEquipment("Bike", "Mountain bike", 50.0, 10))
                .thenReturn("Dodano sprzęt: Bike");

        // Testowanie metody addEquipment
        String result = equipmentController.addEquipment("Bike", "Mountain bike", 50.0, 10);

        // Weryfikacja
        assertEquals("Dodano sprzęt: Bike", result, "Wynik metody addEquipment powinien być poprawny.");
        verify(modelFacadeMock).addEquipment("Bike", "Mountain bike", 50.0, 10);
    }

    @Test
    void testRemoveEquipment() {
        // Mockowanie metody removeEquipment w modelFacade
        when(modelFacadeMock.removeEquipment(1)).thenReturn(true);

        // Testowanie metody removeEquipment
        boolean result = equipmentController.removeEquipment(1);

        // Weryfikacja
        assertTrue(result, "Sprzęt powinien zostać usunięty pomyślnie.");
        verify(modelFacadeMock).removeEquipment(1);
    }

    @Test
    void testValidateEquipmentDataValid() {
        // Testowanie poprawnych danych
        assertTrue(equipmentController.validateEquipmentData("Bike", "Mountain bike", 50.0, 10),
                "Dane sprzętu powinny być poprawne.");
    }

    @Test
    void testValidateEquipmentDataInvalid() {
        // Testowanie nieprawidłowych danych
        assertFalse(equipmentController.validateEquipmentData(null, "Mountain bike", 50.0, 10),
                "Nazwa sprzętu nie może być null.");
        assertFalse(equipmentController.validateEquipmentData("Bike", "", 50.0, 10),
                "Opis sprzętu nie może być pusty.");
        assertFalse(equipmentController.validateEquipmentData("Bike", "Mountain bike", -1, 10),
                "Cena za dzień nie może być ujemna.");
        assertFalse(equipmentController.validateEquipmentData("Bike", "Mountain bike", 50.0, -5),
                "Ilość sprzętu nie może być ujemna.");
    }
}
