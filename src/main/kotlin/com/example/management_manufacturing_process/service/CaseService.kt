package com.example.management_manufacturing_process.service

import com.example.management_manufacturing_process.entity.CaseEntity
import com.example.management_manufacturing_process.enums.CaseConditionType
import com.example.management_manufacturing_process.repository.CaseRepository
import com.example.management_manufacturing_process.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class CaseService {
    @Autowired
    lateinit var caseRepository: CaseRepository
    @Autowired
    lateinit var clientRepository: ClientRepository

    fun getCases(): List<CaseEntity> {
        return caseRepository.findByOrderById()
    }

    fun getCase(id: Long): CaseEntity {
        return caseRepository.findById(id).orElseThrow { IllegalArgumentException("案件が見つかりません") }
    }

    fun registerCase(
        name: String, conditionType: String, address: String, clientId: Long
    ): CaseEntity {
        val clientEntity = clientRepository.findById(clientId).orElseThrow { throw IllegalArgumentException("クライアントが見つかりません") }
        val caseEntity = CaseEntity(name = name, conditionType = conditionType, address = address, client = clientEntity)
        return caseRepository.save(caseEntity)
    }

    fun updateCase(id: Long, name: String, conditionType: String, address: String, clientId: Long): CaseEntity {
        val clientEntity = clientRepository.findById(clientId).orElseThrow { throw IllegalArgumentException("クライアントが見つかりません") }
        val caseEntity = caseRepository.findById(id).orElseThrow { throw IllegalArgumentException("案件が見つかりません") }
        caseEntity.name = name
        caseEntity.conditionType = conditionType
        caseEntity.address = address
        caseEntity.client = clientEntity
        return caseRepository.save(caseEntity)
    }

    fun deleteCase(id: Long) {
        return caseRepository.deleteById(id)
    }
}