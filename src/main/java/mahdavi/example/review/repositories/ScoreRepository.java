package mahdavi.example.review.repositories;

import mahdavi.example.review.entities.Comment;
import mahdavi.example.review.entities.Score;
import mahdavi.example.review.enums.CommentStatus;
import mahdavi.example.review.enums.ScoreStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ScoreRepository extends JpaRepository<Score, Long> {


    @Query("select sc from Score sc where (:productId is null or sc.product.id = :productId ) and (:providerId is null or sc.product.provider.id = :providerId ) ")
    Page<Score> findByProductOrProvider(@Param("productId") Long productId, @Param("providerId") Long providerId, Pageable pageable);


    List<Score> findByProduct_IdAndScoreStatus(Long productId, ScoreStatus scoreStatus);

}
