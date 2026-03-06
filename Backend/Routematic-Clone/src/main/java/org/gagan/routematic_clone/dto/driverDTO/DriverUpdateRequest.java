package org.gagan.routematic_clone.dto.driverDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverUpdateRequest {
    private String licenseNumber;
    private int experienceYears;
    private Boolean availabilityStatus;
    private String password;
}
