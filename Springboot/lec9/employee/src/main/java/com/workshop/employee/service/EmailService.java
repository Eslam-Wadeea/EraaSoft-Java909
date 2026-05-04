package com.workshop.employee.service;

import com.workshop.employee.DTO.EmailDTO;

import java.util.List;

public interface EmailService {

    EmailDTO saveEmail(EmailDTO emailDTO);

    EmailDTO updateEmail(Long id, EmailDTO emailDTO);

    void deleteEmail(Long id);

    List<EmailDTO> getAllEmails();

    EmailDTO getEmailByName(String name);

    List<EmailDTO> getEmailsByNames(List<String> names);

    EmailDTO getEmailByContent(String content);
}
