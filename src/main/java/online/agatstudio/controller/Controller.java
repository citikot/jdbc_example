package online.agatstudio.controller;

import lombok.RequiredArgsConstructor;
import online.agatstudio.dao.CustomerDao;
import online.agatstudio.model.Customer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final CustomerDao dao;

    @PostMapping
    public Long createCustomer(@RequestBody Customer customer) {
        return dao.createCustomer(customer);
    }

    @GetMapping
    public Customer getCustomerById(@RequestParam long id) {
        return dao.getCustomerById(id);
    }

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer) {
        dao.updateCustomer(customer);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestParam long id) {
        dao.deleteCustomer(id);
    }
}
