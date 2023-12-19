package com.main.logicgate.dto;
import com.main.logicgate.common.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationrRequestDTO {
    private String email;
    private String password;
    private UserRole userRole;
}
