package com.comercialmoises.controller;

import com.comercialmoises.model.AuditoriaLog;
import com.comercialmoises.service.AuditoriaLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@CrossOrigin(origins = "*")
public class AuditoriaLogController {

    @Autowired
    private AuditoriaLogService auditoriaLogService;

    @GetMapping
    public List<AuditoriaLog> listarLogs() {
        return auditoriaLogService.listarTodos();
    }
}
