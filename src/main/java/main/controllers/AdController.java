package main.controllers;

import com.mysql.cj.util.StringUtils;
import jakarta.validation.Valid;
import main.enums.ProductCategories;
import main.requests.ServeAdRequest;
import main.responses.ServeAdResponse;
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
    @Autowired
    public AdController(AdvertisementService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServeAdResponse> serveAd(@Valid @RequestBody ServeAdRequest request){
        try{
            ProductCategories.valueOf(request.getCategory().toUpperCase());
        }
        catch(IllegalArgumentException exception){
            return ServeAdResponse.serveAdErrorResponse("This Category does not exist", HttpStatus.BAD_REQUEST);
        }
        try {
            String adResult = service.serveAd(request.getCategory());
            if(!StringUtils.isNullOrEmpty(adResult)){
                return new ResponseEntity<>(new ServeAdResponse(adResult), HttpStatus.OK);
            }
            return ServeAdResponse.serveAdErrorResponse("No qualifying product to promote", HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return ServeAdResponse.serveAdErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
