package com.example.management_manufacturing_process.enums


enum class CaseConditionType(val value: String) {
    // 未着手
    NOT_START("notStart"),
    // 進行中
    IN_PROGRESS("inProgress"),
    // 完了
    COMPLETED("completed");

    companion object {
        fun fromString(value: String): CaseConditionType {
            return entries.find { it.value.equals(value, true) }
                ?: throw IllegalArgumentException("Unknown enum type $value")
        }
    }

    override fun toString(): String {
        return value
    }
}
