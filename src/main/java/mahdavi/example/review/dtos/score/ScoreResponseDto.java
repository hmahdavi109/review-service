package mahdavi.example.review.dtos.score;


import lombok.Data;
import mahdavi.example.review.enums.ScoreStatus;
import mahdavi.example.review.enums.ScoreValue;

@Data
public class ScoreResponseDto {


    private Long id;

    private Integer value;

    private ScoreStatus scoreStatus;

    private String productName;

    private String providerName;

    private Long creationTime;

    private Long updateTime;
}
