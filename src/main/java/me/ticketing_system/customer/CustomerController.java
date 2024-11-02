package me.ticketing_system.customer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/{customerId}")
    public Customer getById(@PathVariable String customerId) {
        Optional<Customer> customer = customerRepository.findByID(customerId);

        if (customer.isEmpty()) {
            throw new CustomerNotFoundException();
        }

        return customer.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void create(@Valid @RequestBody Customer newCustomer) {
        customerRepository.createCustomer(newCustomer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping()
    public void update(@Valid @RequestBody Customer updatedCustomer) {
        customerRepository.updateCustomer(updatedCustomer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{customerId}")
    public void delete(@PathVariable String customerId) {
        customerRepository.deleteCustomer(customerId);
    }
}
