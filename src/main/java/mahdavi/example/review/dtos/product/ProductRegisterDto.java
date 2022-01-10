package mahdavi.example.review.dtos.product;


import lombok.Data;
import mahdavi.example.review.entities.Provider;
import mahdavi.example.review.enums.ReviewType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRegisterDto {


    @NotBlank
    @Length(max = 100)
    private String name;


    @Length(max = 500)
    private String description;


    @NotNull
    private Boolean isVisible;


    @NotNull
    private Boolean isReviewable;


    @NotNull
    private ReviewType reviewType;


    @NotNull
    private Long providerId;

}
