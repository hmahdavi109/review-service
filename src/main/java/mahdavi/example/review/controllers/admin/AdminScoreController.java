package mahdavi.example.review.controllers.admin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.dtos.score.ScoreResponseDto;
import mahdavi.example.review.services.ScoreService;
import mahdavi.example.review.utils.PageResponse;
import mahdavi.example.review.utils.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("admin/score")
public class AdminScoreController {

    private final ScoreService scoreService;

    @GetMapping
    public RestResponse<PageResponse<ScoreResponseDto>> findWithPage(@RequestParam("page") Integer page,
                                                                       @RequestParam("size") Integer size,
                                                                       @RequestParam(value = "productId", required = false) Long productId,
                                                                       @RequestParam(value = "providerId", required = false) Long providerId) {

        PageResponse<ScoreResponseDto> response = scoreService.findWithPage(page, size, productId, providerId);

        return RestResponse.ok(response);
    }


    @GetMapping("apply-score")
    public RestResponse<Void> updateReviewable(@RequestParam("id") Long id) {

        scoreService.applyScore(id);

        return RestResponse.ok();
    }


    @GetMapping("ignore-score")
    public RestResponse<Void> updateVisible(@RequestParam("id") Long id) {

        scoreService.ignoreScore(id);

        return RestResponse.ok();
    }
}
