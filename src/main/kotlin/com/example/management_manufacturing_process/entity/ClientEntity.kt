package com.example.management_manufacturing_process.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "client")
data class ClientEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,

    @Column(name = "client_name")
    var clientName: String = "",

    @Column(name = "client_name_kana")
    var clientNameKana: String = "",

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null

)