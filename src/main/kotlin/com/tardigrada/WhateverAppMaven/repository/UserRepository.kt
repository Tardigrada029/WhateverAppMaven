package com.tardigrada.WhateverAppMaven.repository

import com.tardigrada.WhateverAppMaven.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Int>