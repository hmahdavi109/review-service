package mahdavi.example.review.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.dtos.comment.CommentRegisterDto;
import mahdavi.example.review.dtos.comment.CommentResponseDto;
import mahdavi.example.review.entities.Comment;
import mahdavi.example.review.entities.Product;
import mahdavi.example.review.enums.CommentStatus;
import mahdavi.example.review.enums.ReviewType;
import mahdavi.example.review.exceptions.GeneralException;
import mahdavi.example.review.mappers.CommentMapper;
import mahdavi.example.review.repositories.CommentRepository;
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
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    private CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);



    //region admin services
    public PageResponse<CommentResponseDto> findWithPage(Integer page, Integer size, Long productId, Long providerId) {

        Page<Comment> commentPage = commentRepository.findByProductOrProvider(productId, providerId, PageRequest.of(page, size));

        List<CommentResponseDto> responseDtos = commentPage.getContent().stream().map(comment -> commentMapper.commentToResponseDto(comment)).collect(Collectors.toList());

        return new PageResponse<>(responseDtos, commentPage.isFirst(), commentPage.isLast(), commentPage.getTotalElements());

    }

    public void applyComment(Long id) {

        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (!optionalComment.isPresent()) {
            throw new GeneralException("comment.not.found", 404);
        }


        Comment comment = optionalComment.get();

        comment.setCommentStatus(CommentStatus.APPLIED);

        commentRepository.save(comment);

    }

    public void ignoreComment(Long id) {

        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (!optionalComment.isPresent()) {
            throw new GeneralException("comment.not.found", 404);
        }


        Comment comment = optionalComment.get();

        comment.setCommentStatus(CommentStatus.IGNORED);

        commentRepository.save(comment);

    }
    //endregion




    //region api services


    public void registerComment(CommentRegisterDto registerDto) {


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
            throw new GeneralException("just.buyers.can.register.comment");
        }

        Comment comment = commentMapper.registerDtoToComment(registerDto);

        comment.setProduct(product);

        comment.setCommentStatus(CommentStatus.REGISTERED);

        commentRepository.save(comment);

    }


    //endregion


}
