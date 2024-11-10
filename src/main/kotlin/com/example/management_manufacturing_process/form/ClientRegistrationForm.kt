package com.example.management_manufacturing_process.form

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import lombok.Data

@Data
data class ClientRegistrationForm (
    @field:NotEmpty(message = "取引先名を入力してください")
    @field:Size(max = 255, message = "名前は255文字以内で入力してください")
    var name: String,

    @field:NotEmpty(message = "取引先名(カナ)を入力してください")
    @field:Size(max = 255, message = "名前(カナ)は255文字以内で入力してください")
    var nameKana: String
)