package com.easyprog.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateTaskApi(val id_on_server: Int, val answer: String)