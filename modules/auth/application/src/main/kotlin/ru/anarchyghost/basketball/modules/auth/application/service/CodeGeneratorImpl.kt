package ru.anarchyghost.basketball.modules.auth.application.service

import org.springframework.stereotype.Component
import ru.anarchyghost.basketball.modules.auth.CodeGenerator
import kotlin.random.Random

@Component
class CodeGeneratorImpl: CodeGenerator {
    override fun generate(): String {
        val random = Random.nextInt(1000, 9999).toString()
        return when (Random.nextInt(0, 3)) {
            0 -> {
                random.replaceRange(2..2, random.subSequence(0, 1))
            }
            1 -> {
                random.replaceRange(3..3, random.subSequence(1, 2))
            }
            2 -> {
                random.replaceRange(3..3, random.subSequence(2, 3))
            }
            else -> {
                random.replaceRange(0..0, random.subSequence(3, 4))
            }
        }
    }
}