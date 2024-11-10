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
        return clientRepository.getAllBy()
    }

    fun registerClient(name: String, nameKana: String): ClientEntity {
        val clientEntity = ClientEntity(name = name, nameKana = nameKana)
        return clientRepository.save(clientEntity)
    }
}