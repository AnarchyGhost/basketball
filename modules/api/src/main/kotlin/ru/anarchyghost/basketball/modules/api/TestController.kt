package ru.anarchyghost.basketball.modules.api

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController {

    @GetMapping("/test")
    fun test():ResponseEntity<String> {
        return ResponseEntity.ok("OK")
    }
}