package mahdavi.example.review.mappers;

import mahdavi.example.review.dtos.comment.CommentResponseDto;
import mahdavi.example.review.dtos.comment.CommentRegisterDto;
import mahdavi.example.review.entities.Comment;
import mahdavi.example.review.entities.Comment;
import mahdavi.example.review.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface CommentMapper {


    Comment registerDtoToComment(CommentRegisterDto registerDto);



    @Mappings({
            @Mapping(target = "productName", expression = "java(comment.getProduct().getName())"),
            @Mapping(target = "providerName", expression = "java(comment.getProduct().getProvider().getName())"),
            @Mapping(target = "creationTime", expression = "java(comment.getCreationTime().getTime())"),
            @Mapping(target = "updateTime", expression = "java(comment.getUpdateTime() == null ? null : comment.getUpdateTime().getTime())")
    })
    CommentResponseDto commentToResponseDto(Comment comment);

}
