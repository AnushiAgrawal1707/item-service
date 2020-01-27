package com.mykart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason="Item already added to cart")
public class ItemAlreadyPresentException extends Exception {
  public ItemAlreadyPresentException() {
    super();
  }
  public ItemAlreadyPresentException(String message) {
    super(message);
  }
}
