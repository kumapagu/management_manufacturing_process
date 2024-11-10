package com.example.management_manufacturing_process.controller

import com.example.management_manufacturing_process.entity.ClientEntity
import com.example.management_manufacturing_process.service.MmpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MmpController {
    @Autowired
    lateinit var mmpService: MmpService
    @GetMapping("/")
    fun getCases(): List<ClientEntity> {
        return mmpService.getClients()
    }
}