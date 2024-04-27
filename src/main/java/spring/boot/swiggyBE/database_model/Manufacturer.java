package spring.boot.swiggyBE.database_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {
    @Id
    private String manufacturerName;
    private String manufacturerNo;
    private String address;
    private String contactNumber;
    private String description;
    private String city;
    private String state;
    private String country;
    private String userId;
    private String  image;
}
