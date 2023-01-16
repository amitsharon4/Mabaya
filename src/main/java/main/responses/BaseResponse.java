package main.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse {
    private String error;

    private String errorDescription;

    public static ResponseEntity<BaseResponse> createBaseErrorResponse(String message, HttpStatus status){
        BaseResponse response = new BaseResponse();
        response.setError(status.getReasonPhrase());
        response.setErrorDescription(message);
        return new ResponseEntity<>(response, status);
    }
}
