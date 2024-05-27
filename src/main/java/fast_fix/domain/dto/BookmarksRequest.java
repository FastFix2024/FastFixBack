package fast_fix.domain.dto;


public class BookmarksRequest {
    private Long userId;
    private String serviceName;
    private String serviceAddress;

    public BookmarksRequest() {
    }

    public BookmarksRequest(Long userId, String serviceName, String serviceAddress) {
        this.userId = userId;
        this.serviceName = serviceName;
        this.serviceAddress = serviceAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return "FavoriteRequest{" +
                "userId=" + userId +
                ", serviceName='" + serviceName + '\'' +
                ", serviceAddress='" + serviceAddress + '\'' +
                '}';
    }
}
