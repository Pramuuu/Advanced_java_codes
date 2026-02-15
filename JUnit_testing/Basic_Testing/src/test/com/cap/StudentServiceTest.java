package test.com.cap;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.com.cap.StudentService;


public class StudentServiceTest {
	StudentService service;
	
	@BeforeEach
	void setup() {
		service=new StudentService();
	}
	@Test
	void testEligibleAge18() {
		assertTrue(service.isEligible(18));
	}
	@Test
	void testNotEligibleAge17() {
		assertFalse(service.isEligible(17));
	}
	@Test
	void testEqualsCheck() {
		assertEquals(true,service.isEligible(20));
	}
	@Test
	void testNotEqualsCheck() {
		assertNotEquals(true, service.isEligible(15));
	}
	@Test
	void testServiceNotNull() {
		assertNotNull(service);
	}
	@Test
	void testSameObject() {
		StudentService s1=service;
		assertSame(service,s1);
	}
	@Test
	void testNotSameObject() {
		StudentService s2= new StudentService();
		assertNotSame(service, s2);
	}
	@Test
	void testMultipleConditions() {
		assertAll(
			()->assertTrue(service.isEligible(18)),
			()->assertFalse(service.isEligible(10)),
			()->assertEquals(true,service.isEligible(25))
		);
	}
	@Test
	void testNegativeAgeException() {
		assertThrows(IllegalArgumentException.class,()->service.isEligible(-5));
	}
	@Test
	void testFailExample() {
		try {
			boolean result=service.isEligible(18);
			if(!result) {
				fail("Excepted eligible but got not eligible");
			}
		} catch(Exception e) {
			fail("Exception should not occur");
		}
	}
}
