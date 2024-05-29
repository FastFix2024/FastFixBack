package fast_fix.controller;

import fast_fix.domain.dto.BookmarkDto;
import fast_fix.domain.entity.Bookmark;
import fast_fix.domain.entity.User;
import fast_fix.repository.BookmarkRepository;
import fast_fix.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    private BookmarkRepository repository;
    private UserRepository userRepository;

    public BookmarkController(BookmarkRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody BookmarkDto bookmarkDto) {
        User user = userRepository.findById(bookmarkDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setFuelStation(bookmark.getFuelStation());

        repository.save(bookmark);
        return ResponseEntity.ok("Bookmark added successfully");
    }

    @GetMapping("/user")
    public ResponseEntity<List<Bookmark>> getFavoritesByUser(@RequestParam Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Bookmark> favorites = repository.findByUser(user);
        return ResponseEntity.ok(favorites);
    }
}
