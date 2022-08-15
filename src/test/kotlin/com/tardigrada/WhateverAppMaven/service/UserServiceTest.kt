package com.tardigrada.WhateverAppMaven.service

import  com.tardigrada.WhateverAppMaven.inputValidator.InputValidator
import com.tardigrada.WhateverAppMaven.repository.UserRepository
import org.mockito.Mockito.mock
import org.springframework.boot.test.context.SpringBootTest
import org.testng.Assert.*

@SpringBootTest
class UserServiceTest {

    private final val mockUserRepository: UserRepository = mock(UserRepository::class.java)
    private final val mockInputValidator: InputValidator = mock(InputValidator::class.java)
    val userService = UserService(mockUserRepository, mockInputValidator)

    /*
    @Test
    fun `should return existing saved user`() {
        // given
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

        whenever(mockUserRepository.findById(0).get()).thenReturn(user)

        // when
        val result = userService.getUserById(0)

        // then
        assertEquals(user, result)
    }
     */

}