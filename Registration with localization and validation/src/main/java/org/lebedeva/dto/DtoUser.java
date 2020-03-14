package org.lebedeva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.lebedeva.model.Gender;
import org.lebedeva.validate.EnumPattern;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {
    private Integer id;
    @Length(min = 1, max = 30)
    private String login;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$")
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    @Email
    private String email;
    @Pattern(regexp = "^(\\+[1-9][0-9]*(\\([0-9]*\\)|-[0-9]*-))?[0]?[1-9][0-9\\- ]*$")
    private String phone;
    @NotNull
    @EnumPattern(enumClass = Gender.class)
    private String gender;
}
