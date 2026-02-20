
package com.cap.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cap.StudentService;

public class StudentServiceTest {

    StudentService service = new StudentService();
    // Scenario 1: Test Grade Calculation
    @Test
    void testCalculateGrade_Distinction() {
        assertEquals("Distinction", service.calculateGrade(80));
    }
    @Test
    void testCalculateGrade_FirstClass() {
        assertEquals("First Class", service.calculateGrade(65));
    }
    @Test
    void testCalculateGrade_SecondClass() {
        assertEquals("Second Class", service.calculateGrade(55));
    }
    @Test
    void testCalculateGrade_Fail() {
        assertEquals("Fail", service.calculateGrade(40));
    }

    // Scenario 2: Test Pass/Fail Status
    @Test
    void testIsPassed_WhenMarksAbove50() {
        assertTrue(service.isPassed(75));
    }

    @Test
    void testIsPassed_WhenMarksBelow50() {
        assertFalse(service.isPassed(45));
    }
    // Scenario 3: Test Invalid Input
    @Test
    void testCalculateGrade_WhenMarksNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateGrade(-10);
        });
    }
    @Test
    void testCalculateGrade_WhenMarksAbove100() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateGrade(120);
        });
    }
    // Scenario 4: Test Non-Null Response
    @Test
    void testCalculateGrade_NotNull() {
        String result = service.calculateGrade(70);
        assertNotNull(result);
    }
}