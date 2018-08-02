package net.heronation.zeyo.rest.common.advice;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.dmodel.controller.DmodelController;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
 
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleMethodArgumentNotValid");
		ex.printStackTrace();
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleExceptionInternal");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoSuchRequestHandlingMethod(NoSuchRequestHandlingMethodException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleNoSuchRequestHandlingMethod");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleNoSuchRequestHandlingMethod(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleHttpRequestMethodNotSupported");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleHttpMediaTypeNotSupported");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleHttpMediaTypeNotAcceptable");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleMissingPathVariable");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleMissingPathVariable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleMissingServletRequestParameter");
	 	
		ex.printStackTrace();
		ResultDto R = new ResultDto();
		R.setMsg(CommonConstants.FAIL);
		R.setR(ex.getMessage());
		return new ResponseEntity<Object>(R, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleServletRequestBindingException");
		ex.printStackTrace();
		// TODO Auto-generated method stub
		return super.handleServletRequestBindingException(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleConversionNotSupported");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleConversionNotSupported(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleTypeMismatch");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleHttpMessageNotReadable");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleHttpMessageNotWritable");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleHttpMessageNotWritable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleMissingServletRequestPart");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleMissingServletRequestPart(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		log.debug("GlobalExceptionHandler : handleBindException");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleBindException(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.debug("GlobalExceptionHandler : handleNoHandlerFoundException");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleNoHandlerFoundException(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		log.debug("GlobalExceptionHandler : handleAsyncRequestTimeoutException");
		// TODO Auto-generated method stub
		ex.printStackTrace();
		return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
	}

}