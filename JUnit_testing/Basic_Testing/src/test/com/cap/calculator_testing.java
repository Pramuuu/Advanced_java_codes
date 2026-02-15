package test.com.cap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import main.com.cap.calculator;

public class calculator_testing {

    calculator c;

    @BeforeEach
    void setup() {
        c = new calculator();
    }

    // CsvSource
    @ParameterizedTest
    @CsvSource({
        "2,3,5",
        "0,0,0",
        "-5,10,5",
        "-100,200,100"
    })
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, c.add(a, b));
    }

    //  ValueSource
    @ParameterizedTest
    @ValueSource(ints = {2,4,6,8,10,0,-2,-4})
    void testIsEvenReturnsTrue(int number) {
        assertTrue(c.isEven(number));
    }

    //  MethodSource
    @ParameterizedTest
    @MethodSource("providedDivisionTestCases")
    void testDivision(int a, int b, int expected) {
        assertEquals(expected, c.divide(a, b));
    }

    private static Stream<Arguments> providedDivisionTestCases() {
        return Stream.of(
            Arguments.of(20, 4, 5),
            Arguments.of(10, 2, 5),
            Arguments.of(9, 3, 3)
        );
    }

    //  CsvFileSource
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/add.csv", numLinesToSkip = 1)
    void simpleAddTest(int a, int b, int expected) {
        assertEquals(expected, c.add(a, b));
    }
}