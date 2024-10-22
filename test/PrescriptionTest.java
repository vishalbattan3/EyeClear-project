import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class PrescriptionTest {
    private Prescription prescription;

    @BeforeEach
    public void setUp() {
        prescription = new Prescription(1, "Alice", "Smith", 
                                        "123 Main Street, Suburb, 12345, Australia", 
                                        2.50f, -1.75f, 90, 
                                        new Date(), "Dr. Johnson");
    }

    @Test
    public void testAddPrescription_ValidData() {
        assertTrue(prescription.addPrescription());
    }

    @Test
    public void testAddPrescription_InvalidName() {
        Prescription invalidPrescription = new Prescription(2, "Al", "Smith", 
                                                            "123 Main Street, Suburb, 12345, Australia", 
                                                            2.50f, -1.75f, 90, 
                                                            new Date(), "Dr. Johnson");
        assertFalse(invalidPrescription.addPrescription());
    }

    @Test
    public void testAddRemark_ValidRemark() {
        assertTrue(prescription.addRemark("This is a valid remark.", "Client"));
    }

    @Test
    public void testAddRemark_InvalidRemarkText() {
        assertFalse(prescription.addRemark("invalid.", "Client"));
    }

    @Test
    public void testAddRemark_InvalidRemarkType() {
        assertFalse(prescription.addRemark("This is a valid remark.", "Doctor"));
    }
}
