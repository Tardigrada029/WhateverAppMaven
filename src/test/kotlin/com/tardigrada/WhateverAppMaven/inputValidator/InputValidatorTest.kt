package com.tardigrada.WhateverAppMaven.inputValidator

import org.springframework.boot.test.context.SpringBootTest
import org.testng.Assert.*
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.time.LocalDate

@SpringBootTest
class InputValidatorTest {

    lateinit var inputValidator: InputValidator

    @BeforeMethod
    fun setup() {
        inputValidator = InputValidator()
    }

    // ***************************** inputCheck() ************************************************
    @Test
    fun `return true when all variables are not blank`() {
        // given
        val firstName = "Kate"
        val lastName = "Green"
        val email = "green@gmail.com"
        val dateOfBirth = LocalDate.of(1986, 4, 8)
        val street = "Long"
        val city = "London"
        val postcode = "E1 6AN"
        val telephoneNumber = "+447911123456"

        // when
        val result = inputValidator.inputCheck(firstName, lastName, email, dateOfBirth, street, city, postcode,
            telephoneNumber)

        // then
        assertTrue(result)
    }
    
    @Test
    fun `return false when all or some variables are blank`() {
        // given
        val firstName = "  "
        val lastName = ""
        val email = ""
        val dateOfBirth = LocalDate.of(1986, 4, 3)
        val street = ""
        val city = ""
        val postcode = ""
        val telephoneNumber = ""
        
        // when
        val result = inputValidator.inputCheck(firstName, lastName, email, dateOfBirth, street, city, postcode,
            telephoneNumber)
        
        // then
        assertFalse(result)
    }

    // ***************************** emailCheck() ************************************************
    @Test
    fun `return true when email is in correct format`() {
        // given
        val email = "green@gmail.com"
        
        // when
        val result = inputValidator.emailCheck(email)
        
        // then
        assertTrue(result)
    }

    @Test
    fun `return false when email is in incorrect format`() {
        // given
        val email = "green@emailcom"

        // when
        val result = inputValidator.emailCheck(email)

        // then
        assertFalse(result)
    }

}