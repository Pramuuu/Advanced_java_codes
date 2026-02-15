package test.com.cap;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;

import main.com.cap.calculator;

public class calculator_test {
	calculator calc;

    @Test
    public void testAdd() {
        calculator calc = new calculator();
        assertEquals(5, calc.add(2, 3));
        assertNotEquals(6, calc.add(2, 3));
    }

    @Test
    public void testSub() {
        calculator calc = new calculator();
        assertEquals(-1, calc.Sub(2, 3));
    }

    @Test
    public void testIsEven() {
        calculator calc = new calculator();
        assertTrue(calc.isEven(4));
        assertFalse(calc.isEven(5));
    }

    @Test
    public void testDivide() {
        calculator calc = new calculator();
        assertEquals(2, calc.divide(4, 2));
    }
}