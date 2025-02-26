package com.example.management_manufacturing_process.controller

import com.example.management_manufacturing_process.entity.ClientEntity
import com.example.management_manufacturing_process.form.ClientForm
import com.example.management_manufacturing_process.service.ClientService
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * クライアントコントローラ
 *
 * @author kumagainaoyuki
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
class ClientController {
    @Autowired
    lateinit var clientService: ClientService

    /**
     * クライアント一覧取得
     *
     * @param nameKana クライアント名カナ(任意)
     * @return クライアント一覧
     */
    @GetMapping
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
    @GetMapping("/{id}")
    fun getClient(
        @PathVariable id: Long
    ): ClientEntity {
        return clientService.getClient(id)
    }

    /**
     * クライアント登録
     *
     * @param clientForm クライアント登録フォーム
     * @return クライアント
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerClient(
        @RequestBody @Validated clientForm: ClientForm
    ): ClientEntity {
        return clientService.registerClient(
            clientForm.name,
            clientForm.nameKana
        )
    }

    /**
     * クライアント更新
     *
     * @param id クライアントID
     * @param clientForm クライアント登録フォーム
     * @return クライアント
     */
    @PutMapping("/{id}")
    fun updateClient(
        @PathVariable id: Long,
        @RequestBody @Validated clientForm: ClientForm
    ): ClientEntity {
        return clientService.updateClient(
            id,
            clientForm.name,
            clientForm.nameKana
        )
    }

    /**
     * クライアント削除
     *
     * @param id クライアントID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteClient(
        @PathVariable id: Long
    ) {
        clientService.deleteClient(id)
    }
}