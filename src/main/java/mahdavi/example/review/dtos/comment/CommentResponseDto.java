package mahdavi.example.review.dtos.comment;


import lombok.Data;
import mahdavi.example.review.enums.CommentStatus;

@Data
public class CommentResponseDto {


    private Long id;

    private String text;

    private CommentStatus commentStatus;

    private String productName;

    private String providerName;

    private Long creationTime;

    private Long updateTime;
}
