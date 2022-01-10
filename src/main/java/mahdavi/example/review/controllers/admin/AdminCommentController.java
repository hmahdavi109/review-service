package mahdavi.example.review.controllers.admin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.dtos.comment.CommentRegisterDto;
import mahdavi.example.review.dtos.comment.CommentResponseDto;
import mahdavi.example.review.dtos.product.ProductResponseDto;
import mahdavi.example.review.services.CommentService;
import mahdavi.example.review.utils.PageResponse;
import mahdavi.example.review.utils.RestResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("admin/comment")
public class AdminCommentController {

    private final CommentService commentService;

    @GetMapping
    public RestResponse<PageResponse<CommentResponseDto>> findWithPage(@RequestParam("page") Integer page,
                                                                       @RequestParam("size") Integer size,
                                                                       @RequestParam(value = "productId", required = false) Long productId,
                                                                       @RequestParam(value = "providerId", required = false) Long providerId) {

        PageResponse<CommentResponseDto> response = commentService.findWithPage(page, size, productId, providerId);

        return RestResponse.ok(response);
    }


    @GetMapping("apply-comment")
    public RestResponse<Void> updateReviewable(@RequestParam("id") Long id) {

        commentService.applyComment(id);

        return RestResponse.ok();
    }


    @GetMapping("ignore-comment")
    public RestResponse<Void> updateVisible(@RequestParam("id") Long id) {

        commentService.ignoreComment(id);

        return RestResponse.ok();
    }
}
