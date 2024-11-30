package springboot.projects.springboot_restful_webservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorDetailsException> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest){

        ErrorDetailsException errorDetailsException = new ErrorDetailsException(
                LocalDateTime.now(),
                resourceNotFoundException.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );

        return new ResponseEntity<>(errorDetailsException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    ResponseEntity<ErrorDetailsException> handleEmailAlreadyExistsException(EmailAlreadyExistsException emailAlreadyExistsException, WebRequest webRequest){

        ErrorDetailsException errorDetailsException = new ErrorDetailsException(
                LocalDateTime.now(),
                emailAlreadyExistsException.getMessage(),
                webRequest.getDescription(false),
                "EMAIL_ALREADY_EXISTS"
        );

        return new ResponseEntity<>(errorDetailsException, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorDetailsException> handleGlobalErrorException(Exception exception, WebRequest webRequest){

        ErrorDetailsException errorDetailsException = new ErrorDetailsException(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );

        return new ResponseEntity<>(errorDetailsException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
