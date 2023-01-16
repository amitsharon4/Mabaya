package main.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor()
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCampaignResponse extends BaseResponse{
    String campaignId;
    String name;
    String startDate;
    String bid;

    public static ResponseEntity<CreateCampaignResponse> createErrorResponse(String message, HttpStatus status){
        CreateCampaignResponse response = new CreateCampaignResponse();
        response.setError(status.getReasonPhrase());
        response.setErrorDescription(message);
        return new ResponseEntity<>(response, status);
    }

}
