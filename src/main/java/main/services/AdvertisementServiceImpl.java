package main.services;

import main.entities.Campaign;
import main.exceptions.ProductsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.repositories.CampaignRepository;
import main.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final CampaignRepository campaignRepo;

    private final ProductRepository productRepo;

    @Autowired
    public AdvertisementServiceImpl(CampaignRepository campaignRepo, ProductRepository productRepo){
        this.campaignRepo = campaignRepo;
        this.productRepo = productRepo;
    }
    @Override
    public Campaign createCampaign(Campaign campaign, List<String> products) throws ProductsNotFoundException {
        validateProductsExist(products);
        return campaignRepo.save(campaign);
    }


    private void validateProductsExist(List<String> products) throws ProductsNotFoundException{
        List<String> notFoundProducts = new ArrayList<>();
        for(String productId: products){
            if(!productRepo.existsById(productId)){
                notFoundProducts.add(productId);
            }
        }
        if(!notFoundProducts.isEmpty()){
            throw new ProductsNotFoundException(notFoundProducts);
        }
    }
}
