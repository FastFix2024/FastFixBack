package fast_fix.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Set;

@Schema(description = "Insurance Companies")
@Entity
@Table(name = "insurance_companies")
public class CarInsuranceCompany {

    @Schema(description = "Insurance company ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Schema(description = "Insurance company Name")
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(description = "Insurance company Phone number")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Schema(description = "Insurance company Web site")
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
    public String toString() {
        return String.format("Car insurance company: ID - %d, name - %s, phone number - %s, website - %s", id, name, phoneNumber, website);
    }
}