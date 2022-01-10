package mahdavi.example.review.repositories;

import mahdavi.example.review.entities.Comment;
import mahdavi.example.review.entities.Product;
import mahdavi.example.review.enums.CommentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("select cm from Comment cm where (:productId is null or cm.product.id = :productId ) and (:providerId is null or cm.product.provider.id = :providerId ) ")
    Page<Comment> findByProductOrProvider(@Param("productId") Long productId, @Param("providerId") Long providerId, Pageable pageable);


    Page<Comment> findByProduct_IdAndCommentStatusOrderByCreationTimeDesc(Long productId, CommentStatus commentStatus, Pageable pageable);


}
