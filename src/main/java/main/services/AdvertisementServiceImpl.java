package main.services;

import main.entities.Campaign;
import main.entities.CampaignProduct;
import main.entities.Product;
import main.enums.ProductCategories;
import main.exceptions.ProductsNotFoundException;
import main.repositories.CampaignProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.repositories.CampaignRepository;
import main.repositories.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final CampaignRepository campaignRepo;

    private final ProductRepository productRepo;

    private final CampaignProductRepository campaignProductRepo;

    @Autowired
    public AdvertisementServiceImpl(CampaignRepository campaignRepo, ProductRepository productRepo, CampaignProductRepository campaignProductRepo){
        this.campaignRepo = campaignRepo;
        this.productRepo = productRepo;
        this.campaignProductRepo = campaignProductRepo;
    }
    @Override
    public Campaign createCampaign(Campaign campaign, List<String> products) throws ProductsNotFoundException {
        validateProductsExist(products);
        Campaign createdCampaign =  campaignRepo.save(campaign);
        for(String product: products){
            campaignProductRepo.save(new CampaignProduct(createdCampaign.getCampaignId(), product));
        }
        return createdCampaign;
    }

    @Override
    public String serveAd(String category){
        String currentDate =  LocalDate.now().minusDays(1).toString();
        String startDate = LocalDate.now().minusDays(10).toString();
        try {
            List<String> res = productRepo.getProductToPromote(startDate, currentDate, category);
            System.out.println(res);
        }
        catch (Exception e){
            int i = 3;
        }
        return null;
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
