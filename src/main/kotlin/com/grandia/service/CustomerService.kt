package com.grandia.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import com.grandia.model.Customer
import com.grandia.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
    fun findAllCustomers(): List<Customer> = customerRepository.findAll()

    fun getAllCustomers(pageable: Pageable): Page<Customer> {
        return customerRepository.findAll(pageable)
    }

    fun findCustomerById(id: Long): Optional<Customer> = customerRepository.findById(id)

    fun saveCustomer(customer: Customer): Customer = customerRepository.save(customer)

    fun deleteCustomerById(id: Long) = customerRepository.deleteById(id)
}