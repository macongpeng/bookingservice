package com.grandia.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import com.grandia.model.Customer
import com.grandia.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    //@GetMapping
    //fun getAllCustomers(): ResponseEntity<List<Customer>> = ResponseEntity.ok(customerService.findAllCustomers())

    @GetMapping
    fun listCustomers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<Customer> {
        val pageable = PageRequest.of(page, size)
        return customerService.getAllCustomers(pageable)
    }

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: Long): ResponseEntity<Customer> =
        customerService.findCustomerById(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Customer> =
        ResponseEntity.ok(customerService.saveCustomer(customer))

    @PostMapping("/signup")

    fun signUp(@RequestBody newCustomer: Customer): Customer {
        return customerService.signUp(newCustomer)
    }
    
    @GetMapping("/active-account")
    fun confirmAccount(@RequestParam("token") confirmationToken: String) {
        val customer = customerService.findByConfirmationToken(confirmationToken)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This token does not exist")

        customer.confirmationToken = null
        customerService.saveCustomer(customer)
    }    

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