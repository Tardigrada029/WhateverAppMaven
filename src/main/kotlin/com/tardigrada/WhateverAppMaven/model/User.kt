package com.tardigrada.WhateverAppMaven.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "tbl_users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val userId: Int,

    @Column(nullable = false, length = 50, name = "first_name")
    val firstName: String,

    @Column(nullable = false, length = 50, name = "last_name")
    val lastName: String,

    @Column(unique = true, nullable = false, length = 100, name = "email")
    val email: String,

    @Column(nullable = false, name = "date_of_birth")
    val dateOfBirth: LocalDate,

    @Column(nullable = false, length = 50, name = "street")
    val street: String,

    @Column(nullable = false, length = 50, name = "city")
    val city: String,

    @Column(nullable = false, length = 50, name = "postcode")
    val postcode: String,

    @Column(nullable = false, length = 50, name = "telephone_number")
    val telephoneNumber: String

)