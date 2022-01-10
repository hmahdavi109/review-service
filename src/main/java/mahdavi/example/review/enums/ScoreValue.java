package mahdavi.example.review.enums;


import lombok.Getter;

@Getter
public enum ScoreValue {


    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);


    private final Integer value;

    ScoreValue(Integer value) {
        this.value = value;
    }


}
