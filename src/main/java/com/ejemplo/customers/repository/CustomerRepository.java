package com.ejemplo.customers.repository;

import com.ejemplo.customers.model.Customer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {
    private final List<Customer> customers = new CopyOnWriteArrayList<>();

    public Customer save(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public List<Customer> findAll() {
        return List.copyOf(customers);
    }
}