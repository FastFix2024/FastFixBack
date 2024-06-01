package fast_fix.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "start_location", nullable = false)
    private String startLocation;

    @Column(name = "end_location", nullable = false)
    private String endLocation;

    @Column(name = "duration", nullable = false)
    private String duration;

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

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(user, route.user) && Objects.equals(startLocation, route.startLocation) && Objects.equals(endLocation, route.endLocation) && Objects.equals(duration, route.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, startLocation, endLocation, duration);
    }

    @Override
    public String toString() {
        return String.format("Route: ID - %d, user - %s, start location - %s, end location - %s, duration - %s", id, user, startLocation, endLocation, duration);
    }
}