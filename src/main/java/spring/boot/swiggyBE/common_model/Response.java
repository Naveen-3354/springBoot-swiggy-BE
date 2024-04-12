package spring.boot.swiggyBE.common_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T extends String> {
    private T message;
    private T error;
    private short statusCode;
}
