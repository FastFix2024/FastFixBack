package fast_fix.domain.dto;

public class BookmarkDto {

    private Long id;
    private String serviceName;
    private String serviceAddress;

    public BookmarkDto() {
    }

    public BookmarkDto(Long id, String serviceName, String serviceAddress) {
        this.id = id;
        this.serviceName = serviceName;
        this.serviceAddress = serviceAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    @Override
    public String toString() {
        return String.format("Bookmark: ID - %d, Service name - %s, Service address - %s", id, serviceName, serviceAddress);
    }
}
