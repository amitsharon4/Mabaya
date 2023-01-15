package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name= "SerialNumber")
    private String serialNumber;
    @Column(name= "Title")
    private String title;
    @Column(name= "Category")
    private String category;
    @Column(name= "Price")
    private String price;
}
