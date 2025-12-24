package com.nitish.android.karto.data.login.mapper

import com.nitish.android.karto.data.login.dto.NetworkLoginResponse
import com.nitish.android.karto.domain.login.model.UserDetails

fun NetworkLoginResponse.toUserDetails(): UserDetails {
    return UserDetails(
        id = id,
        username = username,
        email = email,
        firstName = firstName,
        lastName = lastName,
        image = image,
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}