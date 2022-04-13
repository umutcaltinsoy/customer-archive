package com.altinsoy.customerarchive.service.impl;

import com.altinsoy.customerarchive.model.Customer;
import com.altinsoy.customerarchive.repository.CustomerRepository;
import com.altinsoy.customerarchive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByIdentityNumber(String identityNumber) {
        return customerRepository.findByIdentityNumber(identityNumber);
    }

    @Override
    public void deleteCustomerByIdentityNumber(String identityNumber) {
        customerRepository.deleteByIdentityNumber(identityNumber);
    }
}
