package mahdavi.example.review.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mahdavi.example.review.enums.ReviewType;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends EntityBase{

    @Column(name = "NAME" , nullable = false , length = 100 , unique = true)
    private String name;


    @Column(name = "DESCRIPTION" , length = 500)
    private String description;


    @Column(name = "IS_VISIBLE" , nullable = false )
    private Boolean isVisible;


    @Column(name = "IS_REVIEWABLE" , nullable = false )
    private Boolean isReviewable;


    @Enumerated(EnumType.STRING)
    @Column(name = "REVIEW_TYPE" , nullable = false , length = 50)
    private ReviewType reviewType;


    @JoinColumn(name = "PROVIDER_ID",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Provider provider;



}
