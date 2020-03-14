package org.lebedeva.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private Gender gender;
}
