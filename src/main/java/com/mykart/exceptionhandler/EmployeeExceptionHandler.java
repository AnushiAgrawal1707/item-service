package com.mykart.exceptionhandler;

import com.mykart.exception.ResourceNotFound;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;


@ControllerAdvice
@Log4j2
public class EmployeeExceptionHandler {


  @ExceptionHandler(ResourceNotFound.class)
  public ResponseEntity<Object> resourceNotFound(ResourceNotFound exception) {
    ErrorResponse e = new ErrorResponse();
    e.setMessage("Resource Not Found");
    e.setStatusCode(404);
    log.error("Resource Not Found Exception Raised");

    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);

  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handle(Exception ex, HttpServletRequest request,
      HttpServletResponse response) {
    ErrorResponse e = new ErrorResponse();
    if (ex instanceof EmptyResultDataAccessException) {

      e.setMessage("Resource Not Found");
      e.setStatusCode(500);
      log.error("Resource Not Found Exception Raised");
      return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }
    e.setMessage(ex.getMessage());
    e.setStatusCode(404);
    log.error(ex.getMessage());
    return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex)
      throws IOException {
    String msg = "";
    for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
      msg += cv.getPropertyPath() + " " + cv.getMessage();
    }

    ErrorResponse e = new ErrorResponse();
    e.setMessage(msg);
    e.setStatusCode(400);
    log.error(msg);

    return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex)
      throws IOException {
    ErrorResponse e = new ErrorResponse();
    BindingResult result = ex.getBindingResult();
    String msg = "";
    for (FieldError fieldError : result.getFieldErrors()) {
      msg = msg + fieldError.getField() + " : " + fieldError.getDefaultMessage() + "%ns";
    }
    e.setMessage(msg);
    e.setStatusCode(400);
    log.error(msg);
    return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
  }
}
