package com.example.customerproject.service;

import com.example.customerproject.dao.CustomerRepository;
import com.example.customerproject.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public List<Customer> getListCustomer(){
        return customerRepository.findAll();
    }
    public Customer getCustomerByIIN(Long iin){
        return customerRepository.findByIin(iin);
    }
    public Customer getCustomerByName(String fullname){
        return customerRepository.findByFullName(fullname);
    }
    public Customer getCustomerByDocNum(String docNum){
        return customerRepository.findById(docNum).get();
    }
    public boolean deleteCustomerById(String docNum){
        try {
            customerRepository.deleteById(docNum);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
