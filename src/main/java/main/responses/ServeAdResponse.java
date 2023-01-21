package main.responses;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ServeAdResponse extends BaseResponse{
    private String productId;

    public static ResponseEntity<ServeAdResponse> serveAdErrorResponse(String message, HttpStatus status){
        ServeAdResponse response = new ServeAdResponse();
        response.setError(status.getReasonPhrase());
        response.setErrorDescription(message);
        return new ResponseEntity<>(response, status);
    }
}
