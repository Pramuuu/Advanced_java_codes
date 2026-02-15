package test.com.capg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.com.capg.LoanService;



public class LoanServiceTest {
	private LoanService loanService;
	
	@BeforeEach
	void setup() {
		loanService=new LoanService();
	}
	@Test
	void testValidEligibility() {
		assertTrue(loanService.isEligible(30,45000));
	}
	@Test
	void testInvalidAgeLower() {
		assertFalse(loanService.isEligible(20,45000));
	}
	@Test
	void testInvalidAgeUpper() {
		assertFalse(loanService.isEligible(61,45000));
	}
	@Test
	void testInvalidsalary() {
		assertFalse(loanService.isEligible(30,20000));
	}
	@Test
	void testValidEMI() {
		double emi=loanService.calculateEMI(14000, 2);
		assertEquals(5000,emi);
	}
	@Test 
	void InvalidLoanAmount(){
		assertThrows(IllegalArgumentException.class,()->{
			loanService.calculateEMI(0,2);
		});
	}
	@Test
    void testInvalidTenure() {
        assertThrows(IllegalArgumentException.class, () -> {
            loanService.calculateEMI(100000, 0);
        });
    }
	@Test
	void testPremiumCategory() {
		assertEquals("Premium",loanService.getLoanCategory(800));
	}
	@Test
	void testStandardCategory() {
		assertEquals("Standard",loanService.getLoanCategory(650));
	}
	@Test
	void testHighRisk() {
		assertEquals("High Risk",loanService.getLoanCategory(550));
	}
	@Test
    void testBoundaryValuesUsingAssertAll() {
		assertAll("Boundary Tests",
				()->assertTrue(loanService.isEligible(21,25000)),
				()->assertTrue(loanService.isEligible(60,25000)),
				()->assertEquals("Premium",loanService.getLoanCategory(750)),
				()->assertEquals("Standard", loanService.getLoanCategory(600)),
				()->assertNotNull(loanService.getLoanCategory(500)));
	}

}
