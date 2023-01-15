package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Campaigns")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue
    @Column(name= "CampaignId")
    String campaignId;
    @Column(name= "Name")
    String name;
    @Column(name= "StartDate")
    LocalDate startDate;
    @Column(name= "Bid")
    float bid;
}
