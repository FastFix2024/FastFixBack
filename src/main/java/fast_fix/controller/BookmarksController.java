package fast_fix.controller;

import fast_fix.domain.dto.BookmarksRequest;
import fast_fix.domain.entity.Bookmarks;
import fast_fix.domain.entity.User;
import fast_fix.repository.BookmarksRepository;
import fast_fix.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmarks")
public class BookmarksController {

    private final BookmarksRepository bookmarksRepository;
    private BookmarksRepository BookmarksRepository;
    private UserRepository userRepository;

    public BookmarksController(BookmarksRepository bookmarksRepository) {
        this.bookmarksRepository = bookmarksRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody BookmarksRequest favoriteRequest) {
        User user = userRepository.findById(favoriteRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bookmarks bookmarks = new Bookmarks();
        bookmarks.setUser(user);
        bookmarks.setServiceName(bookmarks.getServiceName());
        bookmarks.setServiceAddress(bookmarks.getServiceAddress());

        bookmarksRepository.save(bookmarks);
        return ResponseEntity.ok("Bookmark added successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bookmarks>> getFavoritesByUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Bookmarks> favorites = bookmarksRepository.findByUser(user);
        return ResponseEntity.ok(favorites);
    }
}
