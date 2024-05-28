package fast_fix.domain.dto;

import java.util.Objects;

public class InsuranceCompanyDto {

        private Long id;

        private String companyName;

        private String phone;

        private String website;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceCompanyDto that = (InsuranceCompanyDto) o;
        return Objects.equals(id, that.id) && Objects.equals(companyName, that.companyName) && Objects.equals(phone, that.phone) && Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, phone, website);
    }

    @Override
    public String toString() {
        return String.format("Insurance company: ID - %d, Company Name - %s, Phone - %s, Website - %s", id, companyName, phone, website);
    }
}
