package spring.boot.swiggyBE.components.generators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Generator {

    @Value("${swiggy.app.code}")
    private String userCode;

    public String categoryIdGenerator(String name, long count){
        return name.toLowerCase().substring(0, 4)+ String.format("%05d", count);
    }
    public String userIdGenerator(long count){
        return userCode.toLowerCase().substring(0, 4)+ String.format("%05d", count);
    }
}
