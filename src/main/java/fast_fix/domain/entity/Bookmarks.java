package fast_fix.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "bookmarks")
public class Bookmarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "service_address")
    private String serviceAddress;

    public Bookmarks() {}

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmarks bookmarks = (Bookmarks) o;
        return Objects.equals(id, bookmarks.id) && Objects.equals(user, bookmarks.user) && Objects.equals(serviceName, bookmarks.serviceName) && Objects.equals(serviceAddress, bookmarks.serviceAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, serviceName, serviceAddress);
    }

    @Override
    public String toString() {
        return String.format("Bookmark: ID - %d, User - %s", id, user);
    }
}
