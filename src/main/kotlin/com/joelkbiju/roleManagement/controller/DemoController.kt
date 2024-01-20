package com.joelkbiju.roleManagement.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController("/api/v1/demo-controller")
class DemoController {
    @GetMapping
    fun hello(): ResponseEntity<String>{
        return ResponseEntity.ok("Hellooooo, it workedddd")
    }
}