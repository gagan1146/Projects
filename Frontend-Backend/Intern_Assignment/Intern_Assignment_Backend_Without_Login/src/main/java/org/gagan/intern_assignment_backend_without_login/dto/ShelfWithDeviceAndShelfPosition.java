package org.gagan.intern_assignment_backend_without_login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShelfWithDeviceAndShelfPosition {
    private String shelfId;
    private String shelfName;
    private String partNumber;
    private Boolean flag;
    private String shelfPositionId;
    private String deviceName;
    private String deviceId;
}