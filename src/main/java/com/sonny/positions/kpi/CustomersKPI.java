package com.sonny.positions.kpi;

import com.sonny.positions.customers.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "customers")
@AllArgsConstructor
public class CustomersKPI {     // creation d'un endpoint personnalise

    private CustomerService customerService;

    @ReadOperation
    public int count() {
        return this.customerService.search().size();
    }
}
