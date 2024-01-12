package com.grandia.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import com.grandia.model.Customer
import com.grandia.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import java.util.*
import java.util.UUID

@Service
class CustomerService(private val customerRepository: CustomerRepository, private val passwordEncoder: BCryptPasswordEncoder, private val mailSender: JavaMailSender) {
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
        newCustomer.confirmationToken = UUID.randomUUID().toString()

        val savedCustomer = customerRepository.save(newCustomer)

        val mailMessage = SimpleMailMessage()
        mailMessage.setTo(newCustomer.email)
        mailMessage.setSubject("Complete Registration!")
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/active-account?token="+newCustomer.confirmationToken)

        mailSender.send(mailMessage)

        return savedCustomer
    }

    fun findByConfirmationToken(confirmationToken: String): Customer? {
        return customerRepository.findByConfirmationToken(confirmationToken)
    }    

    fun deleteCustomerById(id: Long) = customerRepository.deleteById(id)
}