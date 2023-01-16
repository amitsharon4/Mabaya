package main.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Entity
@Table(name = "CampaignProduct")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(CampaignProduct.class)
public class CampaignProduct implements Serializable {
    @Id
    @Column(name= "CampaignId")
    private Long campaignId;
    @Id
    @Column(name= "SerialNumber")
    private String serialNumber;
}
