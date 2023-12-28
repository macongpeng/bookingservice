package com.grandia.repository

import com.grandia.model.Customer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.junit.jupiter.api.Assertions.*

@DataJpaTest
class CustomerRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val customerRepository: CustomerRepository
) {

    @Test
    fun `When findByEmail then return Customer`() {
        val customer = Customer(name = "Test Customer", email = "test@example.com")
        entityManager.persist(customer)
        entityManager.flush()

        val found = customerRepository.findByEmail(customer.email)
        assertNotNull(found)
        assertEquals(found?.email, customer.email)
    }
}