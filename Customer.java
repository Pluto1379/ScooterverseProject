public class Customer {
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String username;
    private boolean admin;

    public User(String first_name, String last_name, String license_number, String username, boolean admin) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.licenseNumber = license_number;
        this.username = username;
        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getUsername() {
        return username;
    }

    public boolean getAdmin() {
        return admin;
    }
}
