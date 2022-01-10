package mahdavi.example.review.controllers.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.dtos.score.ScoreRegisterDto;
import mahdavi.example.review.services.ScoreService;
import mahdavi.example.review.utils.RestResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/score")
public class ApiScoreController {

    private final ScoreService scoreService;

    @PostMapping
    public RestResponse<Void> registerScore(@RequestBody @Validated ScoreRegisterDto registerDto) {

        scoreService.registerScore(registerDto);

        return RestResponse.ok();
    }



}
