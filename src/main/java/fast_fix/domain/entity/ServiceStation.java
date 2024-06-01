package fast_fix.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "service_stations")
public class ServiceStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "service_type", nullable = false)
    private String serviceType; // Тип сервиса (например, шиномонтаж, техосмотр, мойка и т.д.)

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        ServiceStation that = (ServiceStation) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(serviceType, that.serviceType) && Objects.equals(contactNumber, that.contactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, serviceType, contactNumber);
    }

    @Override
    public String toString() {
        return String.format("Service station: ID - %d, name - %s, type - %s, contact number - %s", id, name, serviceType, contactNumber);
    }
}