package main.repositories;
import main.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(
            value = "SELECT p.SerialNumber From Products p " +
                    "INNER JOIN CampaignProduct r ON r.SerialNumber=p.SerialNumber " +
                    "INNER JOIN Campaigns c ON c.CampaignId=r.CampaignId " +
                    "Where p.Category=\":category\" " +
                    "AND c.StartDate BETWEEN \":startDate\" AND \":endDate\" " +
                    "Group BY p.SerialNumber " +
                    "ORDER BY MAX(c.Bid) DESC " +
                    "LIMIT 1",
            nativeQuery = true)
    List<String> getProductToPromote(@Param("startDate") String startDate,
                                     @Param("endDate") String endDate,
                                     @Param("category") String category);
}
