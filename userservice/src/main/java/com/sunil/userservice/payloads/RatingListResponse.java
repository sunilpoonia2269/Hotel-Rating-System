package com.sunil.userservice.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingListResponse {

    private Integer pageSize;
    private Integer currentPage;
    private Integer totalPages;
    private Boolean lastPage;
    private Long totalElements;
    private List<RatingDto> content;
}
