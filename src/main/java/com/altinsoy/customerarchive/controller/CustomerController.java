package com.altinsoy.customerarchive.controller;

import com.altinsoy.customerarchive.core.utilities.DataResult;
import com.altinsoy.customerarchive.core.utilities.Result;
import com.altinsoy.customerarchive.model.dto.CustomerDto;
import com.altinsoy.customerarchive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<DataResult<List<CustomerDto>>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @PostMapping("/customer")
    public ResponseEntity<DataResult<CustomerDto>> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
        DataResult<CustomerDto> saveCustomer = customerService.saveCustomer(customerDto);
        return ResponseEntity.ok(saveCustomer);
    }

    @GetMapping("/customer/{identityNumber}")
    public ResponseEntity<DataResult<CustomerDto>> getCustomerByIdentityNumber(
            @RequestParam(name = "identityNumber") String identityNumber) {
        DataResult<CustomerDto> customerDto = customerService.getCustomerByIdentityNumber(identityNumber);
        return ResponseEntity.ok(customerDto);
    }

    @DeleteMapping("/customer/{identityNumber}")
    public ResponseEntity<Result> deleteCustomerByIdentityNumber(@PathVariable(name = "identityNumber") String identityNumber) {

        Result result = customerService.deleteCustomerByIdentityNumber(identityNumber);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/customer/{identityNumber}")
    public ResponseEntity<DataResult<CustomerDto>> updateCustomer(
            @PathVariable(name = "identityNumber") String identityNumber,
            @Valid @RequestBody CustomerDto customerDto) {
        DataResult<CustomerDto> updatedCustomer = customerService.updateCustomer(identityNumber, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

}
