package main.services;
import main.entities.Campaign;
import main.enums.ProductCategories;
import main.exceptions.ProductsNotFoundException;

import java.util.List;

public interface AdvertisementService{

    Campaign createCampaign(Campaign campaign, List<String> products) throws ProductsNotFoundException;

    String serveAd(String category);
}
