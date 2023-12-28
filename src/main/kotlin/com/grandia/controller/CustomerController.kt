package com.grandia.controller

import com.grandia.model.Customer
import com.grandia.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping
    fun getAllCustomers(): ResponseEntity<List<Customer>> = ResponseEntity.ok(customerService.findAllCustomers())

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: Long): ResponseEntity<Customer> =
        customerService.findCustomerById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Customer> =
        ResponseEntity.ok(customerService.saveCustomer(customer))

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody customer: Customer): ResponseEntity<Customer> =
        if (customerService.findCustomerById(id).isPresent) {
            ResponseEntity.ok(customerService.saveCustomer(customer))
        } else {
            ResponseEntity.notFound().build()
        }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long): ResponseEntity<Void> {
        customerService.deleteCustomerById(id)
        return ResponseEntity.ok().build()
    }
}