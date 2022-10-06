package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5432")
@RestController
@RequestMapping("/customerapi")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    //public ResponseEntity<List><Customer>> getAllCustomers(String name)
    public ResponseEntity<List<Customer>> getAllCustomers(String name)
    {
        try{
            List<Customer> customers= new ArrayList<Customer>();

            if(name==null)
                customerRepository.findAll().forEach(customers::add);
            else
                customerRepository.findByTitleContaining(name).forEach(customers::add);
            if(customers.isEmpty()){
                return new ResponseEntity<>(customers, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getTutorialById(@PathVariable("id") long id) {
        Customer customer = customerRepository.findById(id);

        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createTutorial(@RequestBody Customer customer) {
        try {
            customerRepository.save(new Customer(customer.getName(), customer.getNumber()));
            return new ResponseEntity<>("Customer was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") long id, @RequestBody Customer customer) {
        Customer _tutorial = customerRepository.findById(id);

        if (_tutorial != null) {
            _tutorial.setId(id);
            _tutorial.setName(customer.getName());
            _tutorial.setNumber(customer.getNumber());

            customerRepository.update(_tutorial);
            return new ResponseEntity<>("Tutorial was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Tutorial with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable("id") long id) {
        try {
            int result = customerRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Customer with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Customer was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers")
    public ResponseEntity<String> deleteAllTutorials() {
        try {
            int numRows = customerRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Customer(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
