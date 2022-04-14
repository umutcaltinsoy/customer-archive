package com.altinsoy.customerarchive.mapper.impl;


import com.altinsoy.customerarchive.mapper.CustomerMapper;
import com.altinsoy.customerarchive.model.Customer;
import com.altinsoy.customerarchive.model.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public Customer mapCustomerDtoToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .identityNumber(customerDto.getIdentityNumber())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .email(customerDto.getEmail())
                .build();
    }

    @Override
    public CustomerDto mapCustomerToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .identityNumber(customer.getIdentityNumber())
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail())
                .build();
    }
}
