package com.grandia.service

import com.grandia.model.Customer
import com.grandia.repository.CustomerRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class CustomerServiceTest {

    private val customerRepository: CustomerRepository = Mockito.mock(CustomerRepository::class.java)
    private val passwordEncoder: BCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder::class.java)
    private val customerService = CustomerService(customerRepository, passwordEncoder)

    @Test
    fun `When saveCustomer then customer is saved`() {
        val customer = Customer(name = "Test Customer", password = "test", email = "test@example.com")
        Mockito.`when`(customerRepository.save(Mockito.any())).thenReturn(customer)

        val savedCustomer = customerService.saveCustomer(customer)
        Mockito.verify(customerRepository).save(customer)
        assertEquals(savedCustomer.email, customer.email)
        assertEquals(savedCustomer.password, customer.password)
    }
}
