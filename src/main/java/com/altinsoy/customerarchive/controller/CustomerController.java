package com.altinsoy.customerarchive.controller;

import com.altinsoy.customerarchive.model.dto.CustomerDto;
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
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto saveCustomer = customerService.saveCustomer(customerDto);
        return ResponseEntity.ok(saveCustomer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("/customer/{identityNumber}")
    public ResponseEntity<CustomerDto> getCustomerByIdentityNumber(
            @RequestParam(name = "identityNumber") String identityNumber) {
        CustomerDto customerDto = customerService.getCustomerByIdentityNumber(identityNumber);
        return ResponseEntity.ok(customerDto);
    }

    @DeleteMapping("/customer/{identityNumber}")
    public ResponseEntity<?> deleteCustomerByIdentityNumber(@PathVariable(name = "identityNumber") String identityNumber) {

        customerService.deleteCustomerByIdentityNumber(identityNumber);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/customer/{identityNumber}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable(name = "identityNumber") String identityNumber,
            @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(identityNumber, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

}
