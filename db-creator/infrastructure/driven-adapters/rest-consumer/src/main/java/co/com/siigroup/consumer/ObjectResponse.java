package co.com.siigroup.consumer;

    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.LinkedHashMap;
    import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ObjectResponse {

    private Object status;
    private Object message;
    private Object data;

}