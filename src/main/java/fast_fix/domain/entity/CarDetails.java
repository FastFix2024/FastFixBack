package fast_fix.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

@Schema(description = "Car Details")
@Entity
@Table(name = "car_details")
public class CarDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Schema(description = "Car fuel type")
    @ManyToOne
    @JoinColumn(name = "fuel_type_id")
    private FuelType fuelType;

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

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
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
    public String toString() {
        return String.format("Car details: ID - %d, FuelType - %s, last maintenance date - %s, insurance company - %s ", id, fuelType, lastMaintenanceDate, insuranceCompany);
    }
}