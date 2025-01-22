package model;


import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(OrderAnnotation.class)
class CostStrategyTest {

    @Test
    @Order(1)
    void testRegularCostStrategy() {
        RegularCostStrategy strategy = new RegularCostStrategy();
        double cost = strategy.calculateCost(50, 5);
        assertEquals(250.0, cost);
    }

    @Test
    @Order(2)
    void testEmployeeDiscountCostStrategy() {
        EmployeeDiscountCostStrategy strategy = new EmployeeDiscountCostStrategy();
        double cost = strategy.calculateCost(50, 5);
        assertEquals(150.0, cost); // znizka 40%
    }
}
