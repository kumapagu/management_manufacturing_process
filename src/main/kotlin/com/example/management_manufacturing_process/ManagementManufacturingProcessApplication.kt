package com.example.management_manufacturing_process

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.TimeZone

@SpringBootApplication
@EnableJpaAuditing
class ManagementManufacturingProcessApplication

fun main(args: Array<String>) {
	runApplication<ManagementManufacturingProcessApplication>(*args)
}

@PostConstruct
fun init() {
	TimeZone.setDefault(TimeZone.getTimeZone("JST"))
}
