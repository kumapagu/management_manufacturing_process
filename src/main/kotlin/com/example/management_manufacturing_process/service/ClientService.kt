package com.example.management_manufacturing_process.service

import com.example.management_manufacturing_process.entity.ClientEntity
import com.example.management_manufacturing_process.repository.ClientRepository
import io.micrometer.common.util.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 案件管理サービス
 *
 * @author kumagainaoyuki
 */
@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository

    /**
     * クライアント一覧取得
     *
     * @param nameKana クライアント名カナ(任意)
     * @return クライアント一覧
     */
    fun getClients(nameKana: String): List<ClientEntity> {
        if (StringUtils.isBlank(nameKana)) {
            return clientRepository.findByOrderById()
        }
        return clientRepository.findByNameKanaContainingOrderByIdAsc(nameKana)
    }

    /**
     * クライアント取得
     *
     * @param id クライアントID
     * @return クライアント
     */
    fun getClient(id: Long): ClientEntity {
        return clientRepository.getReferenceById(id)
    }

    /**
     * クライアント登録
     *
     * @param name クライアント名
     * @param nameKana クライアント名カナ
     * @return クライアント
     */
    fun registerClient(name: String, nameKana: String): ClientEntity {
        val clientEntity = ClientEntity(name = name, nameKana = nameKana)
        return clientRepository.save(clientEntity)
    }

    /**
     * クライアント更新
     *
     * @param id クライアントID
     * @param name クライアント名
     * @param nameKana クライアント名カナ
     * @return クライアント
     */
    fun updateClient(id: Long, name: String, nameKana: String): ClientEntity {
        val clientEntity = clientRepository.getReferenceById(id)
        clientEntity.name = name
        clientEntity.nameKana = nameKana
        return clientRepository.save(clientEntity)
    }

    /**
     * クライアント削除
     *
     * @param id クライアントID
     */
    fun deleteClient(id: Long) {
        clientRepository.deleteById(id)
    }
}