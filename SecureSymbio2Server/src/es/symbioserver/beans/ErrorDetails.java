package es.symbioserver.beans;

import java.util.Date;

public class ErrorDetails {
	  private String timestamp;
	  private String message;
	  private String details;

	  public ErrorDetails(String timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	  }

	  public String getTimestamp() {
	    return timestamp;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public String getDetails() {
	    return details;
	  }
	  
	  @Override
	  public String toString(){
		  return "timestamp: " + this.timestamp + " message: " +  this.message + "\ndetails: " + this.details;
	  }
}
