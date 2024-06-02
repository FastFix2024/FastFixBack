package fast_fix.domain.dto;

import java.util.Objects;

public class RouteDto {

    private Long id;
    private String startLocation;
    private String endLocation;
    private String duration;
    private String distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteDto routeDto = (RouteDto) o;
        return Objects.equals(id, routeDto.id) && Objects.equals(startLocation, routeDto.startLocation) && Objects.equals(endLocation, routeDto.endLocation) && Objects.equals(duration, routeDto.duration) && Objects.equals(distance, routeDto.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startLocation, endLocation, duration, distance);
    }

    @Override
    public String toString() {
        return String.format("Route: ID - %d, start location - %s, end location - %s, duration - %s", id, startLocation, endLocation, duration);
    }
}