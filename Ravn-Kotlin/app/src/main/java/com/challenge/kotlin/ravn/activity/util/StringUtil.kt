package com.challenge.kotlin.ravn.activity.util

object StringUtil {
    @JvmStatic
    fun formatGraphQLQueryText(string: String?): String? {
        return if (string == null) {
            ""
        } else capitalize(
            string.replace(
                "[",
                ""
            ).replace("]", "")
        )
    }

    fun capitalize(str: String?): String? {
        var str = str
        if (str == null || str.isEmpty()) {
            return str
        }
        str = str.toLowerCase()
        return str.substring(0, 1).toUpperCase() + str.substring(1)
    }
}