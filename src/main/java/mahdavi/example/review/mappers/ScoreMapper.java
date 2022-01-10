package mahdavi.example.review.mappers;

import mahdavi.example.review.dtos.score.ScoreRegisterDto;
import mahdavi.example.review.dtos.score.ScoreResponseDto;
import mahdavi.example.review.entities.Score;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ScoreMapper {


    Score registerDtoToScore(ScoreRegisterDto registerDto);


    @Mappings({
            @Mapping(target = "productName", expression = "java(score.getProduct().getName())"),
            @Mapping(target = "providerName", expression = "java(score.getProduct().getProvider().getName())"),
            @Mapping(target = "creationTime", expression = "java(score.getCreationTime().getTime())"),
            @Mapping(target = "updateTime", expression = "java(score.getUpdateTime() == null ? null : score.getUpdateTime().getTime())")
    })
    ScoreResponseDto scoreToResponseDto(Score score);

}
