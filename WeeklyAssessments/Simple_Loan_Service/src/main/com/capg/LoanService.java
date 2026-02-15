package main.com.capg;

public class LoanService {
	public boolean isEligible(int age, double salary) {
		return age>=21 && age<=60 && salary>25000;
	}
	public double calculateEMI(double loanAmount, int tenureYears) {
		if(loanAmount<=0) {
			throw new IllegalArgumentException("Loan amount should be positive.... not negative nor zero");
		}
		if(tenureYears<=0) {
			throw new IllegalArgumentException("Tenure years should be positive... not negative nor zero");
		}
		return loanAmount/(tenureYears*12);
	}
	public String getLoanCategory(int creditScore) {
		if(creditScore>=750) {
			return "Premium";
		} else if (creditScore>=600) {
			return "Standard";
		} else {
			return "High risk";
		}
	}

}
