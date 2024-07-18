package com.sonny.positions.customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void shouldReturnAllCustomers() {
        // Arrange
        Customer customer1 = Customer.builder().id(1).email("Sonnyhunt@gmail.com").build();
        Customer customer2 = Customer.builder().id(2).email("Sonnyhardy@gmail.com").build();
        Customer customer3 = Customer.builder().id(3).email("Paul@gmail.com").build();
        Customer customer4 = Customer.builder().id(4).email("Jean@gmail.com").build();
        this.customerRepository.saveAll(List.of(customer1, customer2, customer3, customer4));

        // Act
        final List<Customer> customerList = this.customerRepository.findAll();

        // Asserts
        Assertions.assertEquals(4, customerList.size());
    }

    @Test
    public void shouldReturnCustomerByEmail() {
        // Arrange
        Customer customer1 = Customer.builder().id(1).email("sonnyhunt@gmail.com").build();
        Customer customer2 = Customer.builder().id(2).email("Paul@gmail.com").build();
        Customer customer3 = Customer.builder().id(3).email("sonny@gmail.com").build();
        this.customerRepository.saveAll(List.of(customer1, customer2, customer3));

        // Act
        final Customer customer = this.customerRepository.findByEmail("sonny@gmail.com");

        // Asserts
        Assertions.assertEquals(customer3.getId(), customer.getId());
        Assertions.assertEquals(customer3.getEmail(), customer.getEmail());
    }

}