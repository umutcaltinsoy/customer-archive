package com.altinsoy.customerarchive.service.impl;

import com.altinsoy.customerarchive.core.utilities.*;
import com.altinsoy.customerarchive.exception.CustomerNotFoundException;
import com.altinsoy.customerarchive.mapper.CustomerMapper;
import com.altinsoy.customerarchive.model.Customer;
import com.altinsoy.customerarchive.model.dto.CustomerDto;
import com.altinsoy.customerarchive.repository.CustomerRepository;
import com.altinsoy.customerarchive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.altinsoy.customerarchive.core.constants.Messages.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public DataResult<CustomerDto> saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.mapCustomerDtoToCustomer(customerDto);
        Optional<Customer> customer1 = customerRepository.findByIdentityNumber(customerDto.getIdentityNumber());
        if (customer1.isEmpty()) {
            customerRepository.save(customer);
        }
        return new SuccessDataResult(customerMapper.mapCustomerToCustomerDto(customer), SUCCESS_ADDED);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public DataResult<List<CustomerDto>> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();

        return new SuccessDataResult(customerList.stream()
                .map(customerMapper::mapCustomerToCustomerDto)
                .collect(Collectors.toList()), SUCCESS_GET);
    }

    @Override
    public DataResult<CustomerDto> getCustomerByIdentityNumber(String identityNumber) {
        CustomerDto customerDto = new CustomerDto();
        Optional<Customer> customer = customerRepository.findByIdentityNumber(identityNumber);
        if (customer.isPresent()) {
            customerDto = customerMapper.mapCustomerToCustomerDto(customer.get());
            return new SuccessDataResult(customerDto);
        }
        return new ErrorDataResult(customerDto, "Can't find!");
    }

    @Override
    public Result deleteCustomerByIdentityNumber(String identityNumber) {
        customerRepository.deleteByIdentityNumber(identityNumber);
        return new SuccessResult(SUCCESS_DELETED);
    }

    @Override
    public DataResult<CustomerDto> updateCustomer(String identityNumber, CustomerDto customerDto) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber).orElseThrow(
                () -> new CustomerNotFoundException("Customer couldn't find with given ID : " + identityNumber)
        );

        setCustomerDetails(customerDto, customer);
        Customer updatedCustomer = customerRepository.save(customer);
        return new SuccessDataResult(customerMapper.mapCustomerToCustomerDto(updatedCustomer), SUCCESS_UPDATED);
    }

    private void setCustomerDetails(CustomerDto customerDto, Customer customer) {
        customer.setIdentityNumber(customerDto.getIdentityNumber());
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setEmail(customerDto.getEmail());
    }
}
