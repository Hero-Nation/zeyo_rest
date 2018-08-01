package net.heronation.zeyo.rest.common.advice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.dto.ResultDto;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResultDto> handleError(HttpServletRequest request, ConstraintViolationException e) {
		
		log.debug("RestResponseEntityExceptionHandler : handleError");
		 
		ConstraintViolation<?> this_cons = e.getConstraintViolations().iterator().next();
		
		//e.printStackTrace();
		ResultDto R = new ResultDto();
		R.setMsg("FAILED");
		//R.setR(e.getMessage());
		//R.setR(e.getCause());
		R.setR(this_cons.getMessage());
		return new ResponseEntity<ResultDto>(R, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ResultDto> handleError404(HttpServletRequest request, Exception e) {
		
		log.debug("RestResponseEntityExceptionHandler : handleError404");
		
		
		e.printStackTrace();
		ResultDto R = new ResultDto();
		R.setMsg("FAILED");
		//R.setR(e.getMessage());
		R.setR(e.toString());
		return new ResponseEntity<ResultDto>(R, HttpStatus.BAD_REQUEST);
	}
	
	
}
