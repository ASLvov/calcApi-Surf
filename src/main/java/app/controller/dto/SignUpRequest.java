package app.controller.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String userName;
    private String password;
    private String[] roles;
}