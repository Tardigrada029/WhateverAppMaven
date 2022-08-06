package com.tardigrada.WhateverAppMaven.inputValidator

class InputValidator {

    fun inputCheck(firstName: String, lastName: String, email: String, dateOfBirth: String, street: String,
                   city: String, postcode: String, telephoneNumber: String): Boolean {
        return (firstName.isNotEmpty() || lastName.isNotEmpty() || email.isNotEmpty() || dateOfBirth.isNotEmpty() ||
                street.isNotEmpty() || city.isNotEmpty() || postcode.isNotEmpty() || telephoneNumber.isNotEmpty())
    }

    fun emailCheck(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return emailRegex.toRegex().matches(email)
    }

}