package com.betacom.veicoli.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.services.interfaces.IMessaggiServices;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionsManager {

	private final IMessaggiServices messaggiServices;

	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<ResponseDTO> handleException(Exception e) {
		return ResponseEntity.badRequest()
				.body(ResponseDTO.builder().msg(messaggiServices.get(e.getMessage())).build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleValidationException(MethodArgumentNotValidException e) {
		
		String msg = e.getBindingResult()
				.getFieldErrors()
				.stream()
				.findFirst()
				.map(FieldError::getDefaultMessage)
				.orElse("Errore di validazione");

		return ResponseEntity.badRequest().body(ResponseDTO.builder().msg(messaggiServices.get(msg)).build());
	}
}

