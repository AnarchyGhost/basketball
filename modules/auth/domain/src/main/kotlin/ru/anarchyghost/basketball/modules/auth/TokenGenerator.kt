package ru.anarchyghost.basketball.modules.auth

interface TokenGenerator {
    fun generate(): String
}