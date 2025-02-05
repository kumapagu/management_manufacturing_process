package com.example.management_manufacturing_process.controller

import com.example.management_manufacturing_process.entity.ClientEntity
import com.example.management_manufacturing_process.form.ClientRegistrationForm
import com.example.management_manufacturing_process.service.MmpService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
class MmpController {
    @Autowired
    lateinit var mmpService: MmpService

    @GetMapping("/clients")
    fun getClients(
        @RequestParam(value = "nameKana", required = false, defaultValue = "") nameKana: String
    ): List<ClientEntity> {
        return mmpService.getClients(nameKana)
    }

    @GetMapping("/clients/{id}")
    fun getClient(
        @PathVariable id: Long
    ): ClientEntity {
        return mmpService.getClient(id)
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

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateClient(
        @PathVariable id: Long,
        @RequestBody @Validated clientRegistrationForm: ClientRegistrationForm
    ): ClientEntity {
        return mmpService.updateClient(
            id,
            clientRegistrationForm.name,
            clientRegistrationForm.nameKana
        )
    }

    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteClient(
        @PathVariable id: Long
    ) {
        mmpService.deleteClient(id)
    }
}