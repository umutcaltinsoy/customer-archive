package com.altinsoy.customerarchive.mapper;

import com.altinsoy.customerarchive.model.Customer;
import com.altinsoy.customerarchive.model.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public interface CustomerMapper {
    Customer mapCustomerDtoToCustomer(CustomerDto customerDto);
    CustomerDto mapCustomerToCustomerDto(Customer customer);
}
