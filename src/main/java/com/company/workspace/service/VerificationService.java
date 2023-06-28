package com.company.workspace.service;

public interface VerificationService {
    String createVerificationCode();
    boolean checkVerificationCode(String code, String inputCode);
}
