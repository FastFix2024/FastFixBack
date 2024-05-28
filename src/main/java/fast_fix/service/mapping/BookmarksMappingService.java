package fast_fix.service.mapping;

import fast_fix.domain.dto.BookmarksDto;
import fast_fix.domain.entity.Bookmarks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = InsuranceCompanyMappingService.class)
public interface BookmarksMappingService {

    BookmarksDto bookmarksToBookmarksDto(Bookmarks bookmarks);

    @Mapping(target = "id", ignore = true)
    Bookmarks BookmarksDtoToBookmarks(BookmarksDto bookmarksDto);
}
