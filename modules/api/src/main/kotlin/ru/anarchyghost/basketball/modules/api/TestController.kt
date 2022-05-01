package ru.anarchyghost.basketball.modules.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendAuthCodeUseCase

@Controller
class TestController {

    @Autowired
    private lateinit var useCase: SendAuthCodeUseCase

    @GetMapping("/test")
    fun test():ResponseEntity<String> {
        useCase.execute("", "")
        return ResponseEntity.ok("OK")
    }
}