package example.naveen.mock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.naveen.entity.Address;
import example.naveen.entity.Customer;

import java.util.Collection;

public class MockData {
    public static <T> T fromJson(String json, Class<T> classz) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, classz);
    }
    public static  <T> String fromCollection(Collection<T> classz) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(classz);
    }
    public static  <T> String fromObject(Class<T> classz) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(classz);
    }
    public static <T> T fromJson(String json, TypeReference<T> TypeReference) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeReference);
    }
    public static Customer getCustomer(Address address) {
        return Customer.builder()
                .firstName("sonu").lastName("sahu")
                .address(address)
                .build();
    }

    public static Address getAddress() {
        return Address.builder()
                .city("Pune")
                .zip("12334")
                .street("Pune")
                .state("Maharashtra")
                .build();
    }
}
