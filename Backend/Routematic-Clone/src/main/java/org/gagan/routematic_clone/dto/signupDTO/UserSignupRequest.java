package org.gagan.routematic_clone.dto.signupDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequest {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String department;
    private String role;
}
