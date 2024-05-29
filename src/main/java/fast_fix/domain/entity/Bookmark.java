package fast_fix.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "bookmarks")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fuel_station_id")
    private FuelStation fuelStation;

    public Bookmark() {}

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

    public FuelStation getFuelStation() {
        return fuelStation;
    }

    public void setFuelStation(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return Objects.equals(id, bookmark.id) && Objects.equals(user, bookmark.user) && Objects.equals(fuelStation, bookmark.fuelStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, fuelStation);
    }

    @Override
    public String toString() {
        return String.format("Bookmark: ID - %d, User - %s", id, user);
    }
}
