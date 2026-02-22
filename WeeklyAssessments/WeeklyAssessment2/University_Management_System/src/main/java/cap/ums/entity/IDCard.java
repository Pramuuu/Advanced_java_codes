package cap.ums.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class IDCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String cardNumber;
	
	@OneToOne(mappedBy="idCard")
	private Student student;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	

}
