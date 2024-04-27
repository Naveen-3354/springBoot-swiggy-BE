package spring.boot.swiggyBE.database_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String productName;
    private String categoryNo;
    private String productNo;
    private String prize;
    private String manufacturer;
    private String image;
    private boolean status;
    private String description;
    private String country;
    private String rating;
    private Date createdOn;
    private boolean vegetarian;
}
