package fast_fix.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Schema(description = "User Entity")
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Schema(description = "User ID", example = "15")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Schema(description = "User Name", example = "TestUser1")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Schema(description = "User email", example = "looga.jury@gmail.com")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Schema(description = "User Password", example = "111")
    @Column(name = "password", nullable = false)
    private String password;

    @Schema(description = "Car Details", example = "param1, param2")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_details_id", referencedColumnName = "id")
    private CarDetails carDetails;

    @Schema(description = "User Role", example = "USER, ADMIN")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Schema(description = "Active User", example = "isActive")
    @Column(name = "is_active")
    private boolean isActive;

    @Schema(description = "User place in Latitud", example = "23.443.00")
    @Column(name = "lat")
    private BigDecimal lat;

    @Schema(description = "User place in Long..", example = "22.542.00")
    @Column(name = "lng")
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

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
        User user = (User) o;
        return isActive == user.isActive && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(carDetails, user.carDetails) && Objects.equals(roles, user.roles) && Objects.equals(lat, user.lat) && Objects.equals(lng, user.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, carDetails, roles, isActive, lat, lng);
    }

    @Override
    public String toString() {
        return String.format("User: ID - %d, username - %s, email - %s, active - %s, role - %s", id, username, email, isActive, roles);
    }
}