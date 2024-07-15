package com.aircjm.java.base.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {
        Calculator calculator = new Calculator();
        double add = calculator.add(1, 2);
        assertEquals(3, add);
    }
}
