package com.example.data.request;

import com.example.validation.PasswordMatch;
import com.example.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@PasswordMatch
public class UserSignInRequest {
    @NotNull
    @Size(min = 1)
    private String userName;

    @NotNull
    @Size(min = 1)
    private String userAccount;


    @ValidPassword
    private String userPassword;

    @Size(min = 1)
    @NotNull
    private String matchingPassword;

    @Email
    @NotNull
    @Size(min = 1)
    private String userEmail;

    private String userAvatar;

    private boolean isUsing2FA;
    //    @NotBlank(message = "Email is mandatory")
    private String authority;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [userName=")
                .append(userName)
                .append(", isUsing2FA=")
                .append(isUsing2FA)
                .append(" , authority=")
                .append(authority);
        return builder.toString();
    }
}
