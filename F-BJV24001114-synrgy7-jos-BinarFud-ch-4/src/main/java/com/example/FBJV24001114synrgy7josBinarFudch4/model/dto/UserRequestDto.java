package com.example.FBJV24001114synrgy7josBinarFudch4.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String username;

    private String email;

    private String password;
}
