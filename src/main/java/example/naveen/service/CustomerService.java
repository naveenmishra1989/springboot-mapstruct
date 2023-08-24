package example.naveen.service;

import example.naveen.dto.CustomerDto;
import example.naveen.entity.Customer;
import example.naveen.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static example.naveen.dto.mapper.CustomerMapper.CUSTOMER_MAPPER;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDto getCustomer(Long id) {
        return customerRepository.findById(id).map(CUSTOMER_MAPPER::customerToCustomerDTO)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = CUSTOMER_MAPPER.customerDTOToCustomer(customerDto);
        Customer save = customerRepository.save(customer);
        return Optional.of(save)
                .map(CUSTOMER_MAPPER::customerToCustomerDTO)
                .orElseThrow(() -> new RuntimeException("Customer not saved"));
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id).ifPresentOrElse(customerRepository::delete,
                () -> { throw new RuntimeException("Customer not found: "+id); });
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(CUSTOMER_MAPPER::customerToCustomerDTO)
                .collect(Collectors.toList());
    }
}
