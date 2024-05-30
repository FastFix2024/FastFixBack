package fast_fix.domain.dto;

import fast_fix.domain.entity.Role;

import java.util.Objects;
import java.util.Set;

public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean active;
    private Set<Role> roles;
    private Set<BookmarkDto> bookmark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<BookmarkDto> getBookmark() {
        return bookmark;
    }

    public void setBookmark(Set<BookmarkDto> bookmark) {
        this.bookmark = bookmark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return active == userDto.active && Objects.equals(id, userDto.id) && Objects.equals(username, userDto.username) && Objects.equals(password, userDto.password) && Objects.equals(email, userDto.email) && Objects.equals(roles, userDto.roles) && Objects.equals(bookmark, userDto.bookmark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, active, roles, bookmark);
    }

    @Override
    public String toString() {
        return String.format("User: ID - %d, Username - %s, Email - %s, Active - %s, Bookmark - %s", id, username, email, active ? "Yes" : "No", bookmark == null ? "ERROR! Bookmarks are missing!" : bookmark);
    }

}
