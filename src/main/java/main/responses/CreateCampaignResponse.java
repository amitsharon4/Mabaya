package main.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor()
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class CreateCampaignResponse extends BaseResponse{
    private String campaignId;
    private String name;
    private String startDate;
    private String bid;

    public static ResponseEntity<CreateCampaignResponse> createErrorResponse(String message, HttpStatus status){
        CreateCampaignResponse response = new CreateCampaignResponse();
        response.setError(status.getReasonPhrase());
        response.setErrorDescription(message);
        return new ResponseEntity<>(response, status);
    }

}
