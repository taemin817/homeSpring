package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice	// AOP를 이용해 공통 관심사는 분리하자는 개념, 공통적인 예외사항에 대해서 별도로 @ControllerAdvice를 이용해 분리
@Log4j
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)	// 괄호 안에 있는 예외타입만 처리함 => Exception.class니까 except()만을 이용해 처리 가능
	public String except(Exception ex, Model model) {
		
		log.error("Exception....." + ex.getMessage());
		model.addAttribute("exception", ex);
		log.error(model);
		
		return "error_page";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)	// 존재하지않는 주소일때
	public String handle404(NoHandlerFoundException ex) {
		return "custom404";
	}
}
