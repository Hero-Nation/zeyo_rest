package net.heronation.zeyo.rest.common.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;

public class BaseController {

	protected ResponseEntity<ResultVO> return_fail(Object Result) {
		ResultVO R = new ResultVO();
		R.setMsg(CommonConstants.FAIL);
		R.setR(Result);
		return new ResponseEntity<ResultVO>(R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
 
	
	protected ResponseEntity<ResultVO> return_fail(String Result) {
		ResultVO R = new ResultVO();
		R.setMsg(CommonConstants.FAIL);
		R.setR(Result);
		return new ResponseEntity<ResultVO>(R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	protected ResponseEntity<ResultVO> return_fail() {
		ResultVO R = new ResultVO();
		R.setMsg(CommonConstants.FAIL);
		return new ResponseEntity<ResultVO>(R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	protected ResponseEntity<ResultVO> return_success_obj(String Result) {
		ResultVO R = new ResultVO();
		R.setMsg(CommonConstants.SUCCESS);
		R.setR(Result);
		return new ResponseEntity<ResultVO>(R, HttpStatus.OK);
	}
	
 
	
	protected ResponseEntity<ResultVO> return_success(Object object) {
		ResultVO R = new ResultVO();
		R.setMsg(CommonConstants.SUCCESS);
		R.setR(object);
		return new ResponseEntity<ResultVO>(R, HttpStatus.OK);
	}

	protected ResponseEntity<ResultVO> return_success() {
		ResultVO R = new ResultVO();
		R.setMsg(CommonConstants.SUCCESS);
		return new ResponseEntity<ResultVO>(R, HttpStatus.OK);
	}
	
	
}