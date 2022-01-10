package mahdavi.example.review.dtos.provider;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ProviderRegisterDto {


    @NotBlank
    @Length(max = 100)
    private String name;


    @Length(max = 500)
    private String description;

}
