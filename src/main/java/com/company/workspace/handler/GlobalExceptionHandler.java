package com.company.workspace.handler;

import com.company.workspace.entity.User;
import com.company.workspace.entity.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException ex, Model model) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            model.addAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());
        }

        model.addAttribute("userForUser", new User());
        model.addAttribute("userDetails", new UserDetails());

        return "registration_user";
    }
}