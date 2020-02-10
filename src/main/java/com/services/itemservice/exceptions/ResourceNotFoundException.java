package com.services.itemservice.exceptions;
/**
 * Represents an exception class that occurs when the requested resource is not found in the database
 */
public class ResourceNotFoundException extends Exception {
  public ResourceNotFoundException() {}

  public ResourceNotFoundException(String msg) {
    super(msg);
  }
}
