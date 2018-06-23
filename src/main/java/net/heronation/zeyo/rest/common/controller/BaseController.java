package net.heronation.zeyo.rest.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.heronation.zeyo.rest.common.value.ResultDto;
import net.heronation.zeyo.rest.constants.CommonConstants;

public class BaseController {

	protected ResponseEntity<ResultDto> return_fail(Object Result) {
		ResultDto R = new ResultDto();
		R.setMsg(CommonConstants.FAIL);
		R.setR(Result);
		return new ResponseEntity<ResultDto>(R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
 
	
	protected ResponseEntity<ResultDto> return_fail(String Result) {
		ResultDto R = new ResultDto();
		R.setMsg(CommonConstants.FAIL);
		R.setR(Result);
		return new ResponseEntity<ResultDto>(R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	protected ResponseEntity<ResultDto> return_fail() {
		ResultDto R = new ResultDto();
		R.setMsg(CommonConstants.FAIL);
		return new ResponseEntity<ResultDto>(R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	protected ResponseEntity<ResultDto> return_success(String Result) {
		ResultDto R = new ResultDto();
		R.setMsg(CommonConstants.SUCCESS);
		R.setR(Result);
		return new ResponseEntity<ResultDto>(R, HttpStatus.OK);
	}
	
 
	
	protected ResponseEntity<ResultDto> return_success(Object object) {
		ResultDto R = new ResultDto();
		R.setMsg(CommonConstants.SUCCESS);
		R.setR(object);
		return new ResponseEntity<ResultDto>(R, HttpStatus.OK);
	}

	protected ResponseEntity<ResultDto> return_success() {
		ResultDto R = new ResultDto();
		R.setMsg(CommonConstants.SUCCESS);
		return new ResponseEntity<ResultDto>(R, HttpStatus.OK);
	}
	
	
}