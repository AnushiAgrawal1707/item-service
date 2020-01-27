package com.mykart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason="Item out of stock")
public class OutOfStockException extends Exception{

  public OutOfStockException() {
    super();
  }
  public OutOfStockException(String message) {
    super(message);
  }
}
