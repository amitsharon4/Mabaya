package appstarter.configurations;
import implementations.AdvertisementServiceImpl;
import interfaces.AdvertisementService;
import interfaces.CampaignRepository;
import interfaces.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@Configuration
//@EnableJpaRepositories("interfaces")
public class ServiceConfiguration {
   // @Bean
    public AdvertisementService advertisementService(CampaignRepository campaignRepo, ProductRepository productRepo) {
        return new AdvertisementServiceImpl(campaignRepo, productRepo);
    }
}
