package com.easyprog.domain.models

data class ChangePassword(val token: String, val oldPassword: String, val newPassword: String)