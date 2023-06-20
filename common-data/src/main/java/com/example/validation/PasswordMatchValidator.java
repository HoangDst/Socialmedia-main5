package com.example.validation;

import com.example.data.request.UserSignInRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch,Object> {
    @Override
    public void initialize(final PasswordMatch constraintAnnotation){

    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserSignInRequest user = (UserSignInRequest) obj;
        return user.getUserPassword().equals(user.getMatchingPassword());
    }
}
