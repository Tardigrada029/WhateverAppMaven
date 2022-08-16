package com.tardigrada.WhateverAppMaven.service

import  com.tardigrada.WhateverAppMaven.inputValidator.InputValidator
import com.tardigrada.WhateverAppMaven.model.User
import com.tardigrada.WhateverAppMaven.repository.UserRepository
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.testng.Assert.*
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.time.LocalDate
import java.util.*
import kotlin.NoSuchElementException

@SpringBootTest
class UserServiceTest {

    lateinit var mockUserRepository: UserRepository
    lateinit var mockInputValidator: InputValidator
    lateinit var userService: UserService

    val userId = 0
    val firstName = "Kate"
    val lastName = "Green"
    val email = "green@gmail.com"
    val dateOfBirth = LocalDate.of(1986, 4, 8)
    val street = "Long"
    val city = "London"
    val postcode = "E1 6AN"
    val telephoneNumber = "+447911123456"
    val user = User(userId, firstName, lastName, email, dateOfBirth, street, city, postcode, telephoneNumber)


    @BeforeMethod
    fun setupBeforeClass() {
        mockUserRepository = mock(UserRepository::class.java)
        mockInputValidator = mock(InputValidator::class.java)
        userService = UserService(mockUserRepository, mockInputValidator)
    }

    // ***************************** saveUser() ************************************************
    @Test
    fun `should create new user`() {
        // given
        `when`(mockInputValidator.inputCheck(firstName, lastName, email, dateOfBirth, street, city, postcode,
            telephoneNumber)).thenReturn(true)
        `when`(mockInputValidator.emailCheck(email)).thenReturn(true)
        `when`(mockUserRepository.save(user)).thenReturn(user)

        // when
        val savedUser = userService.saveUser(user)

        // then
        assertEquals(savedUser, user)
    }

    @Test
    fun `should throw IllegalArgumentException when there is an incorrect email format while creating new user`() {
        // given
        `when`(mockInputValidator.inputCheck(firstName, lastName, email, dateOfBirth, street, city, postcode,
            telephoneNumber)).thenReturn(true)
        `when`(mockInputValidator.emailCheck(email)).thenReturn(false)
        `when`(mockUserRepository.save(user)).thenReturn(user)

        // when & then
        expectThrows(IllegalArgumentException::class.java) { userService.saveUser(user) }
    }

    @Test
    fun `should throw IllegalArgumentException when there is blank variable while creating new user`() {
        // given
        `when`(mockInputValidator.inputCheck(firstName, lastName, email, dateOfBirth, street, city, postcode,
            telephoneNumber)).thenReturn(false)
        `when`(mockInputValidator.emailCheck(email)).thenReturn(true)
        `when`(mockUserRepository.save(user)).thenReturn(user)

        // when & then
        expectThrows(IllegalArgumentException::class.java) { userService.saveUser(user) }
    }

    @Test
    // @Ignore
    fun `should throw IllegalArgumentException while creating new user with existing email`() {
        // given
        `when`(mockInputValidator.inputCheck(firstName, lastName, email, dateOfBirth, street, city, postcode,
            telephoneNumber)).thenReturn(true)
        `when`(mockInputValidator.emailCheck(email)).thenReturn(true)
        `when`(mockUserRepository.findAll()).thenReturn(mutableListOf(user))
        `when`(mockUserRepository.save(user)).thenReturn(user)

        // when & then
        expectThrows(IllegalArgumentException::class.java) { userService.saveUser(user) }
    }

    // ***************************** getUsers() ************************************************
    @Test
    fun `should return all existing saved users`() {
        // given
        `when`(mockUserRepository.findAll()).thenReturn(mutableListOf(user))
        
        // when
        val numberOfUsers = userService.getUsers().count()
        
        // then
        assertEquals(numberOfUsers, 1)
    }

    // ***************************** getUserById() ************************************************
    @Test
    fun `should return existing saved user with given id`() {
        // given
        `when`(mockUserRepository.findById(0)).thenReturn(Optional.of(user))

        // when
        val result = userService.getUserById(0)

        // then
        assertEquals(user, result)
    }

    @Test
    fun `should throw NoSuchElementException when there is no saved user`() {
        // given
        `when`(mockUserRepository.findById(0)).thenReturn(Optional.empty())

        // when & then
        expectThrows(NoSuchElementException::class.java) { userService.getUserById(0) }
    }

    // ***************************** updateUserById() ************************************************
    @Test
    fun `should update existing user with given id`() {
        // given
        `when`(mockUserRepository.findById(0)).thenReturn(Optional.of(user))
        `when`(mockInputValidator.inputCheck(firstName, lastName, email, dateOfBirth, street, city, postcode,
            telephoneNumber)).thenReturn(true)
        `when`(mockInputValidator.emailCheck(email)).thenReturn(true)
        `when`(mockUserRepository.save(user)).thenReturn(user)
        // looks like it's working, but I'm not sure (no idea what to do with Unit type)
        doNothing().`when`(mockUserRepository).deleteById(userId)

        // when
        val result = userService.updateUserById(user, userId)
        
        // then
        assertEquals(result, user)
    }

    @Test
    fun `should throw NoSuchElementException when user with given id is not present to update`() {
        // given
        `when`(mockUserRepository.findById(0)).thenReturn(Optional.empty())
        `when`(mockInputValidator.inputCheck(firstName, lastName, email, dateOfBirth, street, city, postcode,
            telephoneNumber)).thenReturn(true)
        `when`(mockInputValidator.emailCheck(email)).thenReturn(true)
        `when`(mockUserRepository.save(user)).thenReturn(user)
        // looks like it's working, but I'm not sure (no idea what to do with Unit type)
        doNothing().`when`(mockUserRepository).deleteById(userId)

        // when & then
        expectThrows(NoSuchElementException::class.java) { userService.updateUserById(user, userId) }
    }

    // ***************************** deleteUserById() ************************************************
    @Test
    fun `should delete existing user with given id`() {
        // given
        `when`(mockUserRepository.findById(userId)).thenReturn(Optional.of(user))
        // looks like it's working, but I'm not sure (no idea what to do with Unit type)
        doNothing().`when`(mockUserRepository).deleteById(userId)

        // when
        val result = userService.deleteUserById(userId)

        // then
        assertEquals(result, Unit)
    }
    
    @Test
    fun `should throw NoSuchElementException when user with given id is not present to delete`() {
        // given
        `when`(mockUserRepository.findById(userId)).thenReturn(Optional.empty())
        // looks like it's working, but I'm not sure (no idea what to do with Unit type)
        doNothing().`when`(mockUserRepository).deleteById(userId)
        
        // when & then
        expectThrows(NoSuchElementException::class.java) { userService.deleteUserById(userId) }
    }
}