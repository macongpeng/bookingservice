package com.grandia.service

import com.grandia.model.Customer
import com.grandia.repository.CustomerRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.server.ResponseStatusException
import java.util.Optional
import kotlin.test.assertFailsWith

class CustomerServiceTest {

    private val customerRepository: CustomerRepository = Mockito.mock(CustomerRepository::class.java)
    private val passwordEncoder: BCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder::class.java)
    private val mailSender: JavaMailSender = Mockito.mock(JavaMailSender::class.java);
    private val customerService = CustomerService(customerRepository, passwordEncoder, mailSender)

    @Test
    fun `When saveCustomer then customer is saved`() {
        val customer = Customer(name = "Test Customer", password = "test", email = "test@example.com")
        Mockito.`when`(customerRepository.save(Mockito.any())).thenReturn(customer)

        val savedCustomer = customerService.saveCustomer(customer)
        Mockito.verify(customerRepository).save(customer)
        assertEquals(savedCustomer.email, customer.email)
        assertEquals(savedCustomer.password, customer.password)
    }

    @Test
    fun `When getAllCustomers with pageable then return paginated customers`() {
        // Arrange
        val pageable: Pageable = PageRequest.of(0, 5)
        val customers = listOf(
            Customer(id = 1L, name = "John Doe", email = "john@example.com", password = "password123", confirmationToken = "token1"),
            Customer(id = 2L, name = "Jane Smith", email = "jane@example.com", password = "password456", confirmationToken = "token2")
        )
        val customerPage: Page<Customer> = PageImpl(customers, pageable, customers.size.toLong())

        Mockito.`when`(customerRepository.findAll(pageable)).thenReturn(customerPage)

        // Act
        val result = customerService.getAllCustomers(pageable)

        // Assert
        assertEquals(customerPage, result)
        assertEquals(customers.size, result.content.size)
        assertEquals(pageable, result.pageable)
        assertEquals(customers[0], result.content[0])
        assertEquals(customers[1], result.content[1])
    }

    @Test
    fun `When findCustomerById with existing id then return customer`() {
        // Arrange
        val customerId = 1L
        val mockCustomer = Customer(id = customerId, name = "John Doe", email = "john@example.com", password = "password123")
        val optionalCustomer = Optional.of(mockCustomer)
        Mockito.`when`(customerRepository.findById(customerId)).thenReturn(optionalCustomer)

        // Act
        val result = customerService.findCustomerById(customerId)

        // Assert
        assertTrue(result.isPresent)
        assertEquals(mockCustomer, result.get())
    }

    @Test
    fun `When findCustomerById with non-existing id then return empty`() {
        // Arrange
        val nonExistingId = 99L
        Mockito.`when`(customerRepository.findById(nonExistingId)).thenReturn(Optional.empty<Customer>())

        // Act
        val result = customerService.findCustomerById(nonExistingId)

        // Assert
        assertFalse(result.isPresent)
    }  
    
    @Test
    fun `When signUp with new customer then save and return customer`() {
        // Arrange
        val newCustomer = Customer(email = "new@example.com", name = "David.Johnson", password = "password")
        Mockito.`when`(customerRepository.existsByEmail(newCustomer.email)).thenReturn(false)
        Mockito.`when`(passwordEncoder.encode(newCustomer.password)).thenReturn("encodedPassword")

        Mockito.`when`(customerRepository.save(Mockito.any(Customer::class.java))).thenAnswer { it.arguments[0] }

        // Act
        val savedCustomer = customerService.signUp(newCustomer)

        // Assert
        assertNotNull(savedCustomer.confirmationToken)
        assertEquals("encodedPassword", savedCustomer.password)
        Mockito.verify(mailSender).send(Mockito.any(SimpleMailMessage::class.java))
    }

    @Test
    fun `When signUp with existing email then throw ResponseStatusException`() {
        // Arrange
        val existingCustomer = Customer(email = "existing@example.com", name = "David.Johnson", password = "password")
        Mockito.`when`(customerRepository.existsByEmail(existingCustomer.email)).thenReturn(true)

        // Act & Assert
        assertFailsWith<ResponseStatusException> {
        customerService.signUp(existingCustomer)
        }
    }

}
