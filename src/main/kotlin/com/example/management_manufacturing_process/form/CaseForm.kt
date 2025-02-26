package com.example.management_manufacturing_process.form

import com.example.management_manufacturing_process.entity.ClientEntity
import com.example.management_manufacturing_process.enums.CaseConditionType
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import lombok.Data
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address

@Data
data class CaseForm (
    @NotEmpty(message = "案件名を入力してください")
    @Size(max = 255, message = "名前は255文字以内で入力してください")
    var name: String,

    @NotEmpty(message = "案件状態を選択してください")
    @Size(max = 255, message = "案件状態は255文字以内で入力してください")
    var conditionType: String,

    @Size(max = 255, message = "住所は255文字以内で入力してください")
    var address: String,

    @NotEmpty(message = "取引先を選択してください")
    var clientId: Long
)