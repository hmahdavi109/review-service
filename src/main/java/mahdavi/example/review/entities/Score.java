package mahdavi.example.review.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mahdavi.example.review.enums.ScoreStatus;

import javax.persistence.*;

@Entity
@Table(name = "SCORE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score extends EntityBase {

    @Column(name = "VALUE", nullable = false)
    private Integer value;


    @Enumerated(EnumType.STRING)
    @Column(name = "SCORE_STATUS", nullable = false, length = 50)
    private ScoreStatus scoreStatus;


    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;


}
