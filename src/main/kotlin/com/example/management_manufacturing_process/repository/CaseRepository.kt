package com.example.management_manufacturing_process.repository

import com.example.management_manufacturing_process.entity.CaseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CaseRepository: JpaRepository<CaseEntity, Long> {
    fun findByOrderById(): List<CaseEntity>
}