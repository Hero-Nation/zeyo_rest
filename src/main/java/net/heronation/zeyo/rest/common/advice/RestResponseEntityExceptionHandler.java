package net.heronation.zeyo.rest.common.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.ResultDto;

@Slf4j 
@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResultDto> handleError(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		ResultDto R = new ResultDto();
		R.setMsg("FAILED");
		R.setR(e.toString());
		return new ResponseEntity<ResultDto>(R, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ResultDto> handleError404(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		ResultDto R = new ResultDto();
		R.setMsg("FAILED");
		R.setR(e.toString());
		return new ResponseEntity<ResultDto>(R, HttpStatus.BAD_REQUEST);
	}
}
