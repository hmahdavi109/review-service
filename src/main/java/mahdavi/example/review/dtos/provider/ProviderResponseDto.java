package mahdavi.example.review.dtos.provider;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ProviderResponseDto {

    private Long id;

    private String name;

    private String description;

    private Long creationTime;

    private Long updateTime;

}
