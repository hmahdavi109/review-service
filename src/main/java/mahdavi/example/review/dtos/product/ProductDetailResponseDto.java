package mahdavi.example.review.dtos.product;


import lombok.Data;
import mahdavi.example.review.enums.ReviewType;

import java.util.List;

@Data
public class ProductDetailResponseDto {


    private Long id;

    private String name;

    private String description;

    private String providerName;

    private List<String> comments;

    private Double scoreAverage;

    private Integer ScoreCount;

    private Long creationTime;

    private Long updateTime;
}
