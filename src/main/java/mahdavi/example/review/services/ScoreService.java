package mahdavi.example.review.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.dtos.score.ScoreRegisterDto;
import mahdavi.example.review.dtos.score.ScoreResponseDto;
import mahdavi.example.review.entities.Score;
import mahdavi.example.review.entities.Product;
import mahdavi.example.review.enums.ScoreStatus;
import mahdavi.example.review.enums.ReviewType;
import mahdavi.example.review.exceptions.GeneralException;
import mahdavi.example.review.mappers.ScoreMapper;
import mahdavi.example.review.repositories.ScoreRepository;
import mahdavi.example.review.repositories.ProductRepository;
import mahdavi.example.review.utils.PageResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final ProductRepository productRepository;

    private ScoreMapper scoreMapper = Mappers.getMapper(ScoreMapper.class);


    public void registerScore(ScoreRegisterDto registerDto) {

        
        Optional<Product> optionalProduct = productRepository.findById(registerDto.getProductId());

        if (!optionalProduct.isPresent()) {
            throw new GeneralException("product.not.found", 404);
        }

        Product product = optionalProduct.get();
        
        if (!product.getIsVisible())
        {
            throw new GeneralException("product.is.not.visible");
        }

        if (!product.getIsReviewable())
        {
            throw new GeneralException("product.is.not.reviewable");
        }


        if (product.getReviewType() == ReviewType.JUST_BUYERS && !registerDto.getIsBuyer())
        {
            throw new GeneralException("just.buyers.can.register.score");
        }

        Score score = scoreMapper.registerDtoToScore(registerDto);

        score.setProduct(product);
        
        score.setScoreStatus(ScoreStatus.REGISTERED);        
        
        scoreRepository.save(score);

    }


    public PageResponse<ScoreResponseDto> findWithPage(Integer page, Integer size, Long productId, Long providerId) {

        Page<Score> scorePage = scoreRepository.findByProductOrProvider(productId, providerId, PageRequest.of(page, size));

        List<ScoreResponseDto> responseDtos = scorePage.getContent().stream().map(score -> scoreMapper.scoreToResponseDto(score)).collect(Collectors.toList());

        return new PageResponse<>(responseDtos, scorePage.isFirst(), scorePage.isLast(), scorePage.getTotalElements());

    }


    public void applyScore(Long id) {

        Optional<Score> optionalScore = scoreRepository.findById(id);

        if (!optionalScore.isPresent()) {
            throw new GeneralException("score.not.found", 404);
        }


        Score score = optionalScore.get();

        score.setScoreStatus(ScoreStatus.APPLIED);

        scoreRepository.save(score);

    }


    public void ignoreScore(Long id) {

        Optional<Score> optionalScore = scoreRepository.findById(id);

        if (!optionalScore.isPresent()) {
            throw new GeneralException("score.not.found", 404);
        }


        Score score = optionalScore.get();

        score.setScoreStatus(ScoreStatus.IGNORED);

        scoreRepository.save(score);

    }

}
