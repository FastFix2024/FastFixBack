package fast_fix.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Schema(description = "Username", example = " ")
    @Column(name = "username", nullable = false, unique = true)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Username should contain only English letters")
    private String username;

    @Schema(description = "Email", example = " ")
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "User Password", example = "Ab3$Ef7*")
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern.List({
            @Pattern(regexp = "(?=.*[0-9]).+", message = "Password must contain at least one digit"),
            @Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain at least one lowercase letter"),
            @Pattern(regexp = "(?=.*[A-Z]).+", message = "Password must contain at least one uppercase letter"),
            @Pattern(regexp = "(?=.*[@$!%*?&]).+", message = "Password must contain at least one special character (@$!%*?&)")
    })
    private String password;

    @Schema(description = "Car details", example = "param1, param2")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_details_id", referencedColumnName = "id")
    private CarDetails carDetails;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "is_confirmed")
    private boolean confirmed;

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

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return confirmed == user.confirmed && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(carDetails, user.carDetails) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, carDetails, roles, confirmed);
    }

    @Override
    public String toString() {
        return String.format("User: ID - %d, username - %s, email - %s, role - %s, confirmed - %s", id, username, email, roles, confirmed ? "true" : "false");
    }
}