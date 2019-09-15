package com.example.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.app.ws.ui.model.resp.ErrorMessage;

@ControllerAdvice
public class AppExpHandler extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(value = (Exception.class))
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		
		String errorMsgDesc;
		ErrorMessage errorMessage;
		
		errorMsgDesc = ex.getLocalizedMessage();
		
		if (errorMsgDesc == null) errorMsgDesc = ex.toString();
		
		errorMessage = new ErrorMessage(new Date(), errorMsgDesc);
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = (NullPointerException.class))
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request){
		
		String errorMsgDesc;
		ErrorMessage errorMessage;
		
		errorMsgDesc = ex.getLocalizedMessage();
		
		if (errorMsgDesc == null) errorMsgDesc = ex.toString();
		
		errorMessage = new ErrorMessage(new Date(), errorMsgDesc);
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = (UserClassException.class))
	public ResponseEntity<Object> handleUserClassException(UserClassException ex, WebRequest request){
		
		String errorMsgDesc;
		ErrorMessage errorMessage;
		HttpStatus lHttpStatus;
		
		errorMsgDesc = ex.getLocalizedMessage();
		
		if (errorMsgDesc == null) errorMsgDesc = ex.toString();
		
		errorMessage = new ErrorMessage(new Date(), errorMsgDesc);
		
		lHttpStatus = HttpStatus.valueOf(526);
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), lHttpStatus);
	}
	
}
