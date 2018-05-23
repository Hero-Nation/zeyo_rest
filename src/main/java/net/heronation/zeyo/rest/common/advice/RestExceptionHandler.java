package net.heronation.zeyo.rest.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
 
//    @ExceptionHandler({ AccessDeniedException.class })
//    public ResponseEntity<ResultVO> handleAccessDeniedException(Exception ex, WebRequest request) {
//     
//    	ResultVO R = new ResultVO();
//		R.setMsg(CommonConstants.FAIL);
//		R.setR("히어로네이션 : 권한이 없는 접근입니다.");
//		return new ResponseEntity<ResultVO>(R, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ResultVO>   handleNormalException(Exception ex, WebRequest request) {
        ex.printStackTrace();
    	ResultVO R = new ResultVO();
		R.setMsg(CommonConstants.FAIL);
		R.setR(ex.toString());
		return new ResponseEntity<ResultVO>(R, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ResultVO R = new ResultVO();
		R.setMsg(CommonConstants.FAIL);
		R.setR(ex.toString());
		
		return new ResponseEntity<Object>(R, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
}