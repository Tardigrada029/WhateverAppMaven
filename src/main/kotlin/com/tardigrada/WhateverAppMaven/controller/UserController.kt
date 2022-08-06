package com.tardigrada.WhateverAppMaven.controller

import com.tardigrada.WhateverAppMaven.model.User
import com.tardigrada.WhateverAppMaven.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleNotFound(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getUsers() = userService.getUsers()

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Int) = userService.getUserById(userId)

    @PostMapping
    fun saveUser(@RequestBody user: User) = userService.saveUser(user)

    @PatchMapping("/{userId}")
    fun updateUserById(@PathVariable userId: Int, @RequestBody user: User) = userService.updateUserById(user, userId)

    @DeleteMapping("/{userId}")
    fun deleteUserById(@PathVariable userId: Int) = userService.deleteUserById(userId)

}