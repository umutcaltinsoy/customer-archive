package com.altinsoy.customerarchive.service;

import com.altinsoy.customerarchive.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomer();

    CustomerDto getCustomerByIdentityNumber(String identityNumber);

    void deleteCustomerByIdentityNumber(String identityNumber);

    CustomerDto updateCustomer(String identityNumber, CustomerDto customerDto);
}
