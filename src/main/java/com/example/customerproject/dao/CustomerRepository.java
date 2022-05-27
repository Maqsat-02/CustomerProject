package com.example.customerproject.dao;

import com.example.customerproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
Customer findByIin(Long iin);
Customer findByFullName(String fullName);
}
