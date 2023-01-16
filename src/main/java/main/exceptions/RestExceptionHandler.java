package main.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import main.responses.BaseResponse;
import main.responses.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class RestExceptionHandler{
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handleConstraintViolations(ConstraintViolationException exception){
        String errorMessage;
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        if (!violations.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            violations.forEach(violation -> stringBuilder.append(violation.getMessage()).append(", "));
            errorMessage = stringBuilder.toString();
        }
        else{
            errorMessage = "A constraint validation exception has occurred.";
        }
        return BaseResponse.builder().error(HttpStatus.BAD_REQUEST.getReasonPhrase()).errorDescription(errorMessage).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse handleMethodArgumentViolations(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> objectErrors = exception.getAllErrors();
        objectErrors.forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return ValidationErrorResponse.validationErrorResponseBuilder().error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorDescription(exception.getMessage())
                .validationErrors(errors)
                .build();
    }
}
