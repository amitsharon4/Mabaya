package interfaces;

import exceptions.ProductsNotFoundException;
import models.Campaign;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdvertisementService{

    public Campaign createCampaign(Campaign campaign, List<String> products) throws ProductsNotFoundException;
}
