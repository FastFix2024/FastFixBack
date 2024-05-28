package fast_fix.domain.dto;

import fast_fix.domain.entity.Role;

import java.util.Objects;
import java.util.Set;

public class UserDto {

    private Long id;
    private String username;
    private String email;
    private boolean active;
    private Set<Role> roles;
    private BookmarksDto bookmarks;

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

    public BookmarksDto getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(BookmarksDto bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return active == userDto.active && Objects.equals(id, userDto.id) && Objects.equals(username, userDto.username) && Objects.equals(email, userDto.email) && Objects.equals(roles, userDto.roles) && Objects.equals(bookmarks, userDto.bookmarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, active, roles, bookmarks);
    }

    @Override
    public String toString() {
        return String.format("User: ID - %d, Username - %s, Email - %s, Active - %s, Bookmark - %s", id, username, email, active ? "Yes" : "No", bookmarks == null ? "ERROR! Bookmarks are missing!" : bookmarks);
    }
}
