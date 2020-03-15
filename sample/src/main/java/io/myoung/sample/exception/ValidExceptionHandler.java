package io.myoung.sample.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.myoung.sample.controller.response.HttpErrorResponse;
import io.myoung.sample.model.ErrorItem;

@RestControllerAdvice
public class ValidExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    protected HttpErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorItem item = null;
        List<ErrorItem> errorList = new ArrayList<ErrorItem>();
        
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            item = new ErrorItem();
            item.setField(fieldError.getField());
            item.setReason(fieldError.getDefaultMessage());
            item.setValue(fieldError.getRejectedValue());
            errorList.add(item);
        }
        
        return HttpErrorResponse.builder().message(e.getMessage()).status("Error").errors(errorList).build();
    }
	
	@ExceptionHandler(EncryptException.class)
    protected HttpErrorResponse handleEncryptException(EncryptException e) {
        
        return HttpErrorResponse.builder().message(e.getMessage()).status("Error").errors(null).build();
    }
	
	@ExceptionHandler(UserException.class)
    protected HttpErrorResponse handleUserCreateException(UserException e) {
        
        return HttpErrorResponse.builder().message(e.getMessage()).status("Error").errors(null).build();
    }

	@ExceptionHandler(GroupException.class)
    protected HttpErrorResponse handleGroupSelectException(GroupException e) {
        
        return HttpErrorResponse.builder().message(e.getMessage()).status("Error").errors(null).build();
    }
	
	@ExceptionHandler(FriendException.class)
    protected HttpErrorResponse handleFriendException(FriendException e) {
        
        return HttpErrorResponse.builder().message(e.getMessage()).status("Error").errors(null).build();
    }
}
