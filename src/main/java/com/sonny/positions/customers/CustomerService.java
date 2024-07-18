package com.sonny.positions.customers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerService{

    //private CustomerDao customerDao;
    private CustomerRepository customerRepository;


    public List<CustomerDTO> search() {
        return this.customerRepository.findAll().stream().map(customer ->
            new CustomerDTO(customer.getId(), customer.getEmail())
        ).collect(Collectors.toList());

    }

    public CustomerDTO readById(int id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No customer for id "+ id));
        return new CustomerDTO(customer.getId(), customer.getEmail());
    }

}
