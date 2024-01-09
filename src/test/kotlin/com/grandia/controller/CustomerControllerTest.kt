package com.grandia.controller

import com.grandia.model.Customer
import com.grandia.repository.CustomerRepository
import com.grandia.service.CustomerService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.junit.jupiter.api.Assertions.*

class CustomerControllerTest {

    private val customerService: CustomerService = Mockito.mock(CustomerService::class.java)
    private val customerController = CustomerController(customerService)

    @Test
    fun `When getCustomerById then return Customer`() {
        val customer = Customer(name = "Test Customer", password = "test", email = "test@example.com")
        Mockito.`when`(customerService.findCustomerById(Mockito.anyLong())).thenReturn(java.util.Optional.of(customer))

        val response = customerController.getCustomerById(1L)
        assertEquals(response.statusCode, HttpStatus.OK)
        assertNotNull(response.body)
        assertEquals(response.body?.email, customer.email)
        assertEquals(response.body?.password, customer.password)
    }
}
