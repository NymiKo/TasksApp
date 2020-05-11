package com.easyprog.domain.models

data class CreateTaskForParticipant(val name: String, val description: String, val color: String, val end_date: String,
                                    val type: String, val creator: String, val participants: MutableList<String>?)