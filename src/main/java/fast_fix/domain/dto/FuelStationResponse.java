package fast_fix.domain.dto;

import java.math.BigDecimal;
import java.util.List;

public class FuelStationResponse {

    private List<Station> stations;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public static class Station {
        private String id;
        private String name;
        private String brand;
        private String street;
        private String place;
        private BigDecimal lat;
        private BigDecimal lng;
        private BigDecimal diesel;
        private BigDecimal e5;
        private BigDecimal e10;
        private boolean isOpen;

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
    }
}