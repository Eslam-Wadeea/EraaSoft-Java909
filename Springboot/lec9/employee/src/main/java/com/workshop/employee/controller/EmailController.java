package com.workshop.employee.controller;

import com.workshop.employee.DTO.EmailDTO;
import com.workshop.employee.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService; // Assume implementation similar to EmployeeService

    @GetMapping("/name/{name}")
    public ResponseEntity<EmailDTO> getByName(@PathVariable String name) {
        return ResponseEntity.ok(emailService.getEmailByName(name));
    }

    @GetMapping("/content")
    public ResponseEntity<EmailDTO> getByContent(@RequestParam String content) {
        return ResponseEntity.ok(emailService.getEmailByContent(content));
    }

    @PostMapping
    public ResponseEntity<EmailDTO> create(@Valid @RequestBody EmailDTO dto) {
        return new ResponseEntity<>(emailService.saveEmail(dto), HttpStatus.CREATED);
    }
}
