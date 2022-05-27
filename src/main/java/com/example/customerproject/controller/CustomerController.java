package com.example.customerproject.controller;

import com.example.customerproject.model.Customer;
import com.example.customerproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(value = "",
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomer() {
        return customerService.getListCustomer();
    }

    @GetMapping(value = "getByName/{name}",
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerByName(@PathVariable String name) {
        return customerService.getCustomerByName(name);
    }
    @GetMapping(value = "getByDocNum/{docNum}",
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerByDocNum(@PathVariable String docNum) {
        return customerService.getCustomerByDocNum(docNum);
    }
    @GetMapping(value = "getByIIN/{iin}",
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerByIin(@PathVariable Long iin) {
        return customerService.getCustomerByIIN(iin);
    }

    @PostMapping(value = "/save",
            consumes = {"application/json"},
            produces = {"application/json"})
    public Customer save(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
    @DeleteMapping(value = "/delete/{docNum}")
    public boolean deleteByDocNum(@PathVariable String docNum){
        return customerService.deleteCustomerById(docNum);
    }
}
