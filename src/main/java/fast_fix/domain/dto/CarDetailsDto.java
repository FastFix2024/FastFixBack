package fast_fix.domain.dto;

import java.time.LocalDate;
import java.util.Objects;

public class CarDetailsDto {

    private Long id;
    private String fuelType;
    private LocalDate lastMaintenanceDate;
    private CarInsuranceCompanyDto insuranceCompany;

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

    public CarInsuranceCompanyDto getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(CarInsuranceCompanyDto insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDetailsDto that = (CarDetailsDto) o;
        return Objects.equals(id, that.id) && Objects.equals(fuelType, that.fuelType) && Objects.equals(lastMaintenanceDate, that.lastMaintenanceDate) && Objects.equals(insuranceCompany, that.insuranceCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fuelType, lastMaintenanceDate, insuranceCompany);
    }

    @Override
    public String toString() {
        return String.format("Car details: ID - %d, FuelType - %s, last maintenance date - %s, insurance company - %s ", id, fuelType, lastMaintenanceDate, insuranceCompany);
    }
}