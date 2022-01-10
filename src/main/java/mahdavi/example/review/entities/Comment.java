package mahdavi.example.review.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mahdavi.example.review.enums.CommentStatus;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends EntityBase{

    @Column(name = "TEXT" , nullable = false , length = 500 , unique = true)
    private String text;


    @Enumerated(EnumType.STRING)
    @Column(name = "COMMENT_STATUS" , nullable = false , length = 50)
    private CommentStatus commentStatus;


    @JoinColumn(name = "PRODUCT_ID",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;



}
