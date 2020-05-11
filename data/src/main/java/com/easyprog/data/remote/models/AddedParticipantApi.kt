package com.easyprog.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class AddedParticipantApi(val token: String, val name: String, val surname: String, val avatar: String, val answer: String)