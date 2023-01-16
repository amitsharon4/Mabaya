package main.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
@NoArgsConstructor()
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ValidationErrorResponse extends BaseResponse{
    private String error;
    private String errorDescription;
    private Map<String, String> validationErrors;
    @Builder(builderMethodName = "validationErrorResponseBuilder")
    public ValidationErrorResponse(String error, String errorDescription, Map<String, String> validationErrors){
        super(error, errorDescription);
        this.validationErrors = validationErrors;
    }
}
