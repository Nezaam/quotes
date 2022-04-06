package com.krymov.quotes.exception;

import com.krymov.quotes.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  // handle specific exceptions
  @ExceptionHandler(AccountNotFoundException.class)
  public ResponseEntity<ErrorDto> handleResourceNotFoundException(AccountNotFoundException exception,
                                                                  WebRequest webRequest) {
    ErrorDto errorDetails = new ErrorDto(exception.getMessage(),
        webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AccountAlreadyExistsException.class)
  public ResponseEntity<ErrorDto> handleResourceNotFoundException(AccountAlreadyExistsException exception,
                                                                  WebRequest webRequest) {
    ErrorDto errorDetails = new ErrorDto(exception.getMessage(),
        webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(QuoteNotFoundException.class)
  public ResponseEntity<ErrorDto> handleResourceNotFoundException(QuoteNotFoundException exception,
                                                                  WebRequest webRequest) {
    ErrorDto errorDetails = new ErrorDto(exception.getMessage(),
        webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(OperationNotAllowedException.class)
  public ResponseEntity<ErrorDto> handleBlogAPIException(OperationNotAllowedException exception,
                                                         WebRequest webRequest) {
    ErrorDto errorDetails = new ErrorDto(exception.getMessage(),
        webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  // global exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleGlobalException(Exception exception,
                                                        WebRequest webRequest) {
    ErrorDto errorDetails = new ErrorDto(exception.getMessage(),
        webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
