package com.markian.rentitup.User.UserDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyCollectorRequest {
    private Long id;
    private boolean status;
}