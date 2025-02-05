package com.example.management_manufacturing_process.repository

import com.example.management_manufacturing_process.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository: JpaRepository<ClientEntity, Long> {
    fun findByNameKanaContainingOrderByIdAsc(nameKana: String): List<ClientEntity>

    fun findByOrderById(): List<ClientEntity>
}