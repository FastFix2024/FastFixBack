package fast_fix.domain.dto;

import java.util.Objects;
import java.util.Set;

public class CarInsuranceCompanyDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String website;
    private Set<CarDetailsDto> carDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<CarDetailsDto> getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(Set<CarDetailsDto> carDetails) {
        this.carDetails = carDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInsuranceCompanyDto that = (CarInsuranceCompanyDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, website);
    }

    @Override
    public String toString() {
        return String.format("Car insurance company: ID - %d, name - %s, phone number - %s, website - %s", id, name, phoneNumber, website);
    }
}