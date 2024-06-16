package com.example.FBJV24001114synrgy7josBinarFudch4.model;

import com.example.FBJV24001114synrgy7josBinarFudch4.model.dto.PagingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private Integer statusCode;

    private T data;

    private PagingResponse paging;
}