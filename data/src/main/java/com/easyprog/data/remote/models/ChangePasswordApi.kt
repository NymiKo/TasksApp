package com.easyprog.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordApi(val answer: String)