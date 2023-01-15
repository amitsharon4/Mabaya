package implementations;

import exceptions.ProductsNotFoundException;
import interfaces.AdvertisementService;
import interfaces.CampaignRepository;
import interfaces.ProductRepository;
import models.Campaign;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final CampaignRepository campaignRepo;

    private final ProductRepository productRepo;

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
