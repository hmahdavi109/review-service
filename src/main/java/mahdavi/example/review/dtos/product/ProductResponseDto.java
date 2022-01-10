package mahdavi.example.review.dtos.product;


import lombok.Data;
import mahdavi.example.review.enums.ReviewType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductResponseDto {


    private Long id;

    private String name;

    private String description;

    private Boolean isVisible;

    private Boolean isReviewable;

    private ReviewType reviewType;

    private String providerName;

    private Long creationTime;

    private Long updateTime;
}
