package com.diviso.graeshoppe.repository;

import com.diviso.graeshoppe.domain.Customer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Spring Data  repository for the Customer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByReference(String reference);
	
	Optional<Customer> findByContact_MobileNumber(Long mobileNumber);
}
