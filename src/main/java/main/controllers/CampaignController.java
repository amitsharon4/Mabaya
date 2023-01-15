package main.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.entities.Campaign;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import main.requests.CreateCampaignRequest;
import main.responses.CreateCampaignResponse;
import main.services.AdvertisementService;

@RestController()
@RequestMapping(value="/campaign", produces = "application/json;charset=UTF-8")
@Validated
public class CampaignController {
    private final AdvertisementService service;
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    public CampaignController(AdvertisementService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateCampaignResponse> createCampaign(@Valid @RequestBody CreateCampaignRequest request){
        //validation
        try {
            Campaign campaignToCreate = mapper.convertValue(request, Campaign.class);
            Campaign createdCampaign = service.createCampaign(campaignToCreate, request.getProducts());
            return new ResponseEntity<>(mapper.convertValue(createdCampaign, CreateCampaignResponse.class), HttpStatus.CREATED);
        }
        catch(Exception e){
            return CreateCampaignResponse.createErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
