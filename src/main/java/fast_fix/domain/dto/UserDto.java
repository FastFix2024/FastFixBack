package fast_fix.domain.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class UserDto {

    private Long id;
    private String username;
    private String email;
    private CarDetailsDto carDetails;
    private boolean isActive;
    private BigDecimal lat;
    private BigDecimal lng;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CarDetailsDto getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetailsDto carDetails) {
        this.carDetails = carDetails;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return isActive == userDto.isActive && Objects.equals(id, userDto.id) && Objects.equals(username, userDto.username) && Objects.equals(email, userDto.email) && Objects.equals(carDetails, userDto.carDetails) && Objects.equals(lat, userDto.lat) && Objects.equals(lng, userDto.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, carDetails, isActive, lat, lng);
    }

    @Override
    public String toString() {
        return String.format("User: ID - %d, username - %s, email - %s", id, username, email);
    }
}