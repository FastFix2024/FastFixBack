package fast_fix.domain.dto;

import java.util.Objects;

public class RoleDto {

    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(id, roleDto.id) && Objects.equals(title, roleDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }


    @Override
    public String toString() {
        return String.format("Role: ID - %d - Title - %s", id, title);
    }
}