package main.repositories;

import main.entities.Product;
import main.projections.ProductIdOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(
            value = "SELECT p.SerialNumber\n" +
                    "From Products p\n" +
                    "INNER JOIN CampaignProduct r ON r.SerialNumber=p.SerialNumber\n" +
                    "INNER JOIN Campaigns c ON c.CampaignId=r.CampaignId\n" +
                    "Where p.Category= :vino\n" +
                    "AND c.StartDate BETWEEN :startDate AND :endDate\n" +
                    "Group BY p.SerialNumber\n" +
                    "ORDER BY MAX(c.Bid) DESC\n" +
                    "LIMIT 1\n",
            nativeQuery = true)
    ProductIdOnly getProductToPromote(@Param("startDate") String startDate,
                                            @Param("endDate") String endDate,
                                            @Param("vino") String category);
}
