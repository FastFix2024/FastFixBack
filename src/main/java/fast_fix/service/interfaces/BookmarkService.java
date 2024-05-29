package fast_fix.service.interfaces;

import fast_fix.domain.dto.BookmarkDto;

import java.util.List;

public interface BookmarkService {

    BookmarkDto addBookmark(Long userId, Long fuelStationId);
    List<BookmarkDto> getBookmarksByUserId(Long userId);

}
