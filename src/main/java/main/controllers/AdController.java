package main.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import main.entities.Campaign;
import main.entities.Product;
import main.enums.ProductCategories;
import main.exceptions.ProductsNotFoundException;
import main.requests.CreateCampaignRequest;
import main.requests.ServeAdRequest;
import main.responses.CreateCampaignResponse;
import main.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value="/serveAd", produces = "application/json;charset=UTF-8")
@Validated
public class AdController {
    private final AdvertisementService service;
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    public AdController(AdvertisementService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateCampaignResponse> serveAd(@Valid @RequestBody ServeAdRequest request){
        //validation?
        try {
            ProductCategories category = ProductCategories.valueOf(request.getCategory().toUpperCase()); //make sure to catch this error
            String adResult = service.serveAd(request.getCategory());
            System.out.println(adResult);
        }
        //catch(ProductsNotFoundException exception){
         //   return CreateCampaignResponse.createErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
       // }
        catch(Exception e){
            return CreateCampaignResponse.createErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
