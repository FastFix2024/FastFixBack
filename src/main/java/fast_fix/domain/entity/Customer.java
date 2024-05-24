package fast_fix.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    public Customer() {
    }

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return isActive == customer.isActive && Objects.equals(id, customer.id) && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isActive);
    }

    @Override
    public String toString() {
        return String.format("Customer: ID - %d, name - %s, active - %s",
                id, name, isActive ? "yes" : "no");
    }
}