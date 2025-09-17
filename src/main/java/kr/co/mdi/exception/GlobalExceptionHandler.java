package kr.co.mdi.exception;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CannotGetJdbcConnectionException.class)
	public String handleJdbcConnectionError(Model model) {
		model.addAttribute("errorMessage", "데이터베이스 연결에 실패했습니다.");
		return "error/db-error";
	}

	@ExceptionHandler(DataAccessException.class)
	public String handleDataAccessError(Model model) {
		model.addAttribute("errorMessage", "데이터 처리 중 오류가 발생했습니다.");
		return "error/db-error";
	}

	@ExceptionHandler(SQLException.class)
	public String handleSqlError(Model model) {
		model.addAttribute("errorMessage", "SQL 실행 중 문제가 발생했습니다.");
		return "error/db-error";
	}
}
