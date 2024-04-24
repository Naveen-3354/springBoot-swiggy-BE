package spring.boot.swiggyBE.database_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import spring.boot.swiggyBE.common_model.Status;

import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
    @Id
    private String id;
    private String categoryName;
    private String categoryNo;
    private String createdBy;
    private Date createdOn;
    private Status status;
}
