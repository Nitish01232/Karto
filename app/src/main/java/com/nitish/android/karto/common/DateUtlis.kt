package com.nitish.android.karto.common

import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

const val DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy"

fun formatDate(
    dateString: String,
    inputFormat: String,
    outputFormat: String
): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern(inputFormat)
        val outputFormatter = DateTimeFormatter.ofPattern(outputFormat)

        val parsedDate = if (dateString.endsWith("Z")) {
            ZonedDateTime.parse(dateString)
        } else {
            LocalDateTime.parse(dateString, inputFormatter)
        }

        outputFormatter.format(parsedDate)
    } catch (_: Exception) {
        ""
    }
}