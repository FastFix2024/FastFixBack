package fast_fix.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Schema(description = "Car Details")
@Entity
@Table(name = "car_details")
public class CarDetails {

    @Schema(description = "CarDetails ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Schema(description = "Car fuel type")
    @Column(name = "fuel_type")
    private String fuelType;

    @Schema(description = "Car maintenance date")
    @Column(name = "last_maintenance_date")
    private LocalDate lastMaintenanceDate;

    @Schema(description = "Car owner")
    @OneToOne(mappedBy = "carDetails")
    private User user;

    @Schema(description = "Car Insurance company")
    @ManyToOne
    @JoinColumn(name = "insurance_company_id")
    private CarInsuranceCompany insuranceCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarInsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(CarInsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDetails that = (CarDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(fuelType, that.fuelType) && Objects.equals(lastMaintenanceDate, that.lastMaintenanceDate) && Objects.equals(user, that.user) && Objects.equals(insuranceCompany, that.insuranceCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fuelType, lastMaintenanceDate, user, insuranceCompany);
    }

    @Override
    public String toString() {
        return String.format("Car details: ID - %d, FuelType - %s, last maintenance date - %s, insurance company - %s ", id, fuelType, lastMaintenanceDate, insuranceCompany);
    }
}