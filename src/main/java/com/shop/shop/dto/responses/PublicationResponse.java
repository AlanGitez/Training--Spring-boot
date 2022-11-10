package com.shop.shop.dto.responses;

import com.shop.shop.dto.ProductDTO;
import com.shop.shop.dto.PublicationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PublicationResponse {

    private List<PublicationDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}