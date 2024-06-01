package fast_fix.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "fuel_stations")
public class FuelStation {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "lat", nullable = false)
    private BigDecimal lat;

    @Column(name = "lng", nullable = false)
    private BigDecimal lng;

    @Column(name = "dist", nullable = false)
    private BigDecimal dist;

    @Column(name = "diesel", nullable = false)
    private BigDecimal diesel;

    @Column(name = "e5", nullable = false)
    private BigDecimal e5;

    @Column(name = "e10", nullable = false)
    private BigDecimal e10;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "post_code", nullable = false)
    private int postCode;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getDist() {
        return dist;
    }

    public void setDist(BigDecimal dist) {
        this.dist = dist;
    }

    public BigDecimal getDiesel() {
        return diesel;
    }

    public void setDiesel(BigDecimal diesel) {
        this.diesel = diesel;
    }

    public BigDecimal getE5() {
        return e5;
    }

    public void setE5(BigDecimal e5) {
        this.e5 = e5;
    }

    public BigDecimal getE10() {
        return e10;
    }

    public void setE10(BigDecimal e10) {
        this.e10 = e10;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelStation that = (FuelStation) o;
        return isOpen == that.isOpen && postCode == that.postCode && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(brand, that.brand) && Objects.equals(street, that.street) && Objects.equals(place, that.place) && Objects.equals(lat, that.lat) && Objects.equals(lng, that.lng) && Objects.equals(dist, that.dist) && Objects.equals(diesel, that.diesel) && Objects.equals(e5, that.e5) && Objects.equals(e10, that.e10) && Objects.equals(houseNumber, that.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, street, place, lat, lng, dist, diesel, e5, e10, isOpen, houseNumber, postCode);
    }

    @Override
    public String toString() {
        return String.format("Fuel station: ID - %s, name - %s, brand - %s, street - %s, place - %s, houseNumber - %s, postCode - %s, diesel - %f.2, e5 - %f.2, e10 - %f.2,", id, name, brand, street, place, houseNumber, postCode, diesel, e5, e10);
    }
}