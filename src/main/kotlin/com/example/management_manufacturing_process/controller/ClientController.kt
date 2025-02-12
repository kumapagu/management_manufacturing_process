package com.example.management_manufacturing_process.controller

import com.example.management_manufacturing_process.entity.ClientEntity
import com.example.management_manufacturing_process.form.ClientRegistrationForm
import com.example.management_manufacturing_process.service.ClientService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 案件管理コントローラ
 *
 * @author kumagainaoyuki
 */
@RestController
@RequiredArgsConstructor
class ClientController {
    @Autowired
    lateinit var clientService: ClientService

    /**
     * クライアント一覧取得
     *
     * @param nameKana クライアント名カナ(任意)
     * @return クライアント一覧
     */
    @GetMapping("/clients")
    fun getClients(
        @RequestParam(value = "nameKana", required = false, defaultValue = "") nameKana: String
    ): List<ClientEntity> {
        return clientService.getClients(nameKana)
    }

    /**
     * クライアント取得
     *
     * @param id クライアントID
     * @return クライアント
     */
    @GetMapping("/clients/{id}")
    fun getClient(
        @PathVariable id: Long
    ): ClientEntity {
        return clientService.getClient(id)
    }

    /**
     * クライアント登録
     *
     * @param clientRegistrationForm クライアント登録フォーム
     * @return クライアント
     */
    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerClient(
        @RequestBody @Validated clientRegistrationForm: ClientRegistrationForm
    ): ClientEntity {
        return clientService.registerClient(
            clientRegistrationForm.name,
            clientRegistrationForm.nameKana
        )
    }

    /**
     * クライアント更新
     *
     * @param id クライアントID
     * @param clientRegistrationForm クライアント登録フォーム
     * @return クライアント
     */
    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateClient(
        @PathVariable id: Long,
        @RequestBody @Validated clientRegistrationForm: ClientRegistrationForm
    ): ClientEntity {
        return clientService.updateClient(
            id,
            clientRegistrationForm.name,
            clientRegistrationForm.nameKana
        )
    }

    /**
     * クライアント削除
     *
     * @param id クライアントID
     */
    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteClient(
        @PathVariable id: Long
    ) {
        clientService.deleteClient(id)
    }
}