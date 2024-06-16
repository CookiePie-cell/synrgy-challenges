package com.example.FBJV24001114synrgy7josBinarFudch5.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String username;

    private String email;

    private String password;
}
