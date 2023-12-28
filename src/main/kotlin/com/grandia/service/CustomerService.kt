package com.grandia.service

import com.grandia.model.Customer
import com.grandia.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
    fun findAllCustomers(): List<Customer> = customerRepository.findAll()

    fun findCustomerById(id: Long): Optional<Customer> = customerRepository.findById(id)

    fun saveCustomer(customer: Customer): Customer = customerRepository.save(customer)

    fun deleteCustomerById(id: Long) = customerRepository.deleteById(id)
}