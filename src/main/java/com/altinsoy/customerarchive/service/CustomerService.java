package com.altinsoy.customerarchive.service;

import com.altinsoy.customerarchive.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomer();

    Customer getCustomerByIdentityNumber(String identityNumber);

    void deleteCustomerByIdentityNumber(String identityNumber);

}
