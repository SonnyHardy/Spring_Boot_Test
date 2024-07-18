package com.sonny.positions.customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock              // Ici on simule la couche repository
    CustomerRepository customerRepository;

    @InjectMocks     // On injecte la classe a tester
    CustomerService customerService;

    @Test
    @DisplayName("[Controller] Test liste des customers")
    void shouldReturnAllCustomers() {
        // Arrange
        Customer customer1 = Customer.builder().id(1).email("sonnyhunt@gmail.com").build();
        Customer customer2 = Customer.builder().id(2).email("sonny@gmail.com").build();
        // Ici on simule l'acces du customerService au repository
        when(this.customerRepository.findAll()).thenReturn(List.of(customer1, customer2));

        // Act
        final List<CustomerDTO> customerList = this.customerService.search();

        // Asserts
        Assertions.assertEquals(2, customerList.size());
    }

    @Test
    @DisplayName("[Service] Test lecture d'un customer")
    void shouldReturnCustomerById() {
        // Arrange
        Customer customer1 = Customer.builder().id(1).email("sonnyhunt@gmail.com").build();
        Customer customer2 = Customer.builder().id(2).email("sonny@gmail.com").build();
        // Ici on simule l'acces du customerService au repository
        when(this.customerRepository.findById(2)).thenReturn(Optional.of(customer2));

        // Act
        final CustomerDTO customerDTO = this.customerService.readById(2);

        // Asserts
        Assertions.assertEquals(customer2.getId(), customerDTO.id());
        Assertions.assertEquals(customer2.getEmail(), customerDTO.email());
    }

    @Test
    void shouldThrowException() {
        // Arrange
        when(this.customerRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        // Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> this.customerService.readById(2));
        assertEquals("No customer for id 2", exception.getMessage());
    }

}