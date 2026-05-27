package com.pos.controller;

import com.pos.modal.Customer;
import com.pos.payload.response.ApiResponse;
import com.pos.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws Exception {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) throws Exception {
        return ResponseEntity.ok(customerService.updateCustomer(id,customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) throws Exception {

        customerService.deleteCustomer(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted employee");
        return ResponseEntity.ok(apiResponse);

    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAll() throws Exception {


        return ResponseEntity.ok(customerService.getAllCustomers());

    }


@GetMapping("/search")
    public ResponseEntity<List<Customer>> search(@RequestParam String keyword) throws Exception {

        return ResponseEntity.ok(customerService.searchCustomers(keyword));

}

}
