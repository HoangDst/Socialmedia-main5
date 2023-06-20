package com.example.validation;

import com.nimbusds.jose.Payload;

public @interface ValidPassword {
    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

