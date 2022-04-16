package com.altinsoy.customerarchive.service;

import com.altinsoy.customerarchive.core.utilities.DataResult;
import com.altinsoy.customerarchive.core.utilities.Result;
import com.altinsoy.customerarchive.model.Customer;
import com.altinsoy.customerarchive.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    DataResult<CustomerDto> saveCustomer(CustomerDto customerDto);

    Customer getById(Long id);

    DataResult<List<CustomerDto>> getAllCustomer();

    DataResult<CustomerDto> getCustomerByIdentityNumber(String identityNumber);

    Result deleteCustomerByIdentityNumber(String identityNumber);

    DataResult<CustomerDto> updateCustomer(String identityNumber, CustomerDto customerDto);
}
