package fast_fix.domain.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ServiceStationDto {

    private String id;
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String serviceType;
    private String contactNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceStationDto that = (ServiceStationDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(serviceType, that.serviceType) && Objects.equals(contactNumber, that.contactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, latitude, longitude, serviceType, contactNumber);
    }

    @Override
    public String toString() {
        return String.format("Service station: ID - %s, name - %s, type - %s, contact number - %s", id, name, serviceType, contactNumber);
    }
}