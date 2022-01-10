package mahdavi.example.review.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GeneralException extends RuntimeException {

    private int status;
    private String message;
    private Object[] args;

    public GeneralException(String message) {
        this.status = 400;
        this.message = message;
    }

    public GeneralException(String message, int status) {
        this.status = status;
        this.message = message;
    }

    public GeneralException(String message, int status, Object... args) {
        this.status = status;
        this.message = message;
        this.args = args;
    }

    public GeneralException(String message, Object... args) {
        this.status = 400;
        this.message = message;
        this.args = args;
    }
}
