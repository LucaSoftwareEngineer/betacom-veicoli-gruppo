package com.betacom.veicoli.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.veicoli.exceptions.ValidationGroups;
import com.betacom.veicoli.dto.request.BiciRequest;
import com.betacom.veicoli.dto.response.BiciResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.VeicoliException;
import com.betacom.veicoli.services.interfaces.IBiciServices;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/bici/")
@RequiredArgsConstructor
public class BiciController {
	
	private final IBiciServices biciService;
	
	@Operation(
            summary = "BICI CREATE",
            description = "Questo metodo serve a inserire una nuova bici sul database",
            tags = {"Bici"}
    )
	@PostMapping("create")
	public ResponseEntity<BiciResponse> create(@RequestBody @Validated(ValidationGroups.Create.class) BiciRequest req) throws VeicoliException {
		return new ResponseEntity<>(biciService.create(req), HttpStatus.CREATED);
	}
	
	@Operation(
            summary = "BICI LIST",
            description = "Questo metodo serve a visualizzare le bici del database",
            tags = {"Bici"}
    )
	@GetMapping("list")
	public ResponseEntity<List<BiciResponse>> getAll() throws VeicoliException {
		return new ResponseEntity<>(biciService.getAll(), HttpStatus.OK);
	}
	
	@Operation(
            summary = "BICI BY ID",
            description = "Questo metodo serve a cercare una bici sul database",
            tags = {"Bici"}
    )
	@GetMapping("list/{id}")
	public ResponseEntity<BiciResponse> getById(@PathVariable @Min(value=1, message="l'id deve essere un numero") Integer id) throws VeicoliException {
		return new ResponseEntity<>(biciService.getById(id), HttpStatus.OK);
	}
	
	@Operation(
            summary = "BICI REMOVE",
            description = "Questo metodo serve a rimuovere una bici sul database",
            tags = {"Bici"}
    )
	@DeleteMapping("remove/{id}")
	public ResponseEntity<ResponseDTO> remove(@PathVariable @Min(value=1, message="l'id deve essere un numero") Integer id) throws VeicoliException {
		return new ResponseEntity<>(biciService.remove(id), HttpStatus.OK);
	}
	
	@Operation(
            summary = "BICI UPDATE",
            description = "Questo metodo serve a modificare una bici sul database",
            tags = {"Bici"}
    )
	@PostMapping("update/{id}")
	public ResponseEntity<BiciResponse> update(@PathVariable @Min(value=1, message="l'id deve essere un numero") Integer id, @RequestBody BiciRequest req) throws VeicoliException {
		return new ResponseEntity<>(biciService.update(req, id), HttpStatus.OK);
	}
	
	
	
}
