package ru.anarchyghost.basketball.modules.review.domain

data class Rating(
    val rate: Int
) {
    companion object {
        fun create(rate: Int): Rating {
            check(rate in 1..5) {"Rate should be from 1 to 5"}
            return Rating(rate)
        }
    }
}