package es.symbioserver.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.symbioserver.beans.ErrorDetails;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(Exception.class) 
	public ResponseEntity < String > assertionException(final Exception e) {
		ErrorDetails errorDetails = new ErrorDetails(String.valueOf(System.currentTimeMillis()), "Validation Failed " + e.getMessage(), "");
		ResponseEntity<String > rE = new ResponseEntity(errorDetails.toString(), HttpStatus.BAD_REQUEST);
		return rE;
    }
	
	

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(String.valueOf(System.currentTimeMillis()), "Validation Failed",
	        ex.getBindingResult().toString());
	    return new ResponseEntity(errorDetails.toString(), HttpStatus.BAD_REQUEST);
	} 
	
	
}
