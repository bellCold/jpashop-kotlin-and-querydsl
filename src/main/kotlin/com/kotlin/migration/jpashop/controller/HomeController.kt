package com.kotlin.migration.jpashop.controller

import com.kotlin.migration.jpashop.global.logger
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeController {
    val log = logger()

    @RequestMapping("/")
    fun home(): String? {
        log.info("home controller")
        return "home"
    }
}