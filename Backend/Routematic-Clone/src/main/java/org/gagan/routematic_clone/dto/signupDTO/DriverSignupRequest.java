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
public class DriverSignupRequest {
    private String email;
    private String password;
}
