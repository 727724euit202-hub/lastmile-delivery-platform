package com.lastmile.delivery.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message="Name is required")
    @Pattern(regexp="^[A-Za-z]+$",message="Name must only contain alphabets and space")
    private String name;
    @NotBlank(message="Enter an Email")
    @Email(message="Enter a valid email")
    private String email;

    @NotBlank(message="Enter a password")
    @Size(min=8 ,message="The password must be atleast 8 charachters")
    private String password;

    @NotBlank(message="Enter a phone number to register")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;
}
