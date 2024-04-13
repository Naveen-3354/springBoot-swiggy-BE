package spring.boot.swiggyBE.database_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import spring.boot.swiggyBE.common_model.Status;

import java.util.Date;
import java.util.Set;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    private String id;
    private String userName;
    private String email;
    private String mobileNumber;
    private String password;
    private Date createdOn;
    private Status status;
    private Set<Roles> roles;

}
