package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testConstructorAndGetters() {
        User user = new User(1, "John Doe", "john.doe@example.com", "EMPLOYEE", "password123");

        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("EMPLOYEE", user.getRole());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testSetters() {
        User user = new User();

        user.setId(2);
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setRole("CUSTOMER");
        user.setPassword("securePassword");

        assertEquals(2, user.getId());
        assertEquals("Jane Doe", user.getName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("CUSTOMER", user.getRole());
        assertEquals("securePassword", user.getPassword());
    }

    @Test
    void testIsEmployee() {
        User employee = new User(1, "Employee", "employee@example.com", "EMPLOYEE", "password123");
        User customer = new User(2, "Customer", "customer@example.com", "CUSTOMER", "password456");

        assertTrue(employee.isEmployee());
        assertFalse(customer.isEmployee());
    }

    @Test
    void testEqualsAndHashCode() {
        User user1 = new User(1, "John Doe", "john.doe@example.com", "EMPLOYEE", "password123");
        User user2 = new User(1, "John Doe", "different.email@example.com", "EMPLOYEE", "differentPassword");
        User user3 = new User(2, "Jane Doe", "jane.doe@example.com", "CUSTOMER", "password456");

        assertEquals(user1, user2); // Powinny być równe na podstawie id i name
        assertNotEquals(user1, user3); // Powinny być różne
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
}
