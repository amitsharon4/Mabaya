package main.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor()
@Data
public class CreateCampaignResponse {
    String error;
    String errorDescription;
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
