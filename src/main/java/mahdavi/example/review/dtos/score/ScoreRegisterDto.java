package mahdavi.example.review.dtos.score;


import lombok.Data;
import mahdavi.example.review.enums.ScoreValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ScoreRegisterDto {


    @NotNull
    private ScoreValue scoreValue;


    @NotNull
    private Long productId;


    @NotNull
    private Boolean isBuyer;

}
