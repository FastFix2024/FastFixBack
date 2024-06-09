package fast_fix.domain.dto;

import java.util.Objects;

public class UserDto {

    private Long id;
    private String username;
    private String email;
    private CarDetailsDto carDetails;
    private boolean isConfirmed = false;

    public UserDto() {
    }

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

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return isConfirmed == userDto.isConfirmed && Objects.equals(id, userDto.id) && Objects.equals(username, userDto.username) && Objects.equals(email, userDto.email) && Objects.equals(carDetails, userDto.carDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, carDetails, isConfirmed);
    }

    @Override
    public String toString() {
        return String.format("User: ID - %d, username - %s, email - %s", id, username, email);
    }
}