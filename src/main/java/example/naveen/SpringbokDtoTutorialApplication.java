package example.naveen;

import example.naveen.dto.AddressDto;
import example.naveen.dto.CustomerDto;
import example.naveen.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringbokDtoTutorialApplication implements CommandLineRunner {

    private final CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbokDtoTutorialApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        AddressDto addressDto = AddressDto.builder()
                .city("Pune")
                .zip("12334")
                .street("Pune")
                .state("Maharashtra")
                .build();
        CustomerDto customerDto = CustomerDto.builder()
                .firstName("naveen")
                .lastName("Kumar")
                .customerAddress(addressDto)
                .build();
        CustomerDto saveCustomer = customerService.saveCustomer(customerDto);
        System.out.println(saveCustomer);
    }
}
