package com.tardigrada.WhateverAppMaven.inputValidator

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class InputValidator {

    fun inputCheck(firstName: String, lastName: String, email: String, dateOfBirth: LocalDate, street: String,
                   city: String, postcode: String, telephoneNumber: String): Boolean {
        return (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() &&
                dateOfBirth.toString().isNotBlank() && street.isNotBlank() && city.isNotBlank() &&
                postcode.isNotBlank() && telephoneNumber.isNotBlank())
    }

    fun emailCheck(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return emailRegex.toRegex().matches(email)
    }

}