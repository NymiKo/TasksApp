package com.easyprog.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserApi(val token: String?, val avatar: String?, val name: String?, val surname: String?, val email: String?, val answer: String)