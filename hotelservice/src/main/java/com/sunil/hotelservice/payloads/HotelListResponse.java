package com.sunil.hotelservice.payloads;

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
public class HotelListResponse {
    private Integer pageSize;
    private Integer currentPage;
    private Boolean lastPage;
    private Long totalElements;
    private Integer totalPages;
    private List<HotelDto> content;
}
