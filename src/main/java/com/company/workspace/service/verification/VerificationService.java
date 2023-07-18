package com.company.workspace.service.verification;

public interface VerificationService {
    String createVerificationCode();
    void checkVerificationCode(String code, String inputCode);
}
