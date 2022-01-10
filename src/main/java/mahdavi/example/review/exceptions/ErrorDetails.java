package mahdavi.example.review.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDetails {

    private String fieldName;
    private String message;

}
