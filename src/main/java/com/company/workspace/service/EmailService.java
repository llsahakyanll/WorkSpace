package com.company.workspace.service;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
    String sendVerificationCode(String toEmail);
}
