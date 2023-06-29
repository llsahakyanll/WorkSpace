package com.company.workspace.service.verification;

public interface VerificationService {
    String createVerificationCode();
    boolean checkVerificationCode(String code, String inputCode);
}
