package mahdavi.example.review.dtos.comment;


import lombok.Data;
import mahdavi.example.review.enums.ReviewType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentRegisterDto {


    @NotBlank
    @Length(max = 500)
    private String text;


    @NotNull
    private Long productId;


    @NotNull
    private Boolean isBuyer;

}
