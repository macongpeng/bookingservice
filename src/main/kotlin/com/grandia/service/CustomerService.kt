package com.grandia.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import com.grandia.model.Customer
import com.grandia.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import java.util.*

@Service
class CustomerService(private val customerRepository: CustomerRepository, private val passwordEncoder: BCryptPasswordEncoder) {
    fun findAllCustomers(): List<Customer> = customerRepository.findAll()

    fun getAllCustomers(pageable: Pageable): Page<Customer> {
        return customerRepository.findAll(pageable)
    }

    fun findCustomerById(id: Long): Optional<Customer> = customerRepository.findById(id)

    fun saveCustomer(customer: Customer): Customer = customerRepository.save(customer)

    fun signUp(newCustomer: Customer): Customer {
        if (customerRepository.existsByEmail(newCustomer.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer already exists")
        }
        newCustomer.password = passwordEncoder.encode(newCustomer.password)
        return customerRepository.save(newCustomer)
    }

    fun deleteCustomerById(id: Long) = customerRepository.deleteById(id)
}