package com.example.management_manufacturing_process.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MmpController {
    @GetMapping("/")
    fun getCases(): String {
        return "Test"
    }
}