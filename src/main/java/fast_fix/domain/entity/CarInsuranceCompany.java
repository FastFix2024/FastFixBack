package fast_fix.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "insurance_companies")
public class CarInsuranceCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "website", nullable = false)
    private String website;

    @OneToMany(mappedBy = "insuranceCompany")
    private Set<CarDetails> carDetails;

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

    public Set<CarDetails> getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(Set<CarDetails> carDetails) {
        this.carDetails = carDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInsuranceCompany that = (CarInsuranceCompany) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(website, that.website) && Objects.equals(carDetails, that.carDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, website, carDetails);
    }

    @Override
    public String toString() {
        return String.format("Car insurance company: ID - %d, name - %s, phone number - %s, website - %s, car details - %s", id, name, phoneNumber, website, carDetails);
    }
}