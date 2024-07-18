package com.sonny.positions.customers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(CustomerController.class)   // On definit le controller a tester
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean    // On simule le service et l'injection du bean
    CustomerService customerService;

    @Test
    @DisplayName("[Controller] Test liste des customers")     // On nomme notre test
    void shouldReturnListOfCustomers() throws Exception {
        // Arrange
        CustomerDTO customer1 = new CustomerDTO(1, "sonnyhunt@gmail.com");
        CustomerDTO customer2 = new CustomerDTO(2, "sonny@gmail.com");
        // Ici on simule l'acces du controller au service
        when(this.customerService.search()).thenReturn(List.of(customer1, customer2));

        // Act & Assert
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(containsString("sonny@gmail.com")));
    }

    @Test
    @DisplayName("[Controller] Test lecture d'un customer")
    void shouldReturnCustomerById() throws Exception {
        // Arrange
        CustomerDTO customer1 = new CustomerDTO(1, "sonnyhunt@gmail.com");
        CustomerDTO customer2 = new CustomerDTO(2, "sonny@gmail.com");
        // Ici on simule l'acces du controller au service
        when(this.customerService.readById(2)).thenReturn(customer2);

        // Act & Assert
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(customer2.email()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

}