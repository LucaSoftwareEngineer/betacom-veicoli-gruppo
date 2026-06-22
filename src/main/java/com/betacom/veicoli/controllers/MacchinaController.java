package com.betacom.veicoli.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.veicoli.dto.request.MacchinaRequest;
import com.betacom.veicoli.dto.response.MacchinaResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.ValidationGroups;
import com.betacom.veicoli.exceptions.VeicoliException;
import com.betacom.veicoli.services.interfaces.IMacchinaServices;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/macchina/")
public class MacchinaController {
	
	private final IMacchinaServices iMacchinaServices;
	
	@Operation(
            summary = "CREATE MACCHINA",
            description = "Questo metodo serve a inserire una nuova macchina sul database",
            tags = {"Macchina"}
    )
    @PostMapping
    public ResponseEntity<MacchinaResponse> create(
    		@RequestBody @Validated(ValidationGroups.Create.class) MacchinaRequest req
    		) throws VeicoliException{
        return new ResponseEntity<>(iMacchinaServices.create(req), HttpStatus.CREATED);
    }
	
	@Operation(
            summary = "UPDATE MACCHINA",
            description = "Questo metodo serve a modificare una macchina",
            tags = {"Macchina"}
    )
    @PutMapping("/{id}")
    public ResponseEntity<MacchinaResponse> update(
    		@RequestBody MacchinaRequest req, 
    		@PathVariable @Min(value = 1, message = "id deve essere un numero intero maggiore di 0") Integer id
    		) throws VeicoliException{
        return new ResponseEntity<>(iMacchinaServices.update(req, id), HttpStatus.OK);
    }
	
	@Operation(
            summary = "GET ALL MACCHINE",
            description = "Questo metodo restituisce una lista di tutte le macchine presenti sul database",
            tags = {"Macchina"}
    )
    @GetMapping("/list")
    public ResponseEntity<List<MacchinaResponse>> getAll() throws VeicoliException{
        return new ResponseEntity<>(iMacchinaServices.getAll(), HttpStatus.OK);
    }
	
	@Operation(
            summary = "FIND MACCHINA BY ID",
            description = "Questo metodo serve a visualizzare la macchina selezionata con l'id",
            tags = {"Macchina"}
    )
    @GetMapping("/list/{id}")
    public ResponseEntity<MacchinaResponse> findById(@PathVariable @Min(value = 1, message = "id deve essere un numero intero maggiore di 0") Integer id) throws VeicoliException{
        return new ResponseEntity<>(iMacchinaServices.findById(id), HttpStatus.OK);
    }
	
	@Operation(
            summary = "DELETE MACCHINA BY ID",
            description = "Questo metodo serve a eliminare la macchina selezionata con l'id",
            tags = {"Macchina"}
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> remove(@PathVariable @Min(value = 1, message = "id deve essere un numero intero maggiore di 0") Integer id) throws VeicoliException{
        return new ResponseEntity<>(iMacchinaServices.remove(id), HttpStatus.OK);
    }

	
	
	
	
	
	
	
	
	
	
	
	
}
