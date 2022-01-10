package mahdavi.example.review.controllers.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.dtos.comment.CommentRegisterDto;
import mahdavi.example.review.dtos.comment.CommentResponseDto;
import mahdavi.example.review.enums.ReviewType;
import mahdavi.example.review.services.CommentService;
import mahdavi.example.review.utils.PageResponse;
import mahdavi.example.review.utils.RestResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/comment")
public class ApiCommentController {

    private final CommentService commentService;

    @PostMapping
    public RestResponse<Void> registerComment(@RequestBody @Validated CommentRegisterDto registerDto) {

        commentService.registerComment(registerDto);

        return RestResponse.ok();
    }



}
