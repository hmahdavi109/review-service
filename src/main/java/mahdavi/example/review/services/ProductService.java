package mahdavi.example.review.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.dtos.product.ProductDetailResponseDto;
import mahdavi.example.review.dtos.product.ProductRegisterDto;
import mahdavi.example.review.dtos.product.ProductResponseDto;
import mahdavi.example.review.entities.Comment;
import mahdavi.example.review.entities.Product;
import mahdavi.example.review.entities.Provider;
import mahdavi.example.review.entities.Score;
import mahdavi.example.review.enums.CommentStatus;
import mahdavi.example.review.enums.ReviewType;
import mahdavi.example.review.enums.ScoreStatus;
import mahdavi.example.review.exceptions.GeneralException;
import mahdavi.example.review.mappers.ProductMapper;
import mahdavi.example.review.repositories.CommentRepository;
import mahdavi.example.review.repositories.ProductRepository;
import mahdavi.example.review.repositories.ProviderRepository;
import mahdavi.example.review.repositories.ScoreRepository;
import mahdavi.example.review.utils.PageResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;
    private final CommentRepository commentRepository;
    private final ScoreRepository scoreRepository;

    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);


    //region admin services
    public void registerProduct(ProductRegisterDto registerDto) {

        if (productRepository.existsByName(registerDto.getName())) {
            throw new GeneralException("product.name.is.unique");
        }


        Optional<Provider> optionalProvider = providerRepository.findById(registerDto.getProviderId());

        if (!optionalProvider.isPresent()) {
            throw new GeneralException("provider.not.found", 404);
        }

        Product product = productMapper.registerDtoToProduct(registerDto);

        product.setProvider(optionalProvider.get());


        productRepository.save(product);

    }


    public PageResponse<ProductResponseDto> findWithPage(Integer page, Integer size) {

        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));

        List<ProductResponseDto> responseDtos = productPage.getContent().stream().map(product -> productMapper.productToResponseDto(product)).collect(Collectors.toList());

        return new PageResponse<>(responseDtos, productPage.isFirst(), productPage.isLast(), productPage.getTotalElements());

    }


    public void updateReviewable(Long id, Boolean isReviewable) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            throw new GeneralException("product.not.found", 404);
        }


        Product product = optionalProduct.get();

        product.setIsReviewable(isReviewable);

        productRepository.save(product);

    }


    public void updateVisible(Long id, Boolean isVisible) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            throw new GeneralException("product.not.found", 404);
        }


        Product product = optionalProduct.get();

        product.setIsVisible(isVisible);

        productRepository.save(product);

    }


    public void updateReviewType(Long id, ReviewType reviewType) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            throw new GeneralException("product.not.found", 404);
        }


        Product product = optionalProduct.get();

        product.setReviewType(reviewType);

        productRepository.save(product);

    }
    //endregion


    //region api services
    public PageResponse<ProductDetailResponseDto> findProductWithDetails(Integer page, Integer size) {

        Page<Product> productPage = productRepository.findByIsVisible(true, PageRequest.of(page, size));

        List<ProductDetailResponseDto> responseDtos = productPage.getContent().stream().map(product -> productMapper.productToDetailResponseDto(product)).collect(Collectors.toList());

        for (ProductDetailResponseDto detailResponseDto : responseDtos) {

            Page<Comment> last3CommentPage = commentRepository.findByProduct_IdAndCommentStatusOrderByCreationTimeDesc(detailResponseDto.getId(), CommentStatus.APPLIED, PageRequest.of(0, 3));

            List<Score> scores = scoreRepository.findByProduct_IdAndScoreStatus(detailResponseDto.getId(), ScoreStatus.APPLIED);

            Integer sumOfScores = 0;
            for (Score score : scores) {
                sumOfScores += score.getValue();
            }


            detailResponseDto.setComments(last3CommentPage.getContent().stream().map(Comment::getText).collect(Collectors.toList()));

            detailResponseDto.setScoreCount(scores.size());

            detailResponseDto.setScoreAverage(scores.size() == 0 ? 0 : (double) (sumOfScores / scores.size()));

        }


        return new PageResponse<>(responseDtos, productPage.isFirst(), productPage.isLast(), productPage.getTotalElements());

    }
    //endregion


}
