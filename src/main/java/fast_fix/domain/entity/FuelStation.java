package fast_fix.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "fuel_stations")
public class FuelStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String street;

    @Column(name = "latitude")
    private double lat;

    @Column(name = "longitude")
    private double lng;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelStation that = (FuelStation) o;
        return Double.compare(lat, that.lat) == 0 && Double.compare(lng, that.lng) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, lat, lng);
    }

    @Override
    public String toString() {
        return "FuelStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
