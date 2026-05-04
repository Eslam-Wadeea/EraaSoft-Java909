package com.workshop.employee.service.impl;

import com.workshop.employee.DTO.EmailDTO;
import com.workshop.employee.mapper.EntityMapper;
import com.workshop.employee.model.Email;
import com.workshop.employee.repository.EmailRepository;
import com.workshop.employee.service.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {


    private final EmailRepository emailRepository;
    private final EntityMapper mapper;

    @Override
    public EmailDTO saveEmail(EmailDTO emailDTO) {
        Email email = mapper.toEntity(emailDTO);
        return mapper.toDto(emailRepository.save(email));
    }

    @Override
    @Transactional
    public EmailDTO updateEmail(Long id, EmailDTO emailDTO) {
        Email existing = emailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email record not found with id: " + id));

        existing.setName(emailDTO.getName());
        existing.setContent(emailDTO.getContent());

        return mapper.toDto(emailRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteEmail(Long id) {
        if (!emailRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete. Email not found with id: " + id);
        }
        emailRepository.deleteById(id);

    }

    @Override
    public List<EmailDTO> getAllEmails() {
        return mapper.toEmailDtoList(emailRepository.findAll());
    }

    @Override
    public EmailDTO getEmailByName(String name) {
        return emailRepository.findByName(name)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Email not found with type: " + name));
    }

    @Override
    public List<EmailDTO> getEmailsByNames(List<String> names) {
        return mapper.toEmailDtoList(emailRepository.findByNameIn(names));
    }

    @Override
    public EmailDTO getEmailByContent(String content) {
        return emailRepository.findByContent(content)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Email not found with content: " + content));
    }
}
