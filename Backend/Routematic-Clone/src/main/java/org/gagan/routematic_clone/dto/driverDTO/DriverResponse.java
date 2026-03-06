package org.gagan.routematic_clone.dto.driverDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverResponse {
    private UUID id;
    private String licenseNumber;
    private int experienceYears;
    private Boolean availabilityStatus;
    private String email;
}