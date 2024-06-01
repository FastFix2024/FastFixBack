package fast_fix.domain.dto;

import java.util.Objects;

public class EmergencyContactDto {

    private Long id;
    private String phoneNumber;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmergencyContactDto that = (EmergencyContactDto) o;
        return Objects.equals(id, that.id) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, name);
    }

    @Override
    public String toString() {
        return String.format("Emergency contact: ID - %d, name - %s, phone number %s]", id, name, phoneNumber);
    }
}