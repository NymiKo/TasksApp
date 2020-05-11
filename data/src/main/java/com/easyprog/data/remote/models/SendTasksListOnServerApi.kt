package com.easyprog.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class SendTasksListOnServerApi(val id_on_server: Int, val name: String,
                                    val description: String, val color: String, val end_date: String,
                                    val type: String, val creator: Boolean)