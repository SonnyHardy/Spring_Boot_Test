package com.sonny.positions.customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@JdbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CustomerDaoTest {

    CustomerDao customerDao;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void shouldReturnListOfCustomers() {      // Test de la methode search()
        // Arrange
        customerDao = new CustomerDao(jdbcTemplate);

        // Act
        List<CustomerDTO> customerDTOList = this.customerDao.search();

        // Assert
        Assertions.assertEquals(6, customerDTOList.size());
        Assertions.assertEquals(customerDTOList.getFirst().email(), "sonnyhardy@gmail.com");
    }
}