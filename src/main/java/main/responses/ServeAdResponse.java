package main.responses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor()
public class ServeAdResponse extends BaseResponse{
    private String productId;

    public static ResponseEntity<ServeAdResponse> serveAdErrorResponse(String message, HttpStatus status){
        ServeAdResponse response = new ServeAdResponse();
        response.setError(status.getReasonPhrase());
        response.setErrorDescription(message);
        return new ResponseEntity<>(response, status);
    }
}
