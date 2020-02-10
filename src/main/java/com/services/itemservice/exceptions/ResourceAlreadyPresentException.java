package com.services.itemservice.exceptions;

/**
 * Represents an exception class that occurs when the requested resource to be inserted is already present
 */
public class ResourceAlreadyPresentException extends Exception {
  public ResourceAlreadyPresentException() {}

  public ResourceAlreadyPresentException(String msg) {
    super(msg);
  }
}
