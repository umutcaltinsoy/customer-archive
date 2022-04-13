package com.altinsoy.customerarchive.controller;

import com.altinsoy.customerarchive.model.Customer;
import com.altinsoy.customerarchive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer saveCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(saveCustomer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("/customer/{identityNumber}")
    public ResponseEntity<Customer> getCustomerByIdentityNumber(
            @PathVariable(name = "identityNumber") String identityNumber) {
        return ResponseEntity.ok(customerService.getCustomerByIdentityNumber(identityNumber));
    }

    @DeleteMapping("/customer/{identityNumber}")
    public ResponseEntity<?> deleteCustomerByIdentityNumber(@PathVariable(name = "identityNumber") String identityNumber) {

        customerService.deleteCustomerByIdentityNumber(identityNumber);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
