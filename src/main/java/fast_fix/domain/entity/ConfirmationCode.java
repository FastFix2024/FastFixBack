package fast_fix.domain.entity;

import java.time.LocalDate;
import java.util.Objects;

public class ConfirmationCode {
    private Long id;
    private String code;
    private LocalDate expired;
    private User user;

    public ConfirmationCode() {
    }

    public ConfirmationCode(String code, LocalDate expired, User user) {
        this.code = code;
        this.expired = expired;
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public LocalDate getExpired() {
        return expired;
    }
    public void setExpired(LocalDate expired) {
        this.expired = expired;
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

        ConfirmationCode that = (ConfirmationCode) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(expired, that.expired) && Objects.equals(user, that.user);
    }
    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(code);
        result = 31 * result + Objects.hashCode(expired);
        result = 31 * result + Objects.hashCode(user);
        return result;
    }

    @Override
    public String toString() {
        return "ConfirmationCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", expired=" + expired +
                ", user=" + user +
                '}';
    }
}
