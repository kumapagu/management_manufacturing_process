package com.example.management_manufacturing_process.controller

import com.example.management_manufacturing_process.entity.ClientEntity
import com.example.management_manufacturing_process.form.ClientRegistrationForm
import com.example.management_manufacturing_process.service.MmpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class MmpController {
    @Autowired
    lateinit var mmpService: MmpService

    @GetMapping("/clients")
    fun getClients(): List<ClientEntity> {
        return mmpService.getClients()
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerClient(
        @RequestBody @Validated clientRegistrationForm: ClientRegistrationForm
    ): ClientEntity {
        return mmpService.registerClient(
            clientRegistrationForm.name,
            clientRegistrationForm.nameKana
        )
    }
}