package com.grandia.service

import com.grandia.model.Customer
import com.grandia.repository.CustomerRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito

class CustomerServiceTest {

    private val customerRepository: CustomerRepository = Mockito.mock(CustomerRepository::class.java)
    private val customerService = CustomerService(customerRepository)

    @Test
    fun `When saveCustomer then customer is saved`() {
        val customer = Customer(name = "Test Customer", email = "test@example.com")
        Mockito.`when`(customerRepository.save(Mockito.any())).thenReturn(customer)

        val savedCustomer = customerService.saveCustomer(customer)
        Mockito.verify(customerRepository).save(customer)
        assertEquals(savedCustomer.email, customer.email)
    }
}
