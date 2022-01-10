package mahdavi.example.review.exceptions;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mahdavi.example.review.utils.RestResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ErrorResponseHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(GeneralException.class)
    public RestResponse<Void> exceptionHandler(GeneralException exception,
                                               Locale locale, HttpServletResponse response) {
        logger.error(exception.getMessage(), exception);
        response.setStatus(exception.getStatus());
        return RestResponse.error(messageSource.getMessage(exception.getMessage(), exception.getArgs(), locale));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<Void> handleDefaultException(Throwable ex) {
        logger.error(ex.getMessage(), ex);
        return RestResponse.error(ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public RestResponse<?> handleDefaultException(NoHandlerFoundException ex) {
        return RestResponse.error(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse<List<ErrorDetails>> validationHandler(MethodArgumentNotValidException ex
            , Locale locale) {

        logger.error(ex.getMessage(), ex);

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        List<ErrorDetails> errorDetails = new ArrayList<>();
        for (FieldError fieldError : errors) {
            ErrorDetails error = new ErrorDetails();
            error.setFieldName(fieldError.getField());
            error.setMessage(messageSource.getMessage(Objects.requireNonNull(fieldError.getDefaultMessage())
                    , null, locale));
            errorDetails.add(error);
        }

        return RestResponse.error(messageSource.getMessage("validation.error"
                , null
                , locale)
                , errorDetails);
    }
}
