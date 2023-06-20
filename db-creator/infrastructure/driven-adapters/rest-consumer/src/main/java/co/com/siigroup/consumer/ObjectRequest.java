package co.com.siigroup.consumer;

    import lombok.Builder;
    import lombok.Data;
    @Data
    @Builder(toBuilder = true)
public class ObjectRequest {
private String url;
}
