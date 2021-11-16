package com.jindolph.shoppingmall.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String token;

    private String id;

    @NotBlank(message = "name is necessary")
    private String name;

    @Email
    @NotBlank(message = "email is necessary")
    private String email;

    @Length(min = 8, max = 16, message = "password must be at least 8 digits and not more than 16 digits.")
    @NotBlank(message = "name is necessary")
    private String password;
}