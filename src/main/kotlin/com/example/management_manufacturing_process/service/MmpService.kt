package com.example.management_manufacturing_process.service

import com.example.management_manufacturing_process.entity.ClientEntity
import com.example.management_manufacturing_process.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MmpService {
    @Autowired
    lateinit var clientRepository: ClientRepository
    fun getClients(): List<ClientEntity> {
        return clientRepository.findAll()
    }

    fun getClient(id: Long): ClientEntity {
        return clientRepository.getReferenceById(id)
    }

    fun registerClient(name: String, nameKana: String): ClientEntity {
        val clientEntity = ClientEntity(name = name, nameKana = nameKana)
        return clientRepository.save(clientEntity)
    }

    fun updateClient(id: Long, name: String, nameKana: String): ClientEntity {
        val clientEntity = clientRepository.getReferenceById(id)
        clientEntity.name = name
        clientEntity.nameKana = nameKana
        return clientRepository.save(clientEntity)
    }
}