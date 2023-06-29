package com.company.workspace.service.email;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
    String sendVerificationCode(String toEmail);
}
