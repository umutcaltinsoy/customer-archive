package com.altinsoy.customerarchive.service.impl;

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

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.mapCustomerDtoToCustomer(customerDto);
        Optional<Customer> customer1 = customerRepository.findByIdentityNumber(customerDto.getIdentityNumber());
        if (customer1.isEmpty()) {
            customerRepository.save(customer);
        }
        return customerMapper.mapCustomerToCustomerDto(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream()
                .map(customerMapper::mapCustomerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerByIdentityNumber(String identityNumber) {
        CustomerDto customerDto = new CustomerDto();
        Optional<Customer> customer = customerRepository.findByIdentityNumber(identityNumber);
        if (customer.isPresent()) {
            customerDto = customerMapper.mapCustomerToCustomerDto(customer.get());
            return customerDto;
        }
        return customerDto;
    }

    @Override
    public void deleteCustomerByIdentityNumber(String identityNumber) {
        customerRepository.deleteByIdentityNumber(identityNumber);
    }

    @Override
    public CustomerDto updateCustomer(String identityNumber, CustomerDto customerDto) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber).orElseThrow(
                () -> new CustomerNotFoundException("Customer couldn't find with given ID : " + identityNumber)
        );

        setCustomerDetails(customerDto, customer);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.mapCustomerToCustomerDto(updatedCustomer);
    }

    private void setCustomerDetails(CustomerDto customerDto, Customer customer) {
        customer.setIdentityNumber(customerDto.getIdentityNumber());
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setEmail(customerDto.getEmail());
    }
}
