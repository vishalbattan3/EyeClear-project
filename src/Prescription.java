import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float cylinder;
    private float axis;
    private Date examinationDate;
    private String optometrist;
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Constructor
    public Prescription(int id, String firstName, String lastName, String address, 
                        float sphere, float cylinder, float axis, Date examinationDate, 
                        String optometrist) {
        this.prescID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.cylinder = cylinder;
        this.axis = axis;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    // Method to add prescription details to a file
    public boolean addPrescription() {
        if (firstName.length() >= 4 && firstName.length() <= 15 &&
            lastName.length() >= 4 && lastName.length() <= 15 &&
            address.length() >= 20 &&
            sphere >= -20.00 && sphere <= 20.00 &&
            cylinder >= -4.00 && cylinder <= 4.00 &&
            axis >= 0 && axis <= 180 &&
            optometrist.length() >= 8 && optometrist.length() <= 25) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String formattedDate = dateFormat.format(examinationDate);

            try (FileWriter writer = new FileWriter("presc.txt", true)) {
                writer.write("Prescription ID: " + prescID + "\n");
                writer.write("First Name: " + firstName + "\n");
                writer.write("Last Name: " + lastName + "\n");
                writer.write("Address: " + address + "\n");
                writer.write("Sphere: " + sphere + "\n");
                writer.write("Cylinder: " + cylinder + "\n");
                writer.write("Axis: " + axis + "\n");
                writer.write("Examination Date: " + formattedDate + "\n");
                writer.write("Optometrist: " + optometrist + "\n\n");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    // Method to add remarks to a file
    public boolean addRemark(String remarkText, String remarkType) {
        if ((remarkType.equalsIgnoreCase("Client") || remarkType.equalsIgnoreCase("Optometrist")) &&
            postRemarks.size() < 2) {

            String[] words = remarkText.trim().split("\\s+");

            // Allow fewer words (e.g., minimum 3 words instead of 6)
            if (words.length >= 3 && words.length <= 20 && Character.isUpperCase(words[0].charAt(0))) {
                postRemarks.add(remarkText);
                try (FileWriter writer = new FileWriter("remark.txt", true)) {
                    writer.write("Prescription ID: " + prescID + "\n");
                    writer.write("Remark: " + remarkText + "\n");
                    writer.write("Type: " + remarkType + "\n\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    // Main method for manual testing
    public static void main(String[] args) {
        // Example prescription
        Prescription prescription = new Prescription(1, "Alice", "Smith", 
                                                     "123 Main Street, Suburb, 12345, Australia", 
                                                     2.50f, -1.75f, 90, 
                                                     new Date(), "Dr. Johnson");

        // Add the prescription
        if (prescription.addPrescription()) {
            System.out.println("Prescription added successfully!");
        } else {
            System.out.println("Failed to add prescription.");
        }

        // Add a remark with fewer words (4 words in this case)
        if (prescription.addRemark("Valid short remark.", "Client")) {
            System.out.println("Remark added successfully!");
        } else {
            System.out.println("Failed to add remark.");
        }
    }
}
