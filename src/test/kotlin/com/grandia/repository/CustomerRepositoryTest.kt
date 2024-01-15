package com.grandia.repository

import com.grandia.model.Customer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.PageRequest
import org.junit.jupiter.api.Assertions.*

@DataJpaTest
class CustomerRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val customerRepository: CustomerRepository
) {

    @Test
    fun `When findByEmail then return Customer`() {
        val customer = Customer(name = "Test Customer", password = "test", email = "test@example.com")
        entityManager.persist(customer)
        entityManager.flush()

        val found = customerRepository.findByEmail(customer.email)
        assertNotNull(found)
        assertEquals(found?.email, customer.email)
        assertEquals(found?.password, customer.password)
    }

    @Test
    fun `When save customer then customer is created`() {
        val customer = Customer(name = "New Customer", password = "newpass", email = "new@example.com")
        customerRepository.save(customer)

        val found = customerRepository.findByEmail(customer.email)
        assertNotNull(found)
        assertEquals(customer.email, found?.email)
        assertEquals(customer.password, found?.password)
    }

    @Test
    fun `When findAll then return paginated customers`() {
        val customer1 = Customer(name = "Customer1", password = "pass1", email = "customer1@example.com")
        val customer2 = Customer(name = "Customer2", password = "pass2", email = "customer2@example.com")
        entityManager.persist(customer1)
        entityManager.persist(customer2)
        entityManager.flush()
    
        val pageable: Pageable = PageRequest.of(0, 10)
        val customersPage = customerRepository.findAll(pageable)
    
        assertNotNull(customersPage)
        assertTrue(customersPage.content.size == 2)
        assertTrue(customersPage.content.containsAll(listOf(customer1, customer2)))
    }
    
    @Test
    fun `When existsByEmail with existing email then return true`() {
        val customer = Customer(name = "Existing Customer", password = "password", email = "exists@example.com")
        entityManager.persist(customer)
        entityManager.flush()
    
        val exists = customerRepository.existsByEmail(customer.email)
    
        assertTrue(exists)
    }
    
    @Test
    fun `When existsByEmail with non-existing email then return false`() {
        val nonExistingEmail = "nonexistent@example.com"
        val exists = customerRepository.existsByEmail(nonExistingEmail)
    
        assertFalse(exists)
    }

    @Test
    fun `When a customer is deleted then the customer should be removed`() {
        val customer = Customer(name = "Eve Brown", password = "password", email = "eve@example.com", phoneNumber = "4564564567")
        val savedCustomer = customerRepository.save(customer)

        customerRepository.deleteById(savedCustomer.id!!)
        val foundCustomer = customerRepository.findById(savedCustomer.id!!)
        assertFalse(foundCustomer.isPresent)
    }

}