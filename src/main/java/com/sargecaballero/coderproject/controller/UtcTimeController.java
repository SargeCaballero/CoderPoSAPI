package com.sargecaballero.coderproject.controller;

import com.sargecaballero.coderproject.externalservice.UTCTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${base.api.path}")
public class UtcTimeController {

    private final UTCTime utcTime;

    @Autowired
    public UtcTimeController(UTCTime utcTime) {
        this.utcTime = utcTime;
    }

    @GetMapping("/getUtcTime")
    public String getUtcTime() {
        return utcTime.getUtcTime();
    }
}