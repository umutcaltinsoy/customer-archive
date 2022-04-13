package com.altinsoy.customerarchive.repository;

import com.altinsoy.customerarchive.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByIdentityNumber(String identityNumber);
    void deleteByIdentityNumber(String identityNumber);
}
