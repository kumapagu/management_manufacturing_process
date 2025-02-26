package com.example.management_manufacturing_process.controller

import com.example.management_manufacturing_process.entity.CaseEntity
import com.example.management_manufacturing_process.enums.CaseConditionType
import com.example.management_manufacturing_process.form.CaseForm
import com.example.management_manufacturing_process.service.CaseService
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 案件コントローラ
 *
 * @author kumagainaoyuki
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cases")
class CaseController {
    @Autowired
    lateinit var caseService: CaseService

    @GetMapping
    fun getCases(): List<CaseEntity> {
        return caseService.getCases()
    }

    @GetMapping("/{id}")
    fun getCase(
        @PathVariable id: Long
    ): CaseEntity {
        return caseService.getCase(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerCase(
        @RequestBody @Validated caseForm: CaseForm
    ): CaseEntity {
        val name = caseForm.name
        val conditionType = CaseConditionType.NOT_START.toString()
        val address = caseForm.address
        val clientId = caseForm.clientId

        return caseService.registerCase(name, conditionType, address, clientId)
    }

    @PutMapping("/{id}")
    fun updateCase(
        @PathVariable id: Long,
        @RequestBody @Valid caseForm: CaseForm
    ): CaseEntity {
        val name = caseForm.name
        val conditionType = CaseConditionType.toString()
        val address = caseForm.address
        val clientId = caseForm.clientId

        return caseService.updateCase(id, name, conditionType, address, clientId)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCase(
        @PathVariable id: Long
    ) {
        return caseService.deleteCase(id)
    }
}