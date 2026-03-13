import java.util.Date;

class Passenger {
    private String name;
    private String passportNumber;
    private Date dateOfBirth;

    public Passenger(String name, String passportNumber, Date dateOfBirth) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}