package fast_fix.service.mapping;

import fast_fix.domain.dto.BookmarkDto;
import fast_fix.domain.entity.Bookmark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = FuelStationMappingService.class)
public interface BookmarkMappingService {

    BookmarkDto bookmarksToBookmarksDto(Bookmark bookmarks);

    @Mapping(target = "id", ignore = true)
    Bookmark BookmarksDtoToBookmarks(BookmarkDto bookmarksDto);
}
