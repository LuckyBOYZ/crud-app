package pl.lukaszsuma.app.crudapp.dto;

public class PatientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long pesel;

    public PatientDto(Long id, String firstName, String lastName, Long pesel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getPesel() {
        return pesel;
    }

    @Override
    public String toString() {
        return "PatientDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel=" + pesel +
                '}';
    }
}
