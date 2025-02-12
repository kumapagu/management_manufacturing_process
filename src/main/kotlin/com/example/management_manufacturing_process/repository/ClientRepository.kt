package com.example.management_manufacturing_process.repository

import com.example.management_manufacturing_process.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * クライアントリポジトリ
 *
 * @author kumagainaoyuki
 */
@Repository
interface ClientRepository: JpaRepository<ClientEntity, Long> {
    /**
     * クライアント名カナで検索
     *
     * @param nameKana クライアント名カナ
     * @return クライアント一覧
     */
    fun findByNameKanaContainingOrderByIdAsc(nameKana: String): List<ClientEntity>

    /**
     * クライアント一覧をID順で取得
     *
     * @return クライアント
     */
    fun findByOrderById(): List<ClientEntity>
}